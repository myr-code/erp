package com.myr.Service.Impl;

import com.myr.Bean.SettlementMethod;
import com.myr.Mapper.SettlementMethodMapper;
import com.myr.Service.SettMethodService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class SettMethodServiceImpl implements SettMethodService {
    @Resource
    SettlementMethodMapper settlementMethodMapper;

    @Override
    @Transactional
    public Integer addSettlementMethod(SettlementMethod settlementMethod) {
        Integer count = settlementMethodMapper.isexits(settlementMethod);
        if(count == 1){//已存在
            return -3;
        }else{//不存在
            return settlementMethodMapper.addSettlementMethod(settlementMethod);
        }

    }

    @Override
    public List<SettlementMethod> settMethod_all() {
        return settlementMethodMapper.settMethod_all();
    }

    @Override
    public List<SettlementMethod> settMethod_index(String str) {
        return settlementMethodMapper.settMethod_index(str);
    }

    @Override
    public Integer delSett(Integer fid) {
        return settlementMethodMapper.delSett(fid);
    }

    @Override
    public SettlementMethod getSettById(Integer fid) {
        return settlementMethodMapper.getSettById(fid);
    }

    @Override
    @Transactional
    public Integer updateSett(SettlementMethod settlementMethod) {
        Integer count = settlementMethodMapper.isexits(settlementMethod);
        if(count == 1){//已存在
            return -3;
        }else{//不存在
            return settlementMethodMapper.updateSett(settlementMethod);
        }

    }

    @Override
    public Integer isexits(SettlementMethod settlementMethod) {
        return settlementMethodMapper.isexits(settlementMethod);
    }
}
