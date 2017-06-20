package com.cotton.dao;

import com.cotton.model.bean.GoodsBean;
import com.cotton.model.bean.OrderBean;
import com.cotton.model.bean.ShoppingBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by hp on 2016/11/7.
 */
public interface IGoodsDao {
    /**
     * 添加商品
     *
     * @param bean
     */
    int add(GoodsBean bean);

    /**
     * 分页查找
     *
     * @param bean
     * @param start
     * @param end
     * @return
     */
    List<GoodsBean> findByPage(@Param("goods") GoodsBean bean, @Param("start") int start, int end);

    /**
     * 查找总条数
     *
     * @param goods
     * @return
     */
    int findTotalCount(@Param("goods") GoodsBean goods);

    /**
     * 删除ids
     *
     * @param ids
     * @return
     */
    int delGoods(@Param("ids") Integer[] ids);

    /**
     * 修改商品
     *
     * @param goods
     * @return
     */
    int updateGoods(@Param("goods") GoodsBean goods);

    /**
     * 根据id查找商品
     *
     * @param id
     * @return
     */
    GoodsBean findGoodsById(@Param("id") int id);

    /**
     * 修改商品数量
     *
     * @param goods
     * @return
     */
    int updateNumber(@Param("goods") GoodsBean goods);

    /**
     * 查找新增商品是否在库中
     *
     * @param bean
     * @return
     */
    GoodsBean findExistGoods(@Param("bean") GoodsBean bean);

    /**
     * 集合内所有的商品数量减1
     *
     * @param list
     * @return
     */
    int subtractOne(@Param("list") List<GoodsBean> list);

    /**
     * 把数据库里数量小于0的数据状态设置为-1
     */
    void checkNumber();

    /**
     * 把数据库数量大于0的数据状态设置为1
     */
    void checKNumberStatus();

    /**
     * 商品减少相应的数量
     * @param bean
     * @return
     */
    int subtractNum(ShoppingBean bean);
}

