package com.myr.Service.Impl;

import com.myr.Bean.Department;
import com.myr.Mapper.DepartmentMapper;
import com.myr.Service.DepartmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Resource
    DepartmentMapper departmentMapper;

    @Override
    @Transactional
    public Integer addDepartment(Department department) {
        Integer count = departmentMapper.isexits(department);
        if(count == 1){//已存在
            return -3;
        }else{//不存在
            return departmentMapper.addDepartment(department);
        }

    }

    @Override
    public List<Department> depart_all() {
        return departmentMapper.depart_all();
    }

    @Override
    public Integer delDepa(int fid) {
        return departmentMapper.delDepa(fid);
    }

    @Override
    public Department getDepaById(int fid) {
        return departmentMapper.getDepaById(fid);
    }

    @Override
    @Transactional
    public Integer updateDepa(Department department) {
        Integer count = departmentMapper.isexits(department);
        if(count == 1){//已存在
            return -3;
        }else{//不存在
            return departmentMapper.updateDepa(department);
        }
    }

    @Override
    public Integer isexits(Department department) {
        return departmentMapper.isexits(department);
    }
}
