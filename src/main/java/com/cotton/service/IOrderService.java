package com.cotton.service;

import com.cotton.model.bean.OrderBean;
import com.cotton.model.common.Response;

import java.util.List;

/**
 * 订单接口
 */
public interface IOrderService {
    /**
     * 查询某日所有的销售记录
     * @param bean
     * @return
     */
    Response<List<OrderBean>> findAll(OrderBean bean);

    /**
     * 退货,首先删除记录,商品数量恢复,如果是会员购买,那么相应的积分扣除
     * @param bean 退货实体
     * @return 是否退货成功
     */
    Response returnGoods(OrderBean bean);
}
