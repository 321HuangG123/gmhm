package com.huang.gmhm.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "User")
@TableName("user")
public class User {

    @ApiModelProperty(value = "用户id")
    @TableField("id")
    private String id;

    @ApiModelProperty(value = "用户姓名")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty(value = "手机号码")
    @TableField("phone_number")
    private String phoneNumber;

    @ApiModelProperty(value = "登录密码")
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "性别 0:女  1:男")
    @TableField("sex")
    private Integer sex;

    @ApiModelProperty(value = "身份 0:普通用户  1:管理员")
    @TableField("identity")
    private Integer identity;

    @TableLogic
    private Integer isDeleted;

//    create table user(
//      id varchar(36) primary key ,
//      user_name varchar(8) not null ,
//      phone_number varchar(11) not null ,
//      password varchar(20) not null ,
//      sex int not null default 0 ,
//      identity int not null default 0 ,
//      create_time datetime not null default current_timestamp ,
//      update_time datetime not null default current_timestamp on update current_timestamp ,
//      is_deleted int not null default 0
//    );
}
