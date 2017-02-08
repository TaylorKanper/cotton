package com.cotton.controller;

import com.cotton.model.bean.MenuBean;
import com.cotton.model.bean.UserBean;
import com.cotton.model.common.ActionResult;
import com.cotton.model.common.Response;
import com.cotton.service.IMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by hp on 2016/12/15.
 */
@Controller
@RequestMapping("/menu")
@Slf4j
public class MenuController {
    @Autowired
    private IMenuService menuService;

    @RequestMapping("/findAll")
    @ResponseBody
    public ActionResult findAll(UserBean user) {
        try {
            Response<List<MenuBean>> res = menuService.findMenuByUser(user);
            return res.isOk() ? ActionResult.success("查询成功", res.getResult()) : ActionResult.fail("查询失败");
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return ActionResult.fail("查询失败");
    }
    @RequestMapping("/jump")
    public ModelAndView jump(String url){
        ModelAndView m = new ModelAndView(url);
        m.addObject("m",url);
        return m;
    }
}
