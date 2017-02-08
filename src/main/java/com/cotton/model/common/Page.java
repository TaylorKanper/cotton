package com.cotton.model.common;

import lombok.Data;

import java.util.List;

/**
 * Created by hp on 2016/12/14.
 */
@Data
public class Page<T> {
    private int total;
    private List<T> rows;
}
