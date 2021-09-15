package com.myr.Service.Impl;

import com.myr.Bean.CustType;
import com.myr.Bean.SaleOrder;
import com.myr.Bean.SaleOrderEntry;
import com.myr.Mapper.CustTypeMapper;
import com.myr.Mapper.SaleOrderEntryMapper;
import com.myr.Mapper.SaleOrderMapper;
import com.myr.Service.CustTypeService;
import com.myr.Service.SaleOrderService;
import com.myr.utils.DateOption;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
public class SaleOrderServiceImpl implements SaleOrderService {
    @Resource
    SaleOrderMapper saleOrderMapper;

    @Resource
    SaleOrderEntryMapper saleOrderEntryMapper;

    @Override
    public Integer addSaleOrder(SaleOrder saleOrder) {
        return saleOrderMapper.addSaleOrder(saleOrder);
    }

    @Override
    public String getBillNo(String dates) {
        return saleOrderMapper.getBillNo(dates);
    }

    @Override
    public int getCounts(Map<String,Object> map) {
        return saleOrderMapper.getCounts(map);
    }

    @Override
    public int getCounts_index(Map<String, Object> map) {
        return saleOrderMapper.getCounts_index(map);
    }

    @Override
    public List<SaleOrder> SaleOrder_sour(Map<String, Object> map) {
        return saleOrderMapper.SaleOrder_sour(map);
    }

    @Override
    public List<SaleOrder> SaleOrder_page(Map<String,Object> map) {
        return saleOrderMapper.SaleOrder_page(map);
    }

    @Override
    public List<SaleOrder> SaleOrder_pageGj(DateOption dateOption) {
        return saleOrderMapper.SaleOrder_pageGj(dateOption);
    }

    @Override
    public Integer delSaleOrder(Integer fid) {
        return saleOrderMapper.delSaleOrder(fid);
    }

    @Override
    public Integer SaleOrder_update(SaleOrder saleOrder) {
        return saleOrderMapper.SaleOrder_update(saleOrder);
    }

    @Override
    public SaleOrder getSOById(Integer fid) {
        return saleOrderMapper.getSOById(fid);
    }

    @Override
    @Transactional
    public Integer SO_update(SaleOrder saleOrder, List<SaleOrderEntry> saleOrderEntry) {
        Integer count = saleOrderMapper.SaleOrder_update(saleOrder);//1、更新头
        if(count>0){
            saleOrderEntryMapper.delSOEntry(saleOrder.getFid());//2、删除原本行
            for (SaleOrderEntry orderEntry : saleOrderEntry) {//3、新增新行
                orderEntry.setMid(saleOrder.getFid());//sql 已设置返回fid主键
                saleOrderEntryMapper.addSaleOrderEntry(orderEntry);//新增体
            }
        }else{
            return 0;
        }
        return 1;
    }
}
