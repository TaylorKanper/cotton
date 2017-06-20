package com.cotton.controller;

import com.cotton.model.bean.OrderBean;
import com.cotton.model.common.ActionResult;
import com.cotton.model.common.Response;
import com.cotton.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/main")
public class HomeController {
    @Autowired
    private IOrderService orderService;

    @RequestMapping("/index")
    public ModelAndView goIndex() {
        return new ModelAndView("/index/index");
    }

    @RequestMapping("/findAllToday")
    public List<OrderBean> findAll(OrderBean bean) {
        try {
            bean.setBuyDate(DateTime.now().toDate());
            Response<List<OrderBean>> res = orderService.findAll(bean);
            if (res.isOk()) {
                return res.getResult();
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return null;
    }
}
