package com.myr.Mapper;

import com.myr.Bean.CustType;
import com.myr.Bean.Customer;
import com.myr.Bean.Department;
import com.myr.Bean.Store;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DepartmentMapper {
    //01-添加
    Integer addDepartment(Department department);

    //查询搜友部门信息
    List<Department> depart_all();

    //删除
    Integer delDepa(int fid);

    //获取一个
    Department getDepaById(int fid);

    //更新
    Integer updateDepa(Department department);

    //是否存在
    Integer isexits(Department department);
}
