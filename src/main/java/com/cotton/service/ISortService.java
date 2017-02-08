package com.cotton.service;

import com.cotton.model.bean.SortBean;
import com.cotton.model.common.Page;
import com.cotton.model.common.Response;
import com.cotton.model.dto.CategoryDto;

import java.util.List;

/**
 * Created by hp on 2016/12/18.
 */
public interface ISortService {
    /**
     * 添加类目
     *
     * @param bean 类目实体
     * @return
     */
    Response<?> addSort(SortBean bean);

    /**
     * 查找某一级别的所有类目
     *
     * @param level 类目级别
     * @return
     */
    Response<List<SortBean>> findByLevel(int level);

    /**
     * 分页查询
     *
     * @param bean
     * @return
     */
    Response<Page<SortBean>> findByPage(SortBean bean, int page, int rows);

    /**
     * 批量删除类目
     *
     * @param list 类目集合
     * @return
     */
    Response<Integer> delSort(List<SortBean> list);

    /**
     * 修改类目
     *
     * @param bean
     * @return
     */
    Response<?> updateCategory(SortBean bean);

    /**
     * 提供id查找该id下的所有类目
     * @param id
     * @return
     */
    Response<List<SortBean>> findChild(int id);

    /**
     * 查找所有类目,并且包含其子类目
     * @return
     */
    Response<List<CategoryDto>> findCategory();

}
