package com.myr.Service.Impl;

import com.myr.Bean.CustType;
import com.myr.Bean.Store;
import com.myr.Mapper.CustTypeMapper;
import com.myr.Mapper.StoreMapper;
import com.myr.Service.CustTypeService;
import com.myr.Service.StoreService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
public class StoreServiceImpl implements StoreService {
    @Resource
    StoreMapper storeMapper;

    @Override
    @Transactional
    public Integer addStore(Store store) {
        Integer count = storeMapper.isexits(store);
        if(count == 1){//已存在
            return -3;
        }else{//不存在
            return storeMapper.addStore(store);
        }

    }

    @Override
    public int getCounts_page(Map<String, Object> map) {
        return storeMapper.getCounts_page(map);
    }

    @Override
    public List<Store> Store_page(Map<String, Object> map) {
        return storeMapper.Store_page(map);
    }

    @Override
    public List<Store> Store_all(String str) {
        return storeMapper.Store_all(str);
    }

    @Override
    public Integer delStore(int fid) {
        return storeMapper.delStore(fid);
    }

    @Override
    public Store getStoreById(int fid) {
        return storeMapper.getStoreById(fid);
    }

    @Override
    @Transactional
    public Integer updateStore(Store store) {
        Integer count = storeMapper.isexits(store);
        if(count == 1){//已存在
            return -3;
        }else{//不存在
            return storeMapper.updateStore(store);
        }

    }

    @Override
    public Integer isexits(Store store) {
        return storeMapper.isexits(store);
    }
}
