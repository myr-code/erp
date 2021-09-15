package com.myr.Service;

import com.myr.Bean.Poorder;
import com.myr.Bean.Poorderentry;
import com.myr.Bean.SaleOrder;
import com.myr.Bean.SaleOrderEntry;
import com.myr.utils.DateOption;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PurOrderService {
    Integer addPurOrder(Poorder poorder);

    String getBillNo(String dates);

    int getCounts(Map<String,Object> map);

    int getCounts_index(Map<String,Object> map);

    List<Poorder> PurOrder_sour(Map<String,Object> map);

    List<Poorder> PurOrder_page(Map<String,Object> map);

    List<Poorder> PurOrder_pageGj(DateOption dateOption);

    Integer PurOrder_del(int fid);

    Poorder getPurById(int fid);

    Integer PurOrder_update(Poorder poorder, List<Poorderentry> poorderentries);
}
