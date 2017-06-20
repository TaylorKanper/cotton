package com.cotton.service.impl;

import com.cotton.dao.IBuyDao;
import com.cotton.dao.IGoodsDao;
import com.cotton.dao.IMemberDao;
import com.cotton.model.bean.GoodsBean;
import com.cotton.model.bean.OrderBean;
import com.cotton.model.common.Response;
import com.cotton.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl extends BaseServiceImpl<OrderBean> implements IOrderService {
    @Resource
    private IBuyDao dao;
    @Resource
    private IGoodsDao goodsDao;
    @Resource
    private IMemberDao memberDao;

    @Override
    public Response<List<OrderBean>> findAll(OrderBean bean) {
        try {
            List<OrderBean> list = dao.findAllToday(bean);
            return Response.ok(list);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return Response.fail("未查到结果集");
    }

    /**
     * 退货,首先删除记录,商品数量恢复,如果是会员购买,那么相应的积分扣除
     *
     * @param bean 退货实体
     * @return 是否退货成功
     */
    @Override
    public Response returnGoods(OrderBean bean) {
        try {
            GoodsBean goodsBean = new GoodsBean();
            goodsBean.setId(bean.getGoodsId());
            goodsBean.setNumber(bean.getNum());
            goodsDao.updateNumber(goodsBean);
            if (bean.getMemberId() != null) {
                int score = (int) (bean.getPrice() * bean.getNum());
                memberDao.reduceScore(bean.getMemberId(), score);
            }
            dao.delOrder(bean);
            return Response.ok("商品退货成功");
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return Response.fail("商品退货失败");
    }
}
