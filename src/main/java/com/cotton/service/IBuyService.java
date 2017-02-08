package com.cotton.service;

import com.cotton.model.bean.GoodsBean;
import com.cotton.model.bean.OrderBean;
import com.cotton.model.common.Response;

import java.util.List;

/**
 * Created by hp on 2016/12/31.
 */
public interface IBuyService {
    /**
     * 创建购买订单
     *
     * @param list
     * @return
     */
    Response<?> addOrder(List<GoodsBean> list);

    /**
     * 购买单个商品
     *
     * @param id
     * @return
     */
    Response<?> addOneOrder(Integer id);

    /**
     * 多数量购买商品
     *
     * @param goodsBean
     * @return
     */
    Response multiBuyGoods(GoodsBean goodsBean);
}
