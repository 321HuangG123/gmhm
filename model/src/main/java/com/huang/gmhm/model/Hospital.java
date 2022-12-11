package com.huang.gmhm.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Hospital")
@TableName("hospital")
public class Hospital {

    @ApiModelProperty(value = "医院id")
    @TableField("id")
    private String id;

    @ApiModelProperty(value = "医院名")
    @TableField("hosp_name")
    private String hospName;

    @ApiModelProperty(value = "医院等级")
    @TableField("hosp_level")
    private String hospLevel;

    @ApiModelProperty(value = "医院编码")
    @TableField("hosp_code")
    private String hospCode;

    @ApiModelProperty(value = "所属省份")
    @TableField("provinces")
    private String provinces;

    @ApiModelProperty(value = "所在城市及区")
    @TableField("city")
    private String city;

    @ApiModelProperty(value = "详细地址")
    @TableField("detail_address")
    private String detailAddress;

    @ApiModelProperty(value = "医院简介")
    @TableField("hosp_introduction")
    private String hospIntroduction;

    @TableLogic
    private Integer isDeleted;

//    create table hospital(
//      id varchar(36) primary key ,
//      hosp_name varchar(10) not null ,
//      hosp_level varchar(10) not null ,
//      hosp_code varchar(6) not null ,
//      provinces varchar(10) not null ,
//      city varchar(10) not null ,
//      detail_address varchar(255) not null ,
//      hosp_introduction varchar(255) not null ,
//      create_time datetime not null default current_timestamp ,
//      update_time datetime not null default current_timestamp on update current_timestamp ,
//      is_deleted int not null default 0
//    );
}
