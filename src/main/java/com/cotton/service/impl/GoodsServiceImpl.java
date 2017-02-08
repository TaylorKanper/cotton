package com.cotton.service.impl;

import com.cotton.dao.IGoodsDao;
import com.cotton.model.bean.GoodsBean;
import com.cotton.model.common.Page;
import com.cotton.model.common.Response;
import com.cotton.service.IGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class GoodsServiceImpl extends BaseServiceImpl<GoodsBean> implements IGoodsService {

    @Resource
    private IGoodsDao dao;


    /**
     * 添加商品
     *
     * @param bean
     * @return
     */
    @Override
    public Response<?> add(GoodsBean bean) {
        try {
            GoodsBean goodsBean = dao.findExistGoods(bean);
            if (goodsBean != null) {
                if (goodsBean.getStatus() == 1) {
                    goodsBean.setNumber(goodsBean.getNumber() + bean.getNumber());
                } else {
                    goodsBean.setNumber(bean.getNumber());
                    goodsBean.setStatus(1);
                }
                if (dao.updateGoods(goodsBean) > 0) {
                    dao.checKNumberStatus();
                    return Response.ok("添加已有商品" + bean.getGoodsName() + "成功");
                }
            } else {
                bean.setStatus(1);
                int i = dao.add(bean);
                if (i > 0) {
                    return Response.ok("添加" + bean.getGoodsName() + "成功");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return Response.fail("添加失败");
    }

    /**
     * 分页查询商品
     *
     * @param bean
     * @param page
     * @param rows
     * @return
     */
    @Override
    public Response<Page<GoodsBean>> findByPage(GoodsBean bean, int page, int rows) {
        try {
            List<GoodsBean> l = dao.findByPage(bean, (page - 1) * rows, rows);
            int total = dao.findTotalCount(bean);
            return super.findByPage(l, total);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return Response.fail("未查到结果集");
    }

    /**
     * 批量删除商品
     *
     * @param ids
     * @return
     */
    @Override
    public Response<Integer> delGoods(Integer[] ids) {
        try {
            int i = dao.delGoods(ids);
            if (i > 0) {
                return Response.ok(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return Response.fail("删除失败");
    }

    /**
     * 修改商品
     *
     * @param goodsBean
     * @return
     */
    @Override
    public Response<?> updateGoods(GoodsBean goodsBean) {
        try {
            int i = dao.updateGoods(goodsBean);
            if (i > 0) {
                return Response.ok("修改成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return Response.fail("修改失败");
    }

    /**
     * 修改商品数量
     *
     * @param goodsBean
     * @return
     */
    @Override
    public Response<?> updateNumber(GoodsBean goodsBean) {
        try {
            int i = dao.updateNumber(goodsBean);
            if (i > 0) {
                return Response.ok("修改成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.fail("修改成功");
    }
}
