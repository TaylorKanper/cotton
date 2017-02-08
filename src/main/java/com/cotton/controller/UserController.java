package com.cotton.controller;

import com.cotton.model.bean.MenuBean;
import com.cotton.model.bean.UserBean;
import com.cotton.model.common.ActionResult;
import com.cotton.model.common.Page;
import com.cotton.model.common.Response;
import com.cotton.service.IMenuService;
import com.cotton.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by hp on 2016/12/12.
 */
@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IMenuService menuService;

    @RequestMapping("/login")
    public ModelAndView login(String userName, String passWord, HttpServletRequest request) {
        ModelAndView m = new ModelAndView("index/index");
        try {
            Response<UserBean> res = userService.login(userName, passWord);
            Response<List<MenuBean>> ress = menuService.findMenuByUser(res.getResult());
            if (res.isOk() && ress.isOk()) {
                request.getSession().setAttribute("user", res.getResult());
                request.getSession().setAttribute("menus", ress.getResult());
                return m;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return null;
    }

    @RequestMapping("/datagrid")
    public ModelAndView userDataGrid() {
        return new ModelAndView("/index/datagrid");
    }

    @RequestMapping("/addUserFrame")
    public ModelAndView addUserFrame() {
        return new ModelAndView("/index/addUser");
    }

    @RequestMapping(value = "/addUser", method = {RequestMethod.POST})
    @ResponseBody
    public ActionResult addUser(UserBean user) {
        try {
            Response<Boolean> res = userService.addUser(user);
            if (res.isOk()) {
                return ActionResult.success("添加成功", res.getResult());
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return ActionResult.fail("添加失败");
    }

    @RequestMapping("/findAll")
    @ResponseBody
    public Page<UserBean> findAllUser(int page, int rows) {
        try {
            Response<Page<UserBean>> res = userService.findAllUser(page, rows);
            if (res.isOk()) {
                return res.getResult();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/changeStatus")
    @ResponseBody
    public ActionResult changeStatus(int id, int status) {

        try {
            Response<Boolean> res = userService.changeUserStatus(id, status);
            return ActionResult.success("修改成功", res.getResult());
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return null;
    }
}
