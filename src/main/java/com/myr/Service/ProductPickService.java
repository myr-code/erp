package com.myr.Service;

import com.myr.Bean.MrpProductpick;
import com.myr.Bean.MrpProductplan;

import java.util.List;
import java.util.Map;

public interface ProductPickService {
    Integer add_ProductPick(List<MrpProductpick> mrpProductpicks);

    //获取系统单号  1销售订单
    String getBillNo(String dates);

    //获取总条数
    int getCounts(Map<String,Object> map);

    int getCounts_sour(Map<String,Object> map);

    //选择来源
    List<MrpProductpick> ProductPick_sour(Map<String,Object> map);

    List<MrpProductpick> ProductPick_page(Map<String,Object> map);

    List<MrpProductpick> get_ProductPick_ById(Integer mid);

    Integer del_ProductPick(String billNo);

    Integer update_ProductPick(List<MrpProductpick> mrpProductpicks);
}
