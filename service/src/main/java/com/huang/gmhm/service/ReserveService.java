package com.huang.gmhm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.gmhm.model.Reserve;
import com.huang.gmhm.model.User;

import java.util.Date;
import java.util.List;

public interface ReserveService extends IService<Reserve> {
    //根据日期查询预约列表
    List<Reserve> getReserveListByDate(Date date);

    //添加预约
    boolean createReserve(Reserve reserve);

    //通过日期 和 科室Id号查询预约列表
    Integer getCountByDateAndDeptId(Date reserveDate, String deptId);

    //根据用户Id查询已预约的预约单号信息
    List<Reserve> getReserveListByUserId(String userId);

    //更改预约状态
    boolean updateStatus(String reserveId, Integer status);

    //更新UserId
    void updateUserId(String preUserId , String newUserId);

    //根据用户Id删除预约订单
    void deleteReserveByUserId(String userId);

    //根据科室编号删除预约订单
    void deleteReserveByDeptId(String departmentId);
}
