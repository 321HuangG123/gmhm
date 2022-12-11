package com.huang.gmhm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huang.gmhm.mapper.HospitalMapper;
import com.huang.gmhm.model.Hospital;
import com.huang.gmhm.service.HospitalService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class HospitalServiceImpl extends ServiceImpl<HospitalMapper , Hospital> implements HospitalService {

    /**
     * 插入医院信息体，并将以往的逻辑删除
     * @param hospital 信息体
     * @return 插入的信息体
     */
    @Override
    public Hospital updateHospital(Hospital hospital) {
        //先将原先的删除掉
        baseMapper.deleteById(hospital.getId());
        //设置新的id并且插入数据库中
        hospital.setId(UUID.randomUUID().toString());
        baseMapper.insert(hospital);
        return hospital;
    }

    /**
     * 查询医院信息
     * 由于以往更新的信息都被逻辑删除了，所有直接查询为删除的即可
     * @return hospital
     */
    @Override
    public Hospital getHospital() {
        Hospital hospital = baseMapper.selectOne(null);
        return hospital;
    }
}
