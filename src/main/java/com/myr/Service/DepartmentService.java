package com.myr.Service;

import com.myr.Bean.CustType;
import com.myr.Bean.Department;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Integer addDepartment(Department department);

    List<Department> depart_all();

    int getCounts_page(Map<String,Object> map);

    List<Department> Depat_page(Map<String,Object> map);

    Integer delDepa(int fid);

    Department getDepaById(int fid);

    Integer updateDepa(Department department);

    Integer isexits(Department department);
}
