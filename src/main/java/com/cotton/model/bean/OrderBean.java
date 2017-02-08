package com.cotton.model.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by hp on 2016/12/11.
 */
@Data
@Alias("order")
public class OrderBean implements Serializable {
    private Integer id;
    private Integer goodsId;
    private Integer firstId;
    private Integer secondId;
    private String goodsName;
    private Integer memberId;
    private String memberName;
    private String memberPhone;
    private Integer num;
    private Double price;
    private Double discount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date buyDate;
}
