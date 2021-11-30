package com.myr.Service.Impl;

import com.myr.Bean.CustType;
import com.myr.Mapper.CustTypeMapper;
import com.myr.Service.CustTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
public class CustTypeServiceImpl implements CustTypeService {
    @Resource
    CustTypeMapper custTypeMapper;

    @Override
    @Transactional
    public Integer addCustomerType(CustType custType) {
        Integer count = custTypeMapper.isexits(custType);
        if(count == 1){//已存在
            return -3;
        }else{//不存在
            return custTypeMapper.addCustomerType(custType);
        }
    }

    @Override
    public int getCounts_page(Map<String, Object> map) {
        return custTypeMapper.getCounts_page(map);
    }

    @Override
    public List<CustType> custType_page(Map<String, Object> map) {
        return custTypeMapper.custType_page(map);
    }

    @Override
    public int getCounts_SuppType_page(Map<String, Object> map) {
        return custTypeMapper.getCounts_SuppType_page(map);
    }

    @Override
    public List<CustType> SuppType_page(Map<String, Object> map) {
        return custTypeMapper.SuppType_page(map);
    }

    @Override
    public List<CustType> custType_all(int type) {
        return custTypeMapper.custType_all(type);
    }

    @Override
    public Integer delCustType(Integer fid) {
        return custTypeMapper.delCustType(fid);
    }

    @Override
    public CustType getCustTypeById(Integer fid) {
        return custTypeMapper.getCustTypeById(fid);
    }

    @Override
    @Transactional
    public Integer updateCustType(CustType custType) {
        Integer count = custTypeMapper.isexits(custType);
        if(count == 1){//已存在
            return -3;
        }else{//不存在
            return custTypeMapper.updateCustType(custType);
        }
    }

    @Override
    public Integer isexits(CustType custType) {
        return custTypeMapper.isexits(custType);
    }
}
