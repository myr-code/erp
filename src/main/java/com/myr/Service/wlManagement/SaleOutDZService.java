package com.myr.Service.wlManagement;

import com.myr.Bean.Dz;
import com.myr.Bean.Icstockbill;
import com.myr.Bean.Icstockbillentry;
import com.myr.utils.DateOption;

import java.util.List;
import java.util.Map;

public interface SaleOutDZService {
    Integer addSaleOutDZ(List<Dz> dzList);

    String getBillNo_SaleOutDZ(String dates);


    int getCounts_index(Map<String,Object> map);

    List<Dz> SaleOutDZ_index(Map<String,Object> map);

    List<Dz> getSaleOutDZById(int fid);

    Integer SaleOutDZ_del(String billNo);

    Integer SaleOutDZ_update(List<Dz> dzList);

    /*int getCounts_saleout(Map<String,Object> map);

    List<Icstockbill> IcStockBill_saleout_page(Map<String,Object> map);

    List<Icstockbill> IcStockBill_pageGj(DateOption dateOption);

    int getCounts_SaleOut_dz_sour(Map<String,Object> map);

    List<Icstockbill> SaleOut_dz_sour(Map<String,Object> map);*/
}
