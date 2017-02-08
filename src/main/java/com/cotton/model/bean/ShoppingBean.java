package com.cotton.model.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class ShoppingBean implements Serializable {
    private Integer goodsId;
    private Integer buyNumber;
    private GoodsBean goodsBean;
    private Double discount;
    private Double soldPrice;
    private String goodsName;
    private String firstSortName;
    private String secondSortName;
    private String description;
}
