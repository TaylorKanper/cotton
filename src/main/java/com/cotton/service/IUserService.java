package com.cotton.service;

import com.cotton.model.bean.UserBean;
import com.cotton.model.common.Page;
import com.cotton.model.common.Response;

import java.util.List;

/**
 * Created by hp on 2016/12/13.
 */
public interface IUserService {
    /**
     * 查找所有用户的分页实体
     *
     * @param page 页码
     * @param rows 页长
     * @return 分页实体
     */
    Response<Page<UserBean>> findAllUser(int page, int rows);

    /**
     * 添加用户
     *
     * @param bean
     * @return
     */
    Response<Boolean> addUser(UserBean bean);

    /**
     * 删除/启用用户
     *
     * @param id
     * @param type
     * @return
     */
    Response<Boolean> changeUserStatus(int id, int type);

    /**
     * 用户登录
     *
     * @param userName
     * @param passWord
     * @return
     */
    Response<UserBean> login(String userName, String passWord);
}
