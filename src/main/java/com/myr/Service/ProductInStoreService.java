package com.myr.Service;

import com.myr.Bean.MrpProductinstore;
import com.myr.Bean.MrpProductpick;
import com.myr.Bean.MrpProductplan;

import java.util.List;
import java.util.Map;

public interface ProductInStoreService {
    Integer add_ProductInStore(List<MrpProductinstore> mrpProductinstores);

    String getBillNo(String dates);

    List<MrpProductinstore> ProductInStore_page(Map<String,Object> map);

    int getCounts(Map<String,Object> map);

    List<MrpProductinstore> get_ProductInStoreById(Integer mid);

    Integer delProductInStore(String billNo);

    Integer ProductInStore_update(List<MrpProductinstore> mrpProductinstores);
}
