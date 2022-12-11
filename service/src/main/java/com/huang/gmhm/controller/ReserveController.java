package com.huang.gmhm.controller;

import com.huang.gmhm.model.Department;
import com.huang.gmhm.model.Reserve;
import com.huang.gmhm.model.User;
import com.huang.gmhm.service.DepartmentService;
import com.huang.gmhm.service.ReserveService;
import com.huang.gmhm.service.UserService;
import com.huang.gmhm.utils.DateUtil;
import com.huang.gmhm.vo.ReserveVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/service/reserve")
@Api(tags = "预约管理")
public class ReserveController {
    @Autowired
    private ReserveService reserveService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "管理员根据日期查看预约列表")
    @GetMapping("adminGetReserveList/{date}")
    public Map<String, Object> adminGetReserveList(@PathVariable String date) {
        //1、根据日期查询出所有预约信息
        List<Reserve> reserveList = reserveService.getReserveListByDate(DateUtil.timeFormat(date));

        //2、分别根据ID号查询返回前端所需要的信息
        List<ReserveVo> reserveVoList = new ArrayList<>();
        for (Reserve reserve : reserveList) {
            //创建返回的单个对象
            ReserveVo reserveVo = new ReserveVo();
            //查询预约单号中所需的科室信息
            Department department = departmentService.getDepartmentById(reserve.getDeptId());
            reserveVo.setDeptName(department.getDeptName());
            reserveVo.setDoctorName(department.getDoctorName());

            //查询预约单号中所需的用户信息
            User user = userService.getUserById(reserve.getUserId());
            reserveVo.setUserName(user.getUserName());
            reserveVo.setUserPhoneNumber(user.getPhoneNumber());

            reserveVo.setStatus(reserve.getStatus());
            reserveVo.setReserveId(reserve.getId());

            reserveVoList.add(reserveVo);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("reserveVoList", reserveVoList);
        return map;
    }

    @ApiOperation(value = "预约科室")
    @PostMapping("createReserve/{userId}/{departmentId}/{reserveDate}")
    public Map<String, Object> createReserve(@PathVariable String userId, @PathVariable String departmentId, @PathVariable String reserveDate) {
        Map<String, Object> map = new HashMap<>();

        //创建消息体
        Reserve reserve = new Reserve();
        reserve.setReserveDate(DateUtil.timeFormat(reserveDate));
        reserve.setDeptId(departmentId);
        reserve.setUserId(userId);
        reserve.setId(UUID.randomUUID().toString());

        //由于我认为在service中调用其他service不太妥当
        //应该得在controller层中去调用其他的service
        //这样的关系才不会那么混乱
        //虽然这样做会比较冗余，代码量比较大，但是层级关系更清楚明确

        //1、查看当天该科室已经预约多少人
        Integer count = reserveService.getCountByDateAndDeptId(reserve.getReserveDate(), reserve.getDeptId());
        //2、若count==科室每日可预约数量 即满人，则返回false ， 若否则添加进记录且返回true
        Department department = departmentService.getDepartmentById(reserve.getDeptId());
        if (!count.equals(department.getDailyAvailableReserve())) {
            boolean success = reserveService.createReserve(reserve);
            if (success) {
                map.put("success", true);
            }
        } else {
            map.put("success", false);
        }

        return map;
    }

    @ApiOperation(value = "查询用户已预约的科室")
    @GetMapping("getReserveList/{userId}")
    public Map<String, Object> getReserveList(@PathVariable String userId) {
        List<Reserve> reserveList = reserveService.getReserveListByUserId(userId);
        Map<String, Object> map = new HashMap<>();

        List<ReserveVo> reserveVoList = new ArrayList<>();
        for (Reserve reserve : reserveList) {
            ReserveVo reserveVo = new ReserveVo();
            //设置单号中所需要的信息
            reserveVo.setReserveId(reserve.getId());

            User user = userService.getUserById(reserve.getUserId());
            Department department = departmentService.getDepartmentById(reserve.getDeptId());

            reserveVo.setUserPhoneNumber(user.getPhoneNumber());
            reserveVo.setUserName(user.getUserName());
            reserveVo.setDoctorName(department.getDoctorName());
            reserveVo.setDeptName(department.getDeptName());
            reserveVo.setStatus(reserve.getStatus());

            reserveVoList.add(reserveVo);
        }

        map.put("reserveVoList", reserveVoList);
        return map;
    }

    @ApiOperation(value = "取消预约")
    @DeleteMapping("cancelReserve/{reserveId}")
    public Map<String , Object> cancelReserve(@PathVariable String reserveId){
        Map<String , Object> map = new HashMap<>();
        map.put("success" , false);
        //先查询预约订单是否已完成
        if(reserveService.getById(reserveId).getStatus()==1){
            //未完成的再进行删除
            boolean success = reserveService.removeById(reserveId);
            map.put("success" , success);
        }
        return map;
    }

    @ApiOperation(value = "更改预约状态")
    @PutMapping("setReserveStatus/{reserveId}/{status}")
    public Map<String , Object> setReserveStatus(@PathVariable String reserveId , @PathVariable Integer status){
        Map<String , Object> map = new HashMap<>();
        boolean success = reserveService.updateStatus(reserveId , status);
        map.put("success" , success);
        return map;
    }

}
