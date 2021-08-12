package com.myr.Service.Impl;

import com.myr.Bean.Icstockbill;
import com.myr.Bean.Icstockbillentry;
import com.myr.Bean.Poorder;
import com.myr.Bean.Poorderentry;
import com.myr.Mapper.ICStockBillEntryMapper;
import com.myr.Mapper.IcstockbillMapper;
import com.myr.Mapper.PurOrderEntryMapper;
import com.myr.Mapper.PurOrderMapper;
import com.myr.Service.ICStockBillEntryService;
import com.myr.Service.PurOrderEntryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class ICStockBillEntryServiceImpl implements ICStockBillEntryService {
    @Resource
    ICStockBillEntryMapper icStockBillEntryMapper;

    @Resource
    IcstockbillMapper icstockbillMapper;


    @Override
    @Transactional
    public Integer addICStockBillEntry(Icstockbill icstockbill, List<Icstockbillentry> icstockbillentryList) {
        Integer count = icstockbillMapper.addIcStockBill(icstockbill);
        if(count>0){
            for (Icstockbillentry icstockbillentry : icstockbillentryList) {
                icstockbillentry.setMid(icstockbill.getFid());//sql 已设置返回fid主键
                icStockBillEntryMapper.addICStockBillEntry(icstockbillentry);//新增体
            }
        }else{
            return 0;
        }
        return 1;
    }

    @Override
    public Integer ICStockBillEntry_del(Integer mid) {
        return icStockBillEntryMapper.ICStockBillEntry_del(mid);
    }

    @Override
    public List<Icstockbillentry> getICStockBillEntryById(Integer mid) {
        return icStockBillEntryMapper.getICStockBillEntryById(mid);
    }
}
