package com.myr.Service.Impl;

import com.myr.Bean.Item;
import com.myr.Bean.Staff;
import com.myr.Mapper.StaffMapper;
import com.myr.Service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
public class StaffServiceImpl implements StaffService {
    @Resource
    StaffMapper staffMapper;

    @Override
    @Transactional
    public Integer addStaff(Staff staff) {
        Integer count = staffMapper.isexits(staff);
        if(count == 1){//已存在
            return -3;
        }else{//不存在
            return staffMapper.addStaff(staff);
        }

    }

    @Override
    public List<Staff> staff_all() {
        return staffMapper.staff_all();
    }

    @Override
    public int getCounts_page(Map<String, Object> map) {
        return staffMapper.getCounts_page(map);
    }

    @Override
    public List<Staff> Staff_page(Map<String, Object> map) {
        return staffMapper.Staff_page(map);
    }

    @Override
    public Integer delStaff(Integer fid) {
        return staffMapper.delStaff(fid);
    }

    @Override
    public Staff getStaffById(Integer fid) {
        return staffMapper.getStaffById(fid);
    }

    @Override
    @Transactional
    public Integer updateStaff(Staff staff) {
        Integer count = staffMapper.isexits(staff);
        if(count == 1){//已存在
            return -3;
        }else{//不存在
            return staffMapper.updateStaff(staff);
        }

    }

    @Override
    public Integer isexits(Staff staff) {
        return staffMapper.isexits(staff);
    }
}
