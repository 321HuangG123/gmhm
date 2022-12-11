package com.huang.gmhm.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Department")
@TableName("department")
public class Department {

    @ApiModelProperty(value = "科室id")
    @TableField("id")
    private String id;

    @ApiModelProperty(value = "科室")
    @TableField("dept_name")
    private String deptName;

    @ApiModelProperty(value = "医师名")
    @TableField("doctor_name")
    private String doctorName;

    @ApiModelProperty(value = "每日可预约数")
    @TableField("daily_available_reserve")
    private Integer dailyAvailableReserve;

    @ApiModelProperty(value = "主治症状")
    @TableField("main_symptoms")
    private String mainSymptoms;

    @TableLogic
    private Integer isDeleted;

//    create table department(
//        id varchar(36) primary key ,
//        dept_name varchar(10) not null ,
//        doctor_name varchar(8) not null ,
//        daily_available_reserve int not null ,
//        main_symptoms varchar(255) not null ,
//        create_time datetime not null default current_timestamp ,
//        update_time datetime not null default current_timestamp on update current_timestamp ,
//        is_deleted int not null default 0
//    );

}
