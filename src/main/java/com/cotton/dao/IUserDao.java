package com.cotton.dao;

import com.cotton.model.bean.UserBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by hp on 2016/12/13.
 */
public interface IUserDao {
    /**
     * 向数据库插入用户
     *
     * @param bean
     * @return
     */
    int addUser(UserBean bean);

    /**
     * 查询所有用户
     *
     * @return
     */
    List<UserBean> findAllUser();

    /**
     * 删除某个用户
     *
     * @param id
     * @return
     */
    int delUser(int id);

    /**
     * 启用某个用户
     * @param id
     * @return
     */
    int reUseUser(int id);

    /**
     * 用户登录
     *
     * @param userName
     * @param passWord
     * @return
     */
    UserBean login(@Param("userName") String userName, @Param("passWord") String passWord);

    /**
     * 查询start~end记录
     *
     * @param start 起始索引
     * @param end   结束索引
     * @return
     */
    List<UserBean> findUserPage(@Param("start") int start, @Param("end") int end);

    /**
     * 查找所有用户总数
     *
     * @return
     */
    int findUserTotal();
}
