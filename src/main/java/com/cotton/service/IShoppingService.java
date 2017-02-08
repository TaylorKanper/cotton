package com.cotton.service;

import com.cotton.model.bean.GoodsBean;
import com.cotton.model.bean.ShoppingBean;
import com.cotton.model.common.Response;

import java.util.List;
import java.util.Map;

public interface IShoppingService {
    /**
     * 购买商品
     *
     * @param list
     * @param shopCart
     * @return
     */
    Response<?> buy(List<GoodsBean> list, Map<Integer, ShoppingBean> shopCart);

    /**
     * 移除商品
     *
     * @param id
     * @param shopCart
     * @return
     */
    Response<?> remove(Integer id, Map<Integer, ShoppingBean> shopCart);

    /**
     * 会员购买商品
     * @param list
     * @param buyMemberPhone
     * @return
     */
    Response buyGoods(List<ShoppingBean> list, String buyMemberPhone);


}
