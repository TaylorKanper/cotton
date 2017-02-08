package com.cotton.service.impl;

import com.cotton.dao.IUserDao;
import com.cotton.model.bean.UserBean;
import com.cotton.model.common.Page;
import com.cotton.model.common.Response;
import com.cotton.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hp on 2016/12/13.
 */
@Service
@Slf4j
public class UserServiceImpl extends BaseServiceImpl<UserBean> implements IUserService {
    @Resource
    private IUserDao userDao;

    /**
     * 查找所有用户
     *
     * @return
     */
    @Override
    public Response<Page<UserBean>> findAllUser(int page, int rows) {
        try {
            List<UserBean> list = userDao.findUserPage((page - 1) * rows, rows);
            int total = userDao.findUserTotal();
            return super.findByPage(list, total);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return Response.fail("未查询到用户");
    }

    /**
     * 添加用户
     *
     * @param bean
     * @return
     */
    @Override
    public Response<Boolean> addUser(UserBean bean) {
        try {
            bean.setStatus(1);
            int i = userDao.addUser(bean);
            if (i >= 1)
                return Response.ok(true);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return Response.fail("添加失败");
    }

    /**
     * 修改用户的状态
     *
     * @param id
     * @return
     */
    @Override
    public Response<Boolean> changeUserStatus(int id, int type) {
        try {
            if (type == 1) {
                return Response.ok(userDao.delUser(id) > 0);
            } else {
                return Response.ok(userDao.reUseUser(id) > 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return Response.fail("删除失败");
    }

    /**
     * 用户登录
     *
     * @param userName
     * @param passWord
     * @return
     */
    @Override
    public Response<UserBean> login(String userName, String passWord) {
        try {
            UserBean user = userDao.login(userName, passWord);
            return Response.ok(user);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return Response.fail("未查找到用户");
    }
}
