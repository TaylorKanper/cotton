package com.cotton.model.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by hp 2016/11/7.
 */
@Data
@Alias("goods")
public class GoodsBean implements Serializable {
    private Integer id;
    private String goodsName;
    private Integer number;
    private Double price;
    private String description;
    private Double cost;
    private Integer firstSortId;
    private String firstSortName;
    private String secondSortName;
    private Integer secondSortId;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date recordDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date updateDate;
    private Integer status;
    private String merchant;
    private Integer lowPrice;
    private Integer highPrice;
    private Date startTime;
    private Date endTime;
}
