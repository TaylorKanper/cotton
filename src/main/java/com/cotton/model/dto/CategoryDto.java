package com.cotton.model.dto;

import com.cotton.model.bean.SortBean;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


/**
 * 将所有的数据库类目查找出来,并放在该实体下
 */
@Data
public class CategoryDto implements Serializable {
    private Integer id;
    private String categoryName;
    private List<SortBean> children;
}
