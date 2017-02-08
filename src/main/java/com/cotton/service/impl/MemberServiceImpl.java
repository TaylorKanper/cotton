package com.cotton.service.impl;

import com.cotton.dao.IMemberDao;
import com.cotton.model.bean.MemberBean;
import com.cotton.model.bean.SortBean;
import com.cotton.model.common.Page;
import com.cotton.model.common.Response;
import com.cotton.service.IMemberService;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Date;
import java.util.List;

/**
 * Created by hp on 2016/12/21.
 */
@Service
@Slf4j
public class MemberServiceImpl extends BaseServiceImpl<MemberBean> implements IMemberService {
    @Resource
    private IMemberDao memberDao;

    /**
     * 分页查询会员
     *
     * @param bean
     * @param page
     * @param rows
     * @return
     */
    @Override
    public Response<Page<MemberBean>> findByPage(MemberBean bean, int page, int rows) {
        try {
            List<MemberBean> l = memberDao.findByPage(bean, (page - 1) * rows, rows);
            int total = memberDao.findTotalCount(bean);
            return super.findByPage(l, total);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.fail("未查到结果集");
    }

    /**
     * 添加会员
     *
     * @param bean
     * @return
     */
    @Override
    public Response<Boolean> addMember(MemberBean bean) {
        try {
            bean.setStatus(1);
            bean.setMemberScore(0);
            bean.setMemberCreate(DateTime.now().toDate());
            DateTime dt = new DateTime(bean.getMemberBirthday());
            int MemberMonth = dt.getMonthOfYear();
            int month = DateTime.now().getMonthOfYear();
            if (MemberMonth < month) {
                bean.setChance(-1);
            } else {
                bean.setChance(1);
            }
            if (memberDao.addMember(bean) > 0) {
                return Response.ok(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return Response.fail("添加失败");
    }

    /**
     * 批量删除会员
     *
     * @param ids
     * @return
     */
    @Override
    public Response<Integer> delMembers(Integer[] ids) {
        try {
            int i = memberDao.delMember(ids);
            if (i > 0) {
                return Response.ok(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return Response.fail("删除失败");
    }

    /**
     * 根据手机号查找用户
     *
     * @param phone
     * @return
     */
    @Override
    public Response<MemberBean> findByPhone(String phone) {
        try {
            return Response.ok(memberDao.findMemberByPhone(phone));
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return Response.fail("未找到该手机用户");
    }

    /**
     * 修改用户
     *
     * @param member
     * @return
     */
    @Override
    public Response<?> updateMember(MemberBean member) {
        try {
            int i = memberDao.updateMember(member);
            if (i > 0) {
                return Response.ok("修改成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return Response.fail("修改失败");
    }

    /**
     * 查找全部
     *
     * @return
     */
    @Override
    public Response<?> findAll(Integer low, Integer high) {
        try {
            List<MemberBean> list = memberDao.findAll(low,high);
            if (list.size()>0){
                return Response.ok(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return Response.fail("查询失败");
    }

    /**
     * 根据输入值匹配会员姓名模糊查找
     *
     * @param name 会员姓名模糊字
     * @return
     */
    @Override
    public Response<?> findAllByName(String name) {
        try {
            List<MemberBean> list = memberDao.findAllByName(name);
            if (list.size()>0){
                return Response.ok(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return Response.fail("查询失败");
    }
}
