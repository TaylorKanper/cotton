package com.cotton.dao;

import com.cotton.model.bean.MenuBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by hp on 2016/12/12.
 */
public interface IMenuDao {
    /**
     * 查找所有的菜单
     * @param isRui 是否王芮
     * @return 菜单集合
     */
    List<MenuBean> findAll(@Param("isRui")boolean isRui);
}
