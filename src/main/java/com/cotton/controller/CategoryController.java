package com.cotton.controller;

import com.cotton.model.bean.SortBean;
import com.cotton.model.common.ActionResult;
import com.cotton.model.common.Page;
import com.cotton.model.common.Response;
import com.cotton.model.dto.CategoryDto;
import com.cotton.service.ISortService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by hp on 2016/12/17.
 */
@Controller
@Slf4j
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private ISortService sortService;

    @RequestMapping("/index")
    public ModelAndView gotoIndex() {
        return new ModelAndView("/category/index");
    }

    @RequestMapping("/addFirstLevel")
    public ModelAndView addFirstLevel() {
        return new ModelAndView("/category/addFirstLevel");
    }

    @RequestMapping("/addSecondLevel")
    public ModelAndView addSecondLevel() {
        ModelAndView m = new ModelAndView("/category/addSecondLevel");
        Response<List<SortBean>> res = sortService.findByLevel(1);
        m.addObject("categoryList", res.getResult());
        return m;
    }

    @RequestMapping("/updateCategory")
    public ModelAndView updateCategoryPage(SortBean bean) {
        ModelAndView m = new ModelAndView("/category/updateCategory");
        Response<List<SortBean>> res = sortService.findByLevel(1);
        m.addObject("categoryList", res.getResult());
        m.addObject("category", bean);
        return m;
    }

    @RequestMapping("/add")
    @ResponseBody
    public ActionResult add(SortBean bean) {
        try {
            Response<?> response = sortService.addSort(bean);
            if (response.isOk()) {
                return ActionResult.success("成功", response.getResult());
            }
            return ActionResult.fail(response.getErrorMessage());
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ActionResult.fail("保存失败", e.getMessage());
        }

    }

    @RequestMapping("/findAll")
    @ResponseBody
    public List<SortBean> findAllFirstCategory() {
        try {
            Response<List<SortBean>> response = sortService.findByLevel(1);
            if (response.isOk()) {
                return response.getResult();
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return null;
    }

    @RequestMapping("/findAllByPage")
    @ResponseBody
    public Page<SortBean> findAllByPage(SortBean bean, int page, int rows) {
        try {
            Response<Page<SortBean>> res = sortService.findByPage(bean, page, rows);
            return res.getResult();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return null;
    }

    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public ActionResult delCategory(@RequestBody List<SortBean> list, HttpServletRequest request) {
        try {
            return ActionResult.success("成功", sortService.delSort(list));
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return ActionResult.fail("删除失败");
    }

    @RequestMapping("/update")
    @ResponseBody
    public ActionResult updateCategory(SortBean bean) {
        try {
            return ActionResult.success("修改成功", sortService.updateCategory(bean));
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return ActionResult.fail("修改失败");
    }

    @RequestMapping("/findChildren")
    @ResponseBody
    public ActionResult findChildren(Integer id) {
        try {
            Response<List<SortBean>> response = sortService.findChild(id);
            if (response.isOk()) {
                return ActionResult.success("成功", response.getResult());
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return ActionResult.fail("查找失败");
    }

    @RequestMapping("/findAllCategory")
    @ResponseBody
    public ActionResult findAllCategory() {
        try {
            Response<List<CategoryDto>> res = sortService.findCategory();
            if (res.isOk()) {
                return ActionResult.success("成功", res.getResult());
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return ActionResult.fail("查找失败");
    }
}
