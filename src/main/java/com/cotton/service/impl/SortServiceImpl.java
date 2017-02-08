package com.cotton.service.impl;

import com.cotton.dao.ISortDao;
import com.cotton.model.bean.SortBean;
import com.cotton.model.common.Page;
import com.cotton.model.common.Response;
import com.cotton.model.dto.CategoryDto;
import com.cotton.service.ISortService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by hp on 2016/12/18.
 */
@Service
@Slf4j
public class SortServiceImpl extends BaseServiceImpl<SortBean> implements ISortService {
    @Resource
    private ISortDao sortDao;

    /**
     * 添加类目
     *
     * @param bean 类目实体
     * @return
     */
    @Override
    public Response<?> addSort(SortBean bean) {
        try {
            List<SortBean> l = sortDao.findByName(bean.getSortName(), bean.getLevel());
            if (null != l && l.size() != 0) {
                return Response.fail("添加失败", "数据库已存在[" + bean.getSortName() + "]的类目");
            }
            bean.setStatus(1);
            bean.setCreateDate(new Date(System.currentTimeMillis()));
            String sortName = sortDao.findSort(bean.getFatherId()) == null ? "顶级" : sortDao.findSort(bean.getFatherId()).getSortName();
            bean.setFatherName(sortName);
            int i = sortDao.addSort(bean);
            return i > 0 ? Response.ok(true) : Response.fail("添加失败");
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return Response.fail("添加失败");
    }

    /**
     * 查找某一级别的所有类目
     *
     * @param level 类目级别
     * @return
     */
    @Override
    public Response<List<SortBean>> findByLevel(int level) {
        try {
            return Response.ok(sortDao.findByLevel(level));
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return Response.fail("没有找到类目");
    }

    /**
     * 分页查询
     *
     * @param bean
     * @return
     */
    @Override
    public Response<Page<SortBean>> findByPage(SortBean bean, int page, int rows) {
        try {
            List<SortBean> list = sortDao.findSortPage(bean, (page - 1) * rows, rows);
            int total = sortDao.findSortTotal(bean);
            return super.findByPage(list, total);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return Response.fail("未查询到结果集");
    }

    /**
     * 批量删除类目,如果类目等级为1,则旗下所有二级类目也将删除
     *
     * @param list 类目集合
     * @return
     */
    @Override
    public Response<Integer> delSort(List<SortBean> list) {
        try {
            int count = 0;
            Iterator<SortBean> it = list.iterator();
            while (it.hasNext()) {
                SortBean s = it.next();
                if (s.getLevel() == 1) {
                    sortDao.delSort(s.getId());
                    count++;
                    int child = sortDao.delChild(s.getId());
                    count = count + child;
                } else {
                    sortDao.delSort(s.getId());
                    count++;
                }
            }
            return Response.ok(count);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return Response.fail("删除失败");
    }

    /**
     * 修改类目
     *
     * @param bean
     * @return
     */
    @Override
    public Response<?> updateCategory(SortBean bean) {
        try {
            String sortName = sortDao.findSort(bean.getFatherId()) == null ? "顶级" : sortDao.findSort(bean.getFatherId()).getSortName();
            bean.setFatherName(sortName);
            return sortDao.updateSort(bean) > 0 ? Response.ok("修改成功") : Response.fail("修改失败");
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return Response.fail("修改失败");
    }

    /**
     * 提供id查找该id下的所有类目
     *
     * @param id
     * @return
     */
    @Override
    public Response<List<SortBean>> findChild(int id) {
        try {
            List<SortBean> list = sortDao.findChild(id);
            return Response.ok(list);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return Response.fail("该类目下没有子类目");
    }

    /**
     * 查找所有类目,并且包含其子类目
     *
     * @return
     */
    @Override
    public Response<List<CategoryDto>> findCategory() {
        try {
            List<SortBean> list = sortDao.findByLevel(1);
            List<CategoryDto> result = new LinkedList<>();
            Iterator<SortBean> it = list.iterator();
            while (it.hasNext()) {
                SortBean sort = it.next();
                CategoryDto dto = new CategoryDto();
                dto.setId(sort.getId());
                dto.setCategoryName(sort.getSortName());
                dto.setChildren(sortDao.findChild(sort.getId()));
                result.add(dto);
            }
            return Response.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return Response.fail("查询失败...");
    }

}

