package com.huang.gmhm.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Reserve")
public class ReserveVo {

    @ApiModelProperty("预约单号ID")
    private String reserveId;

    @ApiModelProperty(value = "科室名称")
    private String deptName;

    @ApiModelProperty(value = "科室的医师名")
    private String doctorName;

    @ApiModelProperty(value = "用户姓名")
    private String userName;

    @ApiModelProperty(value = "用户手机号码")
    private String userPhoneNumber;

    @ApiModelProperty(value = "预约状态，完成:0  未完成:1")
    private Integer status;
}
