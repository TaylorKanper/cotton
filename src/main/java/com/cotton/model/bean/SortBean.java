package com.cotton.model.bean;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by hp on 2016/12/11.
 */
@Data
@Alias("sort")
public class SortBean implements Serializable {
    private int id;
    private String sortName;
    private String description;
    private Date createDate;
    private int status;
    private int level;
    private int fatherId;
    private String fatherName;
}
