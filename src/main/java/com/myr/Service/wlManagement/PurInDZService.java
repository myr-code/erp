package com.myr.Service.wlManagement;

import com.myr.Bean.Dz;

import java.util.List;
import java.util.Map;

public interface PurInDZService {
    Integer addPurInDZ(List<Dz> dzList);

    String getBillNo_PurInDZ(String dates);


    int getCounts_index(Map<String,Object> map);

    List<Dz> PurInDZ_index(Map<String,Object> map);

    List<Dz> getPurInDZById(int fid);

    Integer PurInDZ_del(String billNo);

    Integer PurInDZ_update(List<Dz> dzList);

    /*int getCounts_saleout(Map<String,Object> map);

    List<Icstockbill> IcStockBill_saleout_page(Map<String,Object> map);

    List<Icstockbill> IcStockBill_pageGj(DateOption dateOption);

    int getCounts_SaleOut_dz_sour(Map<String,Object> map);

    List<Icstockbill> SaleOut_dz_sour(Map<String,Object> map);*/
}
