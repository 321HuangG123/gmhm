package com.huang.gmhm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.gmhm.model.Department;

import java.util.List;

public interface DepartmentService extends IService<Department> {
    //根据Id查询科室信息
    Department getDepartmentById(String deptId);

    //获取科室列表
    List<Department> getDepartmentList();

    //根据Id删除科室
    boolean deleteDepartmentById(String departmentId);

    //添加科室
    int insertDepartment(Department department);
}
