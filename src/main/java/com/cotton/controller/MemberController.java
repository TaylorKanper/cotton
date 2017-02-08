package com.cotton.controller;

import com.alibaba.fastjson.JSON;
import com.cotton.model.bean.MemberBean;
import com.cotton.model.common.ActionResult;
import com.cotton.model.common.Page;
import com.cotton.model.common.Response;
import com.cotton.model.dto.MessageTemple;
import com.cotton.service.IMemberService;
import com.cotton.util.MessageSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Member;
import java.util.Iterator;
import java.util.List;

/**
 * Created by hp on 2016/12/20.
 */
@Controller
@RequestMapping("/member")
@Slf4j
public class MemberController {
    @Autowired
    private IMemberService memberService;

    @RequestMapping("/index")
    public ModelAndView index() {
        return new ModelAndView("/member/index");
    }

    @RequestMapping("/updateMemberFrame")
    public ModelAndView updateMemberFrame(MemberBean member) {
        ModelAndView m = new ModelAndView("/member/updateMemberFrame");
        m.addObject("member", member);
        return m;
    }

    @RequestMapping("/addMemberFrame")
    public ModelAndView addMemberFrame() {
        return new ModelAndView("/member/addMember");
    }

    @RequestMapping("/findAllByPage")
    @ResponseBody
    public Page<MemberBean> findByPage(MemberBean bean, int page, int rows) {
        try {
            Response<Page<MemberBean>> response = memberService.findByPage(bean, page, rows);
            if (response.isOk()) {
                return response.getResult();
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return null;
    }

    @RequestMapping("/add")
    @ResponseBody
    public ActionResult addMember(MemberBean member) {
        try {
            Response res = memberService.addMember(member);
            if (res.isOk()) {
                return ActionResult.success("成功", "添加成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return ActionResult.fail("添加失败");
    }

    @RequestMapping("/findAllByName")
    @ResponseBody
    public ActionResult findAllByName(String name) {
        try {
            Response res = memberService.findAllByName(name);
            if (res.isOk()) {
                return ActionResult.success("成功", res.getResult());
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return ActionResult.fail("查询失败");
    }

    @RequestMapping("/del")
    @ResponseBody
    public ActionResult delMember(@RequestBody Integer[] ids) {
        try {
            Response<Integer> res = memberService.delMembers(ids);
            if (res.isOk()) {
                return ActionResult.success("删除成功", res.getResult());
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return ActionResult.fail("删除失败");
    }

    @RequestMapping("/checkPhoneExist")
    @ResponseBody
    public Boolean checkPhoneExist(String memberPhone) {
        try {
            Response<MemberBean> res = memberService.findByPhone(memberPhone);
            if (res.getResult() != null) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @RequestMapping("/updateMember")
    @ResponseBody
    public ActionResult updateMember(MemberBean member) {
        try {
            Response<?> res = memberService.updateMember(member);
            if (res.isOk()) {
                return ActionResult.success("修改成功", res.getResult());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ActionResult.fail("修改失败");
    }

    @RequestMapping("/sendMessage")
    @ResponseBody
    public ActionResult sendMessage(@RequestBody List<MemberBean> list) {
        try {
            StringBuffer message = new StringBuffer();
            Iterator<MemberBean> it = list.iterator();
            while (it.hasNext()) {
                String s = MessageSender.sendMessage(it.next());
                MessageTemple m = JSON.parseObject(s, MessageTemple.class);
                message.append("成功发送~~会员名:" + m.getName() + "~~手机号:" + m.getPhone() + "<br/>");
            }
            return ActionResult.success("发送成功", message.toString());
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }

        return ActionResult.fail("发送失败");
    }

}
