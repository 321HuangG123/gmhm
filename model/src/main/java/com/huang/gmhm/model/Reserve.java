package com.huang.gmhm.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(description = "Reserve")
@TableName("reserve")
public class Reserve {

    @ApiModelProperty(value = "预约单号id")
    @TableField("id")
    private String id;

    @ApiModelProperty(value = "科室的id")
    @TableField("dept_id")
    private String deptId;

    @ApiModelProperty(value = "用户的id")
    @TableField("user_id")
    private String userId;

    @ApiModelProperty(value = "预约的日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @TableField("reserve_date")
    private Date reserveDate;

    @ApiModelProperty(value = "预约状态，完成:0  未完成:1")
    @TableField("status")
    private Integer status;

    @TableLogic
    private int isDeleted ;

//    create table reserve(
//      id varchar(36) primary key ,
//      dept_id varchar(36) not null ,
//      user_id varchar(36) not null ,
//      reserve_date date not null,
//      status int not null default 1 ,
//      create_time datetime not null default current_timestamp ,
//      update_time datetime not null default current_timestamp on update current_timestamp ,
//      is_deleted int not null default 0 ,
//      foreign key (dept_id) references department(id) ,
//      foreign key (user_id) references user(id)
//    );
}
