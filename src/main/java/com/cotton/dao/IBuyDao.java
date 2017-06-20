package com.cotton.dao;

import com.cotton.model.bean.OrderBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface IBuyDao {
    int addOrders(@Param("list") List<OrderBean> list);
    int addOrder(@Param("orderBean") OrderBean orderBean);
    List<OrderBean> findAllToday(@Param("orderBean") OrderBean orderBean);
    int delOrder(@Param("orderBean") OrderBean orderBean);
}
