package com.cotton.dao;

import com.cotton.model.bean.MemberBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by hp on 2016/12/20.
 */
public interface IMemberDao {
    /**
     * 添加会员
     *
     * @param bean
     * @return
     */
    int addMember(@Param("bean") MemberBean bean);

    /**
     * 删除会员
     *
     * @param ids
     * @return
     */
    int delMember(@Param("ids") Integer[] ids);

    /**
     * 修改会员
     *
     * @param member
     * @return
     */
    int updateMember(@Param("member") MemberBean member);


    /**
     * 根据id查找用户
     *
     * @param id
     * @return
     */
    MemberBean findMemberById(@Param("id") int id);

    /**
     * 分页查找
     *
     * @param member
     * @param start
     * @param end
     * @return
     */
    List<MemberBean> findByPage(@Param("member") MemberBean member, @Param("start") int start, @Param("end") int end);

    /**
     * 查找总条数
     *
     * @param member
     * @return
     */
    int findTotalCount(@Param("member") MemberBean member);

    /**
     * 根据手机号查找用户
     * @param phone 手机号
     * @return
     */
    MemberBean findMemberByPhone(@Param("phone") String phone);

    /**
     * 查找全部
     * @return
     */
    List<MemberBean> findAll(@Param("low") Integer low, @Param("high") Integer high);

    /**
     * 姓名匹配查找全部
     * @param name
     * @return
     */
    List<MemberBean> findAllByName(String name);

    /**
     * 为会员增加积分
     * @param id
     * @param s
     */
    void addScore(@Param("id") int id, @Param("s") int s);

    /**
     * 为会员减少积分
     * @param id
     * @param score
     */
    void reduceScore(@Param("id") int id, @Param("score") int score);
}
