package com.cotton.service.impl;

import com.cotton.dao.IBuyDao;
import com.cotton.dao.IGoodsDao;
import com.cotton.model.bean.GoodsBean;
import com.cotton.model.bean.OrderBean;
import com.cotton.model.common.Response;
import com.cotton.model.constent.DiscountConstant;
import com.cotton.model.constent.NumberConstant;
import com.cotton.service.IBuyService;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by hp on 2017/1/5.
 */
@Service
@Slf4j
@Transactional
public class BuyServiceImpl extends BaseServiceImpl<OrderBean> implements IBuyService {
    @Resource
    private IBuyDao buyDao;
    @Resource
    private IGoodsDao goodsDao;


    /**
     * 创建购买订单
     *
     * @param list
     * @return
     */
    @Override
    public Response<?> addOrder(List<GoodsBean> list) {
        try {
            Iterator<GoodsBean> it = list.iterator();
            List<OrderBean> l = new ArrayList<>();
            while (it.hasNext()) {
                GoodsBean g = it.next();
                OrderBean o = new OrderBean();
                o.setFirstId(g.getFirstSortId());
                o.setSecondId(g.getSecondSortId());
                o.setGoodsName(g.getGoodsName());
                o.setGoodsId(g.getId());
                o.setDiscount(DiscountConstant.ONE.getValue());
                o.setNum(NumberConstant.ONE.getValue());
                o.setPrice(g.getPrice());
                l.add(o);
            }
            int number = buyDao.addOrders(l);
            int num = goodsDao.subtractOne(list);
            if (number > 0 && num > 0) {
                goodsDao.checkNumber();
                return Response.ok("购买成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return Response.fail("购买失败");
    }

    /**
     * 购买单个商品
     *
     * @param id
     * @return
     */
    @Override
    public Response<?> addOneOrder(Integer id) {
        try {
            GoodsBean g = goodsDao.findGoodsById(id);
            OrderBean o = new OrderBean();
            o.setFirstId(g.getFirstSortId());
            o.setSecondId(g.getSecondSortId());
            o.setGoodsName(g.getGoodsName());
            o.setGoodsId(g.getId());
            o.setDiscount(DiscountConstant.ONE.getValue());
            o.setNum(NumberConstant.ONE.getValue());
            o.setPrice(g.getPrice());
            int number = buyDao.addOrder(o);
            List<GoodsBean> list = new ArrayList<>();
            list.add(g);
            int num = goodsDao.subtractOne(list);
            if (number > 0 && num > 0) {
                goodsDao.checkNumber();
                return Response.ok("购买成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return Response.ok("购买失败");
    }

    /**
     * 多数量购买商品
     *
     * @param goodsBean
     * @return
     */
    @Override
    public Response multiBuyGoods(GoodsBean goodsBean) {
        try {
            GoodsBean g = goodsDao.findGoodsById(goodsBean.getId());
            OrderBean o = new OrderBean();
            o.setFirstId(g.getFirstSortId());
            o.setSecondId(g.getSecondSortId());
            o.setGoodsName(g.getGoodsName());
            o.setDiscount(DiscountConstant.ONE.getValue());
            o.setNum(NumberConstant.ONE.getValue());
            o.setPrice(g.getPrice());
            o.setNum(goodsBean.getNumber());
            o.setGoodsId(goodsBean.getId());
            goodsBean.setNumber(-goodsBean.getNumber());
            int num = goodsDao.updateNumber(goodsBean);
            int number = buyDao.addOrder(o);
            if (number > 0 && num > 0) {
                goodsDao.checkNumber();
                return Response.ok("购买成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return Response.ok("购买失败");
    }


}
