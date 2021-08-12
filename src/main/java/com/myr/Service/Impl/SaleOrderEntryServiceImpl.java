package com.myr.Service.Impl;

import com.myr.Bean.CustType;
import com.myr.Bean.SaleOrder;
import com.myr.Bean.SaleOrderEntry;
import com.myr.Mapper.CustTypeMapper;
import com.myr.Mapper.SaleOrderEntryMapper;
import com.myr.Mapper.SaleOrderMapper;
import com.myr.Service.CustTypeService;
import com.myr.Service.SaleOrderEntryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class SaleOrderEntryServiceImpl implements SaleOrderEntryService {
    @Resource
    SaleOrderEntryMapper saleOrderEntryMapper;

    @Resource
    SaleOrderMapper saleOrderMapper;

    @Override
    @Transactional
    public Integer addSaleOrderEntry(SaleOrder saleOrder, List<SaleOrderEntry> saleOrderEntry) {
        Integer count = saleOrderMapper.addSaleOrder(saleOrder);//新增头
        if(count>0){
            for (SaleOrderEntry orderEntry : saleOrderEntry) {
                orderEntry.setMid(saleOrder.getFid());//sql 已设置返回fid主键
                saleOrderEntryMapper.addSaleOrderEntry(orderEntry);//新增体
            }
        }else{
            return 0;
        }
        return 1;
    }

    @Override
    public Integer delSOEntry(Integer mid) {
        return saleOrderEntryMapper.delSOEntry(mid);
    }

    @Override
    public List<SaleOrderEntry> getSOentryById(Integer mid) {
        return saleOrderEntryMapper.getSOentryById(mid);
    }

}
