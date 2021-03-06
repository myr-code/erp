package com.myr.Service;

import com.myr.Bean.CustType;
import com.myr.Bean.SaleOrder;
import com.myr.Bean.SaleOrderEntry;
import com.myr.utils.DateOption;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface SaleOrderService {
    Integer addSaleOrder(SaleOrder saleOrder);

    String getBillNo(String dates);

    int getCounts(Map<String,Object> map);

    int getCounts_index(Map<String,Object> map);

    List<SaleOrder> SaleOrder_mrp(Map<String,Object> map);

    int getCounts_mrp(Map<String,Object> map);

    List<SaleOrder> SaleOrder_sour(Map<String,Object> map);

    List<SaleOrder> SaleOrder_page(Map<String,Object> map);

    List<SaleOrder> SaleOrder_pageGj(DateOption dateOption);

    Integer delSaleOrder(Integer fid);

    Set<String> SaleOrder_isQuoted(Integer fid);

    Integer SaleOrder_update(SaleOrder saleOrder);

    SaleOrder getSOById(Integer fid);

    Integer SO_update(SaleOrder saleOrder,List<SaleOrderEntry> saleOrderEntry);
}
