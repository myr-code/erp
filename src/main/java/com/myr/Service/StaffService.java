package com.myr.Service;

import com.myr.Bean.Item;
import com.myr.Bean.SettlementMethod;
import com.myr.Bean.Staff;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StaffService {
    Integer addStaff(Staff staff);

    List<Staff> staff_all();

    int getCounts_page(Map<String,Object> map);

    List<Staff> Staff_page(Map<String,Object> map);

    Integer delStaff(Integer fid);

    Staff getStaffById(Integer fid);

    Integer updateStaff(Staff staff);

    Integer isexits(Staff staff);
}
