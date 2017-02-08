package com.cotton.controller;

import com.cotton.model.bean.GoodsBean;
import com.cotton.model.bean.OrderBean;
import com.cotton.model.common.ActionResult;
import com.cotton.model.common.Response;
import com.cotton.service.IBuyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/goods/buy")
public class BuyGoodsController {
    @Autowired
    private IBuyService buyService;

    @RequestMapping("/buyGoods")
    public ActionResult buyGoods(@RequestBody List<GoodsBean> list) {
        try {
            Response response = buyService.addOrder(list);
            if (response.isOk()) {
                return ActionResult.success("成功", response.getResult());
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return ActionResult.fail("购买失败");
    }

    @RequestMapping("/buyOne")
    public ActionResult buyOneGoods(Integer id) {
        try {
            Response response = buyService.addOneOrder(id);
            if (response.isOk()) {
                return ActionResult.success("成功", response.getResult());
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return ActionResult.fail("购买失败");
    }

    @RequestMapping("/multiBuy")
    public ActionResult multiBuyGoods(GoodsBean goodsBean) {
        try {
            Response response = buyService.multiBuyGoods(goodsBean);
            if (response.isOk()) {
                return ActionResult.success("成功", "购买成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return ActionResult.fail("购买失败");
    }

}
