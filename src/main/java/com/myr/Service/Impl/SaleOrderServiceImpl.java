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
import java.util.Set;

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
    public List<SaleOrder> SaleOrder_mrp(Map<String, Object> map) {
        return saleOrderMapper.SaleOrder_mrp(map);
    }

    @Override
    public int getCounts_mrp(Map<String, Object> map) {
        return saleOrderMapper.getCounts_mrp(map);
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
    @Transactional
    public Integer delSaleOrder(Integer fid) {
        int count = 0;
        Set<String> strings = saleOrderMapper.SaleOrder_isQuoted(fid);
        if(strings.size()>0){
            count = strings.size()*-1;
        }else{
            //??????????????????????????????
            if(saleOrderMapper.SaleOrder_del_body(fid)>0){
                count = saleOrderMapper.delSaleOrder(fid);
            }

        }
        return count;
    }

    @Override
    public Set<String> SaleOrder_isQuoted(Integer fid) {
        return saleOrderMapper.SaleOrder_isQuoted(fid);
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
        Integer count = saleOrderMapper.SaleOrder_update(saleOrder);//1????????????
        if(count>0){
            saleOrderEntryMapper.delSOEntry(saleOrder.getFid());//2??????????????????
            for (SaleOrderEntry orderEntry : saleOrderEntry) {//3???????????????
                orderEntry.setMid(saleOrder.getFid());//sql ???????????????fid??????
                saleOrderEntryMapper.addSaleOrderEntry(orderEntry);//?????????
            }
        }else{
            return 0;
        }
        return 1;
    }
}
