package com.cotton.controller;

import com.cotton.model.bean.GoodsBean;
import com.cotton.model.bean.ShoppingBean;
import com.cotton.model.common.ActionResult;
import com.cotton.model.common.Response;
import com.cotton.model.dto.ShoppingCart;
import com.cotton.service.IShoppingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/shopping")
public class ShoppingController {
    @Autowired
    private IShoppingService shoppingService;

    @RequestMapping("/addGoodsToCart")
    public ActionResult addGoodsToCart(@RequestBody List<GoodsBean> list, HttpSession session) {
        try {
            Map<Integer, ShoppingBean> map = (Map<Integer, ShoppingBean>) session.getAttribute("shopCart");
            Response response = shoppingService.buy(list, map);
            if (response.isOk()) {
                return ActionResult.success("成功", "添加购物车成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return ActionResult.fail("添加购物车失败");
    }

    @RequestMapping("/getCartFrame")
    public ModelAndView getCart(HttpSession session) {
        ModelAndView m = new ModelAndView("/goods/shopCartFrame");
        try {
            Map<Integer, ShoppingBean> map = (Map<Integer, ShoppingBean>) session.getAttribute("shopCart");
            List<ShoppingBean> list = new ArrayList<>();
            for (ShoppingBean s : map.values()) {
                list.add(s);
            }
            m.addObject("shoppingCartList", list);
            return m;
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return m;
    }

    @RequestMapping("/removeGoods")
    public ActionResult removeGoods(HttpSession session, int id) {
        try {
            Map<Integer, ShoppingBean> map = (Map<Integer, ShoppingBean>) session.getAttribute("shopCart");
            map.remove(id);
            return ActionResult.success("成功", "移除成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ActionResult.fail("移除失败");
    }

    @RequestMapping("/buyGoods")
    public ActionResult buyGoods(HttpSession session, @RequestBody List<ShoppingBean> list, @RequestBody String buyMemberPhone) {
        try {
            Response response = shoppingService.buyGoods(list, buyMemberPhone);
            if (response.isOk()) {
                Map<Integer, ShoppingBean> map = (Map<Integer, ShoppingBean>) session.getAttribute("shopCart");
                map.clear();
                return ActionResult.success("成功", "购买成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return ActionResult.fail("购买失败");
    }

    @RequestMapping("/buyGoodsWithMember")
    public ActionResult buyGoodsWithMember(HttpSession session, @RequestBody ShoppingCart cart) {
        try {
            Response response = shoppingService.buyGoods(cart.getList(), cart.getBuyMemberPhone());
            if (response.isOk()) {
                Map<Integer, ShoppingBean> map = (Map<Integer, ShoppingBean>) session.getAttribute("shopCart");
                map.clear();
                return ActionResult.success("成功", "购买成功");
            }
            return ActionResult.fail(response.getError());
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return ActionResult.fail("购买失败");
    }


}
