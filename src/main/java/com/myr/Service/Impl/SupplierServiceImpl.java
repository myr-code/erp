package com.myr.Service.Impl;

import com.myr.Bean.CustType;
import com.myr.Bean.Store;
import com.myr.Bean.Supplier;
import com.myr.Mapper.CustTypeMapper;
import com.myr.Mapper.SupplierMapper;
import com.myr.Service.CustTypeService;
import com.myr.Service.SupplierService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
public class SupplierServiceImpl implements SupplierService {
    @Resource
    SupplierMapper supplierMapper;

    @Override
    @Transactional
    public Integer addSupplier(Supplier supplier) {
        Integer count = supplierMapper.isexits(supplier);
        if(count == 1){//已存在
            return -3;
        }else{//不存在
            return supplierMapper.addSupplier(supplier);
        }

    }

    @Override
    public List<Supplier> Supplier_all(String str) {
        return supplierMapper.Supplier_all(str);
    }

    @Override
    public int getCounts_page(Map<String, Object> map) {
        return supplierMapper.getCounts_page(map);
    }

    @Override
    public List<Supplier> Supplier_page(Map<String, Object> map) {
        return supplierMapper.Supplier_page(map);
    }

    @Override
    public Integer delSupp(int fid) {
        return supplierMapper.delSupp(fid);
    }

    @Override
    public Supplier getSuppById(int fid) {
        return supplierMapper.getSuppById(fid);
    }

    @Override
    @Transactional
    public Integer updateSupp(Supplier supplier) {
        Integer count = supplierMapper.isexits(supplier);
        if(count == 1){//已存在
            return -3;
        }else{//不存在
            return supplierMapper.updateSupp(supplier);
        }

    }

    @Override
    public List<Supplier> Supplier_pageGj(Supplier supplier) {
        return supplierMapper.Supplier_pageGj(supplier);
    }

    @Override
    public Integer isexits(Supplier supplier) {
        return supplierMapper.isexits(supplier);
    }
}
