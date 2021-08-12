package com.myr.Service.Impl;

import com.myr.Bean.Poorder;
import com.myr.Bean.Poorderentry;
import com.myr.Bean.SaleOrder;
import com.myr.Bean.SaleOrderEntry;
import com.myr.Mapper.PurOrderEntryMapper;
import com.myr.Mapper.PurOrderMapper;
import com.myr.Mapper.SaleOrderEntryMapper;
import com.myr.Mapper.SaleOrderMapper;
import com.myr.Service.PurOrderService;
import com.myr.Service.SaleOrderService;
import com.myr.utils.DateOption;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
public class PurOrderServiceImpl implements PurOrderService {
    @Resource
    PurOrderMapper purOrderMapper;

    @Resource
    PurOrderEntryMapper purOrderEntryMapper;

    @Override
    public Integer addPurOrder(Poorder poorder) {
        return purOrderMapper.addPurOrder(poorder);
    }

    @Override
    public String getBillNo(String dates) {
        return purOrderMapper.getBillNo(dates);
    }

    @Override
    public int getCounts(Map<String,Object> map) {
        return purOrderMapper.getCounts(map);
    }

    @Override
    public List<Poorder> PurOrder_sour(Map<String,Object> map) {
        return purOrderMapper.PurOrder_sour(map);
    }

    @Override
    public List<Poorder> PurOrder_page(String str) {
        return purOrderMapper.PurOrder_page(str);
    }

    @Override
    public List<Poorder> PurOrder_pageGj(DateOption dateOption) {
        return purOrderMapper.PurOrder_pageGj(dateOption);
    }

    @Override
    public Integer PurOrder_del(int fid) {
        return purOrderMapper.PurOrder_del(fid);
    }

    @Override
    public Poorder getPurById(int fid) {
        return purOrderMapper.getPurById(fid);
    }

    @Override
    @Transactional
    public Integer PurOrder_update(Poorder poorder, List<Poorderentry> poorderentries) {
        Integer count = purOrderMapper.PurOrder_update(poorder);
        if(count>0){
            purOrderEntryMapper.POentry_del(poorder.getFid());//2、删除原本行
            for (Poorderentry poorderentry : poorderentries) {//3、新增新行
                poorderentry.setMid(poorder.getFid());//sql 已设置返回fid主键
                purOrderEntryMapper.addPurOrderEntry(poorderentry);//新增体
            }
        }else{
            return 0;
        }
        return 1;
    }
}
