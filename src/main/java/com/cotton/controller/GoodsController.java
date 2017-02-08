package com.cotton.controller;

import com.cotton.model.bean.GoodsBean;
import com.cotton.model.common.ActionResult;
import com.cotton.model.common.Page;
import com.cotton.model.common.Response;
import com.cotton.service.IGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by hp on 2016/11/7.
 */
@Controller
@RequestMapping("/goods")
@Slf4j
public class GoodsController {

    @Autowired
    private IGoodsService goodsService;

    @RequestMapping("/manager/index")
    public ModelAndView index() {
        return new ModelAndView("/goods/index");
    }

    @RequestMapping("/buy/index")
    public ModelAndView buyIndex() {
        return new ModelAndView("/goods/buyIndex");
    }

    @RequestMapping("/addGoodsFrame")
    public ModelAndView addGoodsFrame() {
        return new ModelAndView("/goods/addGoods");
    }

    @RequestMapping("/updateGoodsFrame")
    public ModelAndView updateMemberFrame(GoodsBean goodsBean) {
        ModelAndView m = new ModelAndView("/goods/updateGoodsFrame");
        m.addObject("goods", goodsBean);
        return m;
    }

    @RequestMapping("/add")
    @ResponseBody
    public ActionResult add(GoodsBean goods) {
        try {
            Response<?> res = goodsService.add(goods);
            if (res.isOk()) {
                return ActionResult.success("添加成功", res.getResult());
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return ActionResult.fail("添加失败");
    }

    @RequestMapping("/findAllByPage")
    @ResponseBody
    public Page<GoodsBean> findByPage(GoodsBean goodsBean, int page, int rows) {
        try {
            Response<Page<GoodsBean>> response = goodsService.findByPage(goodsBean, page, rows);
            if (response.isOk()) {
                return response.getResult();
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return null;
    }

    @RequestMapping("/del")
    @ResponseBody
    public ActionResult delGoods(@RequestBody Integer[] ids) {
        try {
            Response<Integer> res = goodsService.delGoods(ids);
            if (res.isOk()) {
                return ActionResult.success("删除成功", "删除成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return ActionResult.fail("删除失败");
    }

    @RequestMapping("/updateGoods")
    @ResponseBody
    public ActionResult updateGoods(GoodsBean goodsBean) {
        try {
            Response<?> res = goodsService.updateGoods(goodsBean);
            if (res.isOk()) {
                return ActionResult.success("修改成功", res.getResult());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ActionResult.fail("修改失败");
    }
    @RequestMapping("/updateNumber")
    @ResponseBody
    public ActionResult updateNumber(GoodsBean goodsBean){
        try {
            Response<?> res = goodsService.updateNumber(goodsBean);
            if (res.isOk()) {
                return ActionResult.success("成功", res.getResult());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ActionResult.fail("修改失败");
    }

}
