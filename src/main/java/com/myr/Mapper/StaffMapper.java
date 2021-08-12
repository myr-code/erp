package com.myr.Mapper;

import com.myr.Bean.Customer;
import com.myr.Bean.Item;
import com.myr.Bean.ItemType;
import com.myr.Bean.Staff;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StaffMapper {
    //01-添加职员
    Integer addStaff(Staff staff);

    //02-全部职员信息
    List<Staff> staff_all();

    //序时簿
    List<Staff> Staff_page(@Param("str") String str);

    //04-删除
    Integer delStaff(Integer fid);

    //获取一个
    Staff getStaffById(Integer fid);

    //更新
    Integer updateStaff(Staff staff);

    //是否存在
    Integer isexits(Staff staff);
}
