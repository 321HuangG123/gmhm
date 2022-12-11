package com.huang.gmhm.controller;

import com.huang.gmhm.model.Department;
import com.huang.gmhm.service.DepartmentService;
import com.huang.gmhm.service.ReserveService;
import com.huang.gmhm.utils.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/service/department")
@Api(tags = "科室管理")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private ReserveService reserveService;

    @ApiOperation(value = "获取科室列表")
    @GetMapping("getDepartmentList/{date}")
    public Map<String , Object> getDepartmentList(@PathVariable String date){
        Map<String , Object> map = new HashMap<>();
        List<Department> departmentList = departmentService.getDepartmentList();

        if(date!=null){
            for (Department department : departmentList){
                //将每个科室的可预约数减去已预约的数量
                Integer count = reserveService.getCountByDateAndDeptId(DateUtil.timeFormat(date), department.getId());
                department.setDailyAvailableReserve(department.getDailyAvailableReserve() - count);
            }
        }

        map.put("departmentList" , departmentList);
        return map;
    }

    @ApiOperation(value = "删除科室")
    @DeleteMapping("deleteDepartment/{departmentId}")
    public Map<String , Object> deleteDepartment(@PathVariable String departmentId){
        Map<String , Object> map = new HashMap<>();
        //先将科室的预约单号进行删除
        reserveService.deleteReserveByDeptId(departmentId);
        //再将科室进行删除
        boolean success = departmentService.deleteDepartmentById(departmentId);
        map.put("success" , success);
        return map;
    }

    @ApiOperation(value = "添加科室")
    @PostMapping("createDepartment/{deptName}/{doctorName}/{dailyAvailableReserve}/{mainSymptoms}")
    public Map<String , Object> createDepartment(@PathVariable String deptName ,
                                                 @PathVariable String doctorName ,
                                                 @PathVariable Integer dailyAvailableReserve ,
                                                 @PathVariable String mainSymptoms){
        Map<String , Object> map = new HashMap<>();

        Department department = new Department();
        department.setDeptName(deptName);
        department.setDoctorName(doctorName);
        department.setDailyAvailableReserve(dailyAvailableReserve);
        department.setMainSymptoms(mainSymptoms);
        department.setId(UUID.randomUUID().toString());

        int i = departmentService.insertDepartment(department);
        map.put("success" , i != 0);
        return map;
    }
}
