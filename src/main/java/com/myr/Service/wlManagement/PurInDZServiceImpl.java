package com.myr.Service.wlManagement;

import com.myr.Bean.Dz;
import com.myr.Mapper.wlManagement.PurInDZMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
public class PurInDZServiceImpl implements PurInDZService {
    @Resource
    PurInDZMapper purInDZMapper;

    @Override
    @Transactional
    public Integer addPurInDZ(List<Dz> dzList) {
        int count = 0;
        for (Dz dz : dzList) {
            if(purInDZMapper.addPurInDZ(dz)>0){
                count++;
            }else{
                return  -1;//有至少一条不成功
            }
        }
        return count;
    }

    @Override
    public String getBillNo_PurInDZ(String dates) {
        return purInDZMapper.getBillNo_PurInDZ(dates);
    }

    @Override
    public int getCounts_index(Map<String, Object> map) {
        return purInDZMapper.getCounts_index(map);
    }

    @Override
    public List<Dz> PurInDZ_index(Map<String, Object> map) {
        return purInDZMapper.PurInDZ_index(map);
    }

    @Override
    public List<Dz> getPurInDZById(int fid) {
        return purInDZMapper.getPurInDZById(fid);
    }

    @Override
    public Integer PurInDZ_del(String billNo) {
        return purInDZMapper.PurInDZ_del(billNo);
    }

    @Override
    @Transactional
    public Integer PurInDZ_update(List<Dz> dzList) {
        int rs = 0;
        Dz dz = new Dz();
        if(dzList.size()>0&&dzList!=null){
            dz = dzList.get(0);

            if(purInDZMapper.PurInDZ_del(dz.getBillNo())>0){
                for (Dz dz1 : dzList) {
                    purInDZMapper.addPurInDZ(dz1);
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
