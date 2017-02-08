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
@Alias("member")
public class MemberBean implements Serializable {
    private Integer id;
    private String memberName;
    private String memberPhone;
    private Integer memberScore;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date memberBirthday;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date memberCreate;
    private Integer chance;
    private String description;
    private String nickName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date updateTime;
    private Integer status;
    private Integer lowScore;
    private Integer highScore;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date startTime;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date endTime;
}
