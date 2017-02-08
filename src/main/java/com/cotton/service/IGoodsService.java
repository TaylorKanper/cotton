package com.cotton.service;

import com.cotton.model.bean.GoodsBean;
import com.cotton.model.common.Page;
import com.cotton.model.common.Response;

import java.util.List;

public interface IGoodsService {
    /**
     * 添加商品
     *
     * @param bean
     * @return
     */
    Response<?> add(GoodsBean bean);



    /**
     * 分页查询商品
     *
     * @param bean
     * @param page
     * @param rows
     * @return
     */
    Response<Page<GoodsBean>> findByPage(GoodsBean bean, int page, int rows);

    /**
     * 批量删除商品
     * @param ids
     * @return
     */
    Response<Integer> delGoods(Integer[] ids);

    /**
     * 修改商品
     * @param goodsBean
     * @return
     */
    Response<?> updateGoods(GoodsBean goodsBean);

    /**
     * 修改商品数量
     * @param goodsBean
     * @return
     */
    Response<?> updateNumber(GoodsBean goodsBean);
}
