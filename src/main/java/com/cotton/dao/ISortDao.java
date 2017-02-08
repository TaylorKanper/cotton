package com.cotton.dao;

import com.cotton.model.bean.SortBean;
import com.cotton.model.common.Response;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by hp on 2016/12/18.
 */
public interface ISortDao {
    /**
     * 添加类目
     *
     * @param bean 类目实体
     * @return
     */
    int addSort(SortBean bean);

    /**
     * 查找所有在level值之下有效的类目
     *
     * @param level
     * @return
     */
    List<SortBean> findByLevel(int level);

    /**
     * 分页查找类目
     *
     * @param bean
     * @param page
     * @param rows
     * @return
     */
    List<SortBean> findSortPage(@Param("bean") SortBean bean, @Param("page") int page, @Param("rows") int rows);

    /**
     * 查询分页条件下所有的类目
     *
     * @param bean
     * @return
     */
    int findSortTotal(@Param("bean") SortBean bean);

    /**
     * 根据id查类目的名称
     *
     * @param id
     * @return
     */
    SortBean findSort(int id);

    /**
     * 查找库里是否有重名的类目
     *
     * @param sortName 类目名称
     * @return
     */
    List<SortBean> findByName(@Param("sortName") String sortName, @Param("level") int level);

    /**
     * 删除sort
     *
     * @param id
     * @return
     */
    int delSort(int id);

    /**
     * 修改sort
     *
     * @param bean
     * @return
     */
    int updateSort(SortBean bean);

    /**
     * 批量删除类目
     *
     * @param fatherId
     * @return
     */
    int delChild(int fatherId);

    /**
     * 根据id查子类目
     * @param id
     * @return
     */
    List<SortBean> findChild(int id);
}
