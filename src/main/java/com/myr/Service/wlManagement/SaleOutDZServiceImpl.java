package com.myr.Service.wlManagement;

import com.myr.Bean.Dz;
import com.myr.Bean.Icstockbill;
import com.myr.Bean.Icstockbillentry;
import com.myr.Bean.MrpProductpick;
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

    @Override
    public int getCounts_index(Map<String, Object> map) {
        return saleOutDZMapper.getCounts_index(map);
    }

    @Override
    public List<Dz> SaleOutDZ_index(Map<String, Object> map) {
        return saleOutDZMapper.SaleOutDZ_index(map);
    }

    @Override
    public List<Dz> getSaleOutDZById(int fid) {
        return saleOutDZMapper.getSaleOutDZById(fid);
    }

    @Override
    public Integer SaleOutDZ_del(String billNo) {
        return saleOutDZMapper.SaleOutDZ_del(billNo);
    }

    @Override
    @Transactional
    public Integer SaleOutDZ_update(List<Dz> dzList) {
        int rs = 0;
        Dz dz = new Dz();
        if(dzList.size()>0&&dzList!=null){
            dz = dzList.get(0);

            if(saleOutDZMapper.SaleOutDZ_del(dz.getBillNo())>0){
                for (Dz dz1 : dzList) {
                    saleOutDZMapper.addSaleOutDZ(dz1);
                }
                rs = 1;
            }
        }

        return rs;
    }
/*


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
