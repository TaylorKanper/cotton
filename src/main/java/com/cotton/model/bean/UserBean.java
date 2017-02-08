package com.cotton.model.bean;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by hp on 2016/12/13.
 */
@Data
@Alias("user")
public class UserBean implements Serializable{
    private int id;
    private String userName;
    private String loginName;
    private String passWord;
    private Date entryDate;
    private String phone;
    private int status;
    private String remark;
}
