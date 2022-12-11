package com.huang.gmhm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huang.gmhm.mapper.DepartmentMapper;
import com.huang.gmhm.model.Department;
import com.huang.gmhm.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper , Department> implements DepartmentService {
    /**
     * 根据ID查询科室信息
     * @param deptId 科室ID号
     * @return department 查询出的科室
     */
    @Override
    public Department getDepartmentById(String deptId) {
        Department department = baseMapper.selectById(deptId);
        return department;
    }

    /**
     * 获取科室列表
     * @return List  科室列表
     */
    @Override
    public List<Department> getDepartmentList() {
        List<Department> departmentList = baseMapper.selectList(null);
        return departmentList;
    }

    /**
     * 根据Id删除科室
     * @param departmentId 科室Id
     * @return success     删除成功标志
     */
    @Override
    public boolean deleteDepartmentById(String departmentId) {
        int i = baseMapper.deleteById(departmentId);
        return i != 0;
    }

    /**
     * 添加科室
     * @param department 科室信息体
     * @return  i        添加成功的记录数
     */
    @Override
    public int insertDepartment(Department department) {
        int i = baseMapper.insert(department);
        return i;
    }
}
