package com.cotton.service;

import com.cotton.model.bean.MemberBean;
import com.cotton.model.common.Page;
import com.cotton.model.common.Response;

/**
 * Created by hp on 2016/12/21.
 */
public interface IMemberService {
    /**
     * 分页查询会员
     *
     * @param bean
     * @param page
     * @param rows
     * @return
     */
    Response<Page<MemberBean>> findByPage(MemberBean bean, int page, int rows);

    /**
     * 添加会员
     * @param bean
     * @return
     */
    Response<Boolean> addMember(MemberBean bean);

    /**
     * 批量删除会员
     * @param ids
     * @return
     */
    Response<Integer> delMembers(Integer[] ids);

    /**
     * 根据手机号查找用户
     * @param phone
     * @return
     */
    Response<MemberBean> findByPhone(String phone);

    /**
     * 修改用户
     * @param member
     * @return
     */
    Response<?> updateMember(MemberBean member);

    /**
     * 查找全部
     * @return
     */
    Response<?> findAll(Integer low,Integer high);

    /**
     * 根据输入值匹配会员姓名模糊查找
     * @param name 会员姓名模糊字
     * @return
     */
    Response<?> findAllByName(String name);
}
