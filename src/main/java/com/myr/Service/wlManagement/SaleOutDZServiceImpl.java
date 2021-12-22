package com.myr.Service.wlManagement;

import com.myr.Bean.Dz;
import com.myr.Bean.Icstockbill;
import com.myr.Bean.Icstockbillentry;
import com.myr.Mapper.ICStockBillEntryMapper;
import com.myr.Mapper.IcstockbillMapper;
import com.myr.Mapper.wlManagement.SaleOutDZMapper;
import com.myr.Service.IcStockBillService;
import com.myr.utils.DateOption;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
public class SaleOutDZServiceImpl implements SaleOutDZService {
    @Resource
    SaleOutDZMapper saleOutDZMapper;

    @Resource
    ICStockBillEntryMapper icStockBillEntryMapper;

    @Override
    @Transactional
    public Integer addSaleOutDZ(List<Dz> dzList) {
        int count = 0;
        for (Dz dz : dzList) {
            if(saleOutDZMapper.addSaleOutDZ(dz)>0){
                count++;
            }else{
                return  -1;//有至少一条不成功
            }
        }
        return count;
    }

    @Override
    public String getBillNo_SaleOutDZ(String dates) {
        return saleOutDZMapper.getBillNo_SaleOutDZ(dates);
    }
/*
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
    }*/
}
