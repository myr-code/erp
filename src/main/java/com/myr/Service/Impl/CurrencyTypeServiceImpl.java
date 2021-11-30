package com.myr.Service.Impl;

import com.myr.Bean.CurrencyType;
import com.myr.Bean.ItemType;
import com.myr.Mapper.CurrencyTypeMapper;
import com.myr.Service.CurrencyTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
public class CurrencyTypeServiceImpl implements CurrencyTypeService {
    @Resource
    CurrencyTypeMapper currencyTypeMapper;

    @Override
    @Transactional
    public Integer add_CurrencyType(CurrencyType currencyType) {
        Integer count = currencyTypeMapper.isexits(currencyType);
        if(count == 1){//已存在
            return -3;
        }else{//不存在
            return currencyTypeMapper.add_CurrencyType(currencyType);
        }

    }

    @Override
    public int getCounts_page(Map<String, Object> map) {
        return currencyTypeMapper.getCounts_page(map);
    }

    @Override
    public List<CurrencyType> CurrencyType_page(Map<String, Object> map) {
        return currencyTypeMapper.CurrencyType_page(map);
    }

    @Override
    public List<CurrencyType> CurrencyType_all() {
        return currencyTypeMapper.CurrencyType_all();
    }

    @Override
    public Integer del_CurrencyType(Integer fid) {
        return currencyTypeMapper.del_CurrencyType(fid);
    }

    @Override
    public CurrencyType getCurrencyTypeById(Integer fid) {
        return currencyTypeMapper.getCurrencyTypeById(fid);
    }

    @Override
    @Transactional
    public Integer update_CurrencyType(CurrencyType currencyType) {
        Integer count = currencyTypeMapper.isexits(currencyType);
        if(count == 1){//已存在
            return -3;
        }else{//不存在
            return currencyTypeMapper.update_CurrencyType(currencyType);
        }

    }

    @Override
    public Integer isexits(CurrencyType currencyType) {
        return currencyTypeMapper.isexits(currencyType);
    }
}
