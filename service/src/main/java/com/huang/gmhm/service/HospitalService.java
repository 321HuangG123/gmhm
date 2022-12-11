package com.huang.gmhm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.gmhm.model.Hospital;

public interface HospitalService extends IService<Hospital> {

    //插入医院信息体，并将以往的逻辑删除
    Hospital updateHospital(Hospital hospital);

    //查询医院信息
    Hospital getHospital();
}
