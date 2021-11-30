package com.myr.Service.Impl;


import com.myr.Bean.Unit;
import com.myr.Bean.User;
import com.myr.Mapper.UnitMapper;
import com.myr.Mapper.UserMapper;
import com.myr.Service.UnitService;
import com.myr.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
public class UnitServiceImpl implements UnitService {
    @Resource
    UnitMapper unitMapper;

    @Override
    @Transactional
    public Integer addUnit(Unit unit) {
        Integer count = unitMapper.isexits(unit);
        if(count == 1){//已存在
            return -3;
        }else{//不存在
            return unitMapper.addUnit(unit);
        }

    }

    @Override
    public List<Unit> unit_all() {
        return unitMapper.unit_all();
    }

    @Override
    public int getCounts_page(Map<String, Object> map) {
        return unitMapper.getCounts_page(map);
    }

    @Override
    public List<Unit> Unit_page(Map<String, Object> map) {
        return unitMapper.Unit_page(map);
    }

    @Override
    public Integer delUnit(Integer fid) {
        return unitMapper.delUnit(fid);
    }

    @Override
    public Unit getUnitById(Integer fid) {
        return unitMapper.getUnitById(fid);
    }

    @Override
    @Transactional
    public Integer updateUnit(Unit unit) {
        Integer count = unitMapper.isexits(unit);
        if(count == 1){//已存在
            return -3;
        }else{//不存在
            return unitMapper.updateUnit(unit);
        }

    }

    @Override
    public Integer isexits(Unit unit) {
        return unitMapper.isexits(unit);
    }
}
