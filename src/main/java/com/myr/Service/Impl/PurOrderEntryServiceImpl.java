package com.myr.Service.Impl;

import com.myr.Bean.Poorder;
import com.myr.Bean.Poorderentry;
import com.myr.Bean.SaleOrder;
import com.myr.Bean.SaleOrderEntry;
import com.myr.Mapper.PurOrderEntryMapper;
import com.myr.Mapper.PurOrderMapper;
import com.myr.Mapper.SaleOrderEntryMapper;
import com.myr.Mapper.SaleOrderMapper;
import com.myr.Service.PurOrderEntryService;
import com.myr.Service.SaleOrderEntryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class PurOrderEntryServiceImpl implements PurOrderEntryService {
    @Resource
    PurOrderEntryMapper purOrderEntryMapper;

    @Resource
    PurOrderMapper purOrderMapper;

    @Override
    @Transactional
    public Integer addPurOrderEntry(Poorder poorder, List<Poorderentry> poorderentries) {
        Integer count = purOrderMapper.addPurOrder(poorder);
        if(count>0){
            for (Poorderentry Poorderentry : poorderentries) {
                Poorderentry.setMid(poorder.getFid());//sql 已设置返回fid主键
                purOrderEntryMapper.addPurOrderEntry(Poorderentry);//新增体
            }
        }else{
            return 0;
        }
        return 1;
    }

    @Override
    public Integer POentry_del(Integer mid) {
        return purOrderEntryMapper.POentry_del(mid);
    }

    @Override
    public List<Poorderentry> getPOentryById(Integer mid) {
        return purOrderEntryMapper.getPOentryById(mid);
    }
}
