package com.huang.gmhm.controller;

import com.huang.gmhm.model.Hospital;
import com.huang.gmhm.service.HospitalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/service/hospital")
@Api(tags = "医院管理")
public class HospitalController {
    @Autowired
    private HospitalService hospitalService;

    @ApiOperation("管理员修改医院信息")
    @PutMapping("updateHospital/{hospName}/{id}/{hospLevel}/{hospCode}/{province}/{city}/{detailAddress}/{hospIntroduction}")
    public Map<String , Object> updateHospital(@PathVariable String hospName ,
                                               @PathVariable String hospLevel ,
                                               @PathVariable String hospCode ,
                                               @PathVariable String province ,
                                               @PathVariable String city ,
                                               @PathVariable String detailAddress ,
                                               @PathVariable String hospIntroduction ,
                                               @PathVariable String id){
        //创建更新的信息体
        Hospital hospital = new Hospital();
        hospital.setId(id);
        hospital.setHospName(hospName);
        hospital.setHospCode(hospCode);
        hospital.setHospLevel(hospLevel);
        hospital.setHospIntroduction(hospIntroduction);
        hospital.setProvinces(province);
        hospital.setCity(city);
        hospital.setDetailAddress(detailAddress);

        //看似是更新，实则是将每一次发送过来的医院对象插入数据库中，并将以往的医院对象删除
        //实际的开发项目中，一定要保存好以往的数据，不能就这样丢了！！！
        Hospital updateOne = hospitalService.updateHospital(hospital);
        Map<String , Object> map = new HashMap<>();
        map.put("hospital" , updateOne);
        map.put("success" , true);
        return map;
    }

    @ApiOperation(value = "查询医院信息")
    @GetMapping("getHospital")
    public Map<String , Object> getHospital(){
        Hospital hospital = hospitalService.getHospital();
        Map<String , Object> map = new HashMap<>();
        map.put("hospital" , hospital);
        return map;
    }
}
