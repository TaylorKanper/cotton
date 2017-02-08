package com.cotton.service;

import com.cotton.model.bean.MenuBean;
import com.cotton.model.bean.UserBean;
import com.cotton.model.common.Response;

import java.util.List;

/**
 * Created by hp on 2016/12/12.
 */
public interface IMenuService {
    /**
     * 查找所有菜单
     *
     * @param user 判断是否王芮和我,如果是,展示全部菜单,否,某些菜单不展示
     * @return 菜单集合
     */
    Response<List<MenuBean>> findMenuByUser(UserBean user);
}
