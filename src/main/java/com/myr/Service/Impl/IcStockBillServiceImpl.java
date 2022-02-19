package com.myr.Service.Impl;

import com.myr.Bean.Icstockbill;
import com.myr.Bean.Icstockbillentry;
import com.myr.Bean.Poorderentry;
import com.myr.Mapper.ICStockBillEntryMapper;
import com.myr.Mapper.IcstockbillMapper;
import com.myr.Service.IcStockBillService;
import com.myr.utils.DateOption;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
public class IcStockBillServiceImpl implements IcStockBillService {
    @Resource
    IcstockbillMapper icstockbillMapper;

    @Resource
    ICStockBillEntryMapper icStockBillEntryMapper;

    @Override
    public Integer addIcStockBill(Icstockbill icstockbill) {
        return icstockbillMapper.addIcStockBill(icstockbill);
    }

    @Override
    public String getICBillNo(String dates) {
        return icstockbillMapper.getICBillNo(dates);
    }

    @Override
    public int getCounts(Map<String, Object> map) {
        return icstockbillMapper.getCounts(map);
    }

    @Override
    public List<Icstockbill> IcStockBill_page(Map<String,Object> map) {
        return icstockbillMapper.IcStockBill_page(map);
    }

    @Override
    public int getCounts_saleout(Map<String, Object> map) {
        return icstockbillMapper.getCounts_saleout(map);
    }

    @Override
    public List<Icstockbill> IcStockBill_saleout_page(Map<String,Object> map) {
        return icstockbillMapper.IcStockBill_saleout_page(map);
    }

    @Override
    public List<Icstockbill> IcStockBill_pageGj(DateOption dateOption) {
        return icstockbillMapper.IcStockBill_pageGj(dateOption);
    }

    @Override
    public Integer IcStockBill_del(int fid) {
        return icstockbillMapper.IcStockBill_del(fid);
    }

    @Override
    public Icstockbill getIcStockBillById(int fid) {
        return icstockbillMapper.getIcStockBillById(fid);
    }

    @Override
    @Transactional
    public Integer IcStockBill_update(Icstockbill icstockbill, List<Icstockbillentry> icstockbillentryList) {
        Integer count = icstockbillMapper.IcStockBill_update(icstockbill);
        if(count>0){
            icStockBillEntryMapper.ICStockBillEntry_del(icstockbill.getFid());//2、删除原本行
            for (Icstockbillentry icstockbillentry : icstockbillentryList) {//3、新增新行
                icstockbillentry.setMid(icstockbill.getFid());//sql 已设置返回fid主键
                icStockBillEntryMapper.addICStockBillEntry(icstockbillentry);//新增体
            }
        }else{
            return 0;
        }
        return 1;
    }

    @Override
    public int getCounts_SaleOut_dz_sour(Map<String, Object> map) {
        return icstockbillMapper.getCounts_SaleOut_dz_sour(map);
    }

    @Override
    public List<Icstockbill> SaleOut_dz_sour(Map<String, Object> map) {
        return icstockbillMapper.SaleOut_dz_sour(map);
    }

    @Override
    public int getCounts_PurIn_dz_sour(Map<String, Object> map) {
        return icstockbillMapper.getCounts_PurIn_dz_sour(map);
    }

    @Override
    public List<Icstockbill> PurIn_dz_sour(Map<String, Object> map) {
        return icstockbillMapper.PurIn_dz_sour(map);
    }

    @Override
    public int getCounts_SK_sour(Map<String, Object> map) {
        return icstockbillMapper.getCounts_SK_sour(map);
    }

    @Override
    public List<Icstockbill> SK_sour(Map<String, Object> map) {
        return icstockbillMapper.SK_sour(map);
    }

    @Override
    public int getCounts_FK_sour(Map<String, Object> map) {
        return icstockbillMapper.getCounts_FK_sour(map);
    }

    @Override
    public List<Icstockbill> FK_sour(Map<String, Object> map) {
        return icstockbillMapper.FK_sour(map);
    }
}
