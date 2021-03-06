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
    public int getCounts_index(Map<String, Object> map) {
        return purOrderMapper.getCounts_index(map);
    }

    @Override
    public List<Poorder> PurOrder_sour(Map<String,Object> map) {
        return purOrderMapper.PurOrder_sour(map);
    }

    @Override
    public List<Poorder> PurOrder_page(Map<String,Object> map) {
        return purOrderMapper.PurOrder_page(map);
    }

    @Override
    public List<Poorder> PurOrder_pageGj(DateOption dateOption) {
        return purOrderMapper.PurOrder_pageGj(dateOption);
    }

    @Override
    public Integer PurOrder_del(int fid) {
        int count = 0;
        if(purOrderMapper.PurOrder_del_body(fid)>0){
            count = purOrderMapper.PurOrder_del(fid);
        }
        return count;
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
            purOrderEntryMapper.POentry_del(poorder.getFid());//2??????????????????
            for (Poorderentry poorderentry : poorderentries) {//3???????????????
                poorderentry.setMid(poorder.getFid());//sql ???????????????fid??????
                purOrderEntryMapper.addPurOrderEntry(poorderentry);//?????????
            }
        }else{
            return 0;
        }
        return 1;
    }
}
