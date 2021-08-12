package com.myr.Service;

import com.myr.Bean.CustType;
import com.myr.Bean.Department;

import java.util.List;

public interface DepartmentService {
    Integer addDepartment(Department department);

    List<Department> depart_all();

    Integer delDepa(int fid);

    Department getDepaById(int fid);

    Integer updateDepa(Department department);

    Integer isexits(Department department);
}
