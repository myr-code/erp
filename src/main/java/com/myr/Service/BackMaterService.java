package com.myr.Service;

import com.myr.Bean.MrpBackmater;
import com.myr.Bean.MrpProductplan;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface BackMaterService {
    Integer add_BackMater(List<MrpBackmater> mrpBackmater);

    //获取系统单号  1销售订单
    String getBillNo(String dates);

    //获取总条数
    int getCounts(Map<String,Object> map);

    List<MrpBackmater> BackMater_page(Map<String,Object> map);

    List<MrpBackmater> getBackMaterById(Integer mid);

    Set<String> BackMater_isQuoted(String billNo);

    Integer delBackMater(String billNo);

    Integer BackMater_update(MrpBackmater mrpBackmater,List<MrpBackmater> mrpBackmaters);
}
