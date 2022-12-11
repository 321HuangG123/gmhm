package com.huang.gmhm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huang.gmhm.mapper.ReserveMapper;
import com.huang.gmhm.model.Reserve;
import com.huang.gmhm.model.User;
import com.huang.gmhm.service.ReserveService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReserveServiceImpl extends ServiceImpl<ReserveMapper, Reserve> implements ReserveService {


    /**
     * 根据日期查询预约列表
     *
     * @param date 预约的时间
     * @return list 当日的预约列表
     */
    @Override
    public List<Reserve> getReserveListByDate(Date date) {
        QueryWrapper<Reserve> wrapper = new QueryWrapper<>();
        wrapper.eq("reserve_date", date);
        List<Reserve> reserveList = baseMapper.selectList(wrapper);
        return reserveList;
    }

    /**
     * 添加预约
     *
     * @param reserve 预约信息体
     * @return success
     */
    @Override
    public boolean createReserve(Reserve reserve) {
        int i = baseMapper.insert(reserve);
        return i != 0;
    }

    /**
     * 通过日期 和 科室Id号获取数量
     *
     * @param reserveDate 日期
     * @param deptId      科室ID号
     * @return count
     */
    @Override
    public Integer getCountByDateAndDeptId(Date reserveDate, String deptId) {
        QueryWrapper<Reserve> wrapper = new QueryWrapper<>();
        wrapper.eq("reserve_date", reserveDate);
        wrapper.eq("dept_id", deptId);
        Integer count = baseMapper.selectCount(wrapper);
        return count;
    }

    /**
     * 根据用户Id查询已预约的预约单号信息
     *
     * @param userId 用户Id
     * @return list 用户已预约的单号列表
     */
    @Override
    public List<Reserve> getReserveListByUserId(String userId) {
        QueryWrapper<Reserve> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        List<Reserve> reserveList = baseMapper.selectList(wrapper);
        return reserveList;
    }

    /**
     * 更改预约状态
     * @param reserveId 预约单号Id
     * @param status    预约的状态
     * @return
     */
    @Override
    public boolean updateStatus(String reserveId, Integer status) {
        Reserve reserve = new Reserve();
        reserve.setId(reserveId);
        reserve.setStatus(status == 1 ? 0 : 1);
        int i = baseMapper.updateById(reserve);
        return i!=0;
    }

    /**
     * 更新UserId
     * @param preUserId 原先的userId
     * @param newUserId 后改的userId
     */
    @Override
    public void updateUserId(String preUserId , String newUserId) {
        //将原先的userId号改为新的userId号
        QueryWrapper<Reserve> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id" , preUserId);
        Reserve entity = new Reserve();
        entity.setUserId(newUserId);
        int update = baseMapper.update(entity, wrapper);
    }

    /**
     * 根据UserId删除相对应的订单
     * @param userId 用户Id号
     */
    @Override
    public void deleteReserveByUserId(String userId) {
        QueryWrapper<Reserve> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id" , userId);
        baseMapper.delete(wrapper);
    }

    /**
     * 根据deptId进行删除相应的订单
     * @param departmentId 科室Id
     */
    @Override
    public void deleteReserveByDeptId(String departmentId) {
        QueryWrapper<Reserve> wrapper = new QueryWrapper<>();
        wrapper.eq("dept_id" , departmentId);
        baseMapper.delete(wrapper);
    }


}
