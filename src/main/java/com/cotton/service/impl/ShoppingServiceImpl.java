package com.cotton.service.impl;

import com.cotton.dao.IBuyDao;
import com.cotton.dao.IGoodsDao;
import com.cotton.dao.IMemberDao;
import com.cotton.model.bean.GoodsBean;
import com.cotton.model.bean.MemberBean;
import com.cotton.model.bean.OrderBean;
import com.cotton.model.bean.ShoppingBean;
import com.cotton.model.common.Response;
import com.cotton.model.constent.DiscountConstant;
import com.cotton.model.constent.NumberConstant;
import com.cotton.service.IShoppingService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@Transactional
public class ShoppingServiceImpl implements IShoppingService {
    @Resource
    private IGoodsDao goodsDao;
    @Resource
    private IBuyDao buyDao;
    @Resource
    private IMemberDao memberDao;

    /**
     * 购买商品
     *
     * @param list
     * @param shopCart
     * @return
     */
    @Override
    public Response<?> buy(List<GoodsBean> list, Map<Integer, ShoppingBean> shopCart) {
        try {
            Iterator<GoodsBean> it = list.iterator();
            while (it.hasNext()) {
                GoodsBean g = it.next();
                if (shopCart.containsKey(g.getId())) {// 购物车集合包含该商品
                    ShoppingBean bean = shopCart.get(g.getId());
                    bean.setBuyNumber(bean.getBuyNumber() + NumberConstant.ONE.getValue());
                } else {
                    ShoppingBean bean = new ShoppingBean();
                    bean.setGoodsId(g.getId());
                    bean.setBuyNumber(NumberConstant.ONE.getValue());
                    bean.setDiscount(DiscountConstant.ONE.getValue());
                    GoodsBean product = goodsDao.findGoodsById(g.getId());
                    bean.setSoldPrice(product.getPrice());
                    bean.setGoodsBean(product);
                    bean.setFirstSortName(product.getFirstSortName());
                    bean.setSecondSortName(product.getSecondSortName());
                    bean.setDescription(product.getDescription());
                    bean.setGoodsName(product.getGoodsName());
                    shopCart.put(g.getId(), bean);
                }
            }
            return Response.ok("添加购物车成功");
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return Response.fail("添加购物车失败");
    }

    /**
     * 移除商品
     *
     * @param id
     * @param shopCart
     * @return
     */
    @Override
    public Response<?> remove(Integer id, Map<Integer, ShoppingBean> shopCart) {
        try {
            shopCart.remove(id);
            return Response.ok("移除购物车成功");
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return Response.fail("移除购物车失败");
    }

    /**
     * 会员购买商品
     *
     * @param list
     * @param buyMemberPhone
     * @return
     */
    @Override
    public Response buyGoods(List<ShoppingBean> list, String buyMemberPhone) {
        try {
            if (!StringUtils.isEmpty(buyMemberPhone)) {
                MemberBean m = memberDao.findMemberByPhone(buyMemberPhone);
                if (null == m) {
                    return Response.fail("数据库没有该用户,请添加或者查证用户");
                }
                Double score = null;
                for (ShoppingBean shoppingBean : list) {
                    OrderBean o = new OrderBean();
                    GoodsBean g = goodsDao.findGoodsById(shoppingBean.getGoodsId());
                    o.setGoodsId(shoppingBean.getGoodsId());
                    o.setDiscount(shoppingBean.getDiscount());
                    o.setGoodsName(g.getGoodsName());
                    o.setFirstId(g.getFirstSortId());
                    o.setSecondId(g.getSecondSortId());
                    o.setNum(shoppingBean.getBuyNumber());
                    o.setPrice(shoppingBean.getSoldPrice());
                    o.setMemberId(m.getId());
                    o.setMemberName(m.getMemberName());
                    o.setMemberPhone(m.getMemberPhone());
                    buyDao.addOrder(o);
                    goodsDao.subtractNum(shoppingBean);
                    score = o.getDiscount() * o.getPrice() * o.getNum();
                }
                int s = score.intValue();
                memberDao.addScore(m.getId(),s);
            } else {
                for (ShoppingBean shoppingBean : list) {
                    OrderBean o = new OrderBean();
                    GoodsBean g = goodsDao.findGoodsById(shoppingBean.getGoodsId());
                    o.setGoodsName(g.getGoodsName());
                    o.setFirstId(g.getFirstSortId());
                    o.setSecondId(g.getSecondSortId());
                    o.setGoodsId(shoppingBean.getGoodsId());
                    o.setDiscount(shoppingBean.getDiscount());
                    o.setNum(shoppingBean.getBuyNumber());
                    o.setPrice(shoppingBean.getSoldPrice());
                    buyDao.addOrder(o);
                    goodsDao.subtractNum(shoppingBean);
                }
            }
            return Response.ok("购买物品成功");
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return Response.fail("购买商品失败");
    }

}
