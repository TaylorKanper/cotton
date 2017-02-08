package com.cotton.service.impl;

import com.cotton.model.common.Page;
import com.cotton.model.common.Response;

import java.util.List;

/**
 * Created by hp on 2016/12/14.
 */
public class BaseServiceImpl<T> {
    /**
     * 分页查找的超类实现方法
     *
     * @param list  分页结果集
     * @param total 总数
     * @return
     */
    protected Response<Page<T>> findByPage(List<T> list, int total) {
        Page<T> p = new Page<>();
        p.setRows(list);
        p.setTotal(total);
        return Response.ok(p);
    }
}
