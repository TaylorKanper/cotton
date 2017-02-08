package com.cotton.model.dto;

import com.cotton.model.bean.ShoppingBean;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class ShoppingCart implements Serializable{
    private List<ShoppingBean> list;
    private String buyMemberPhone;
}
