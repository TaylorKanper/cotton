package com.cotton.model.bean;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * Created by hp on 2016/12/12.
 */
@Data
@Alias("menu")
public class MenuBean implements Serializable {
    private int id;
    private String menuName;
    private String menuIcon;
    private String menuUrl;
    private int status;
    private String description;
    private int visible;
}
