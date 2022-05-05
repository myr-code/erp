package com.myr.Service.Impl.othermodel;

import com.myr.Bean.Customer;
import com.myr.Bean.othermodel.CustomerCrm;
import com.myr.Bean.othermodel.Urlcontent;
import com.myr.Mapper.CustomerMapper;
import com.myr.Mapper.othermodel.CustomerCrmMapper;
import com.myr.Service.CustomerService;
import com.myr.Service.othermodel.CustomerCrmService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
public class CustomerCrmServiceImpl implements CustomerCrmService {
    @Resource
    CustomerCrmMapper customerCrmMapper;

    @Override
    public Integer addCustomerCrm(CustomerCrm customerCrm) {
        Integer count = customerCrmMapper.isexits(customerCrm);
        if(count == 1){//已存在
            return -3;
        }else{//不存在
            return customerCrmMapper.addCustomerCrm(customerCrm);
        }
    }

    @Override
    public int getCounts_page(Map<String, Object> map) {
        return customerCrmMapper.getCounts_page(map);
    }

    @Override
    public List<CustomerCrm> CustomerCrm_page(Map<String, Object> map) {
        return customerCrmMapper.CustomerCrm_page(map);
    }

    @Override
    public List<CustomerCrm> CustomerCrm_pageGj(CustomerCrm customerCrm) {
        return customerCrmMapper.CustomerCrm_pageGj(customerCrm);
    }

    @Override
    public List<CustomerCrm> CustomerCrm_all() {
        return customerCrmMapper.CustomerCrm_all();
    }

    @Override
    public CustomerCrm getCustCrmById(Integer fid) {
        return customerCrmMapper.getCustCrmById(fid);
    }

    @Override
    @Transactional
    public Integer updateCustCrm(CustomerCrm customerCrm) {
        Integer count = customerCrmMapper.isexits(customerCrm);
        if(count == 1){//已存在
            return -3;
        }else{//不存在
            return customerCrmMapper.updateCustCrm(customerCrm);
        }
    }

    @Override
    public Integer isexits(CustomerCrm customerCrm) {
        return customerCrmMapper.isexits(customerCrm);
    }

    @Override
    public Integer delCustCrm(Integer fid) {
        return customerCrmMapper.delCustCrm(fid);
    }

}
