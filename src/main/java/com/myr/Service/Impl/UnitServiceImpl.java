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
    public List<Unit> Unit_page(String str) {
        return unitMapper.Unit_page(str);
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
