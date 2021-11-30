package com.myr.Service;

import com.myr.Bean.CurrencyType;
import com.myr.Bean.ItemType;

import java.util.List;
import java.util.Map;

public interface CurrencyTypeService {
    //01-添加
    Integer add_CurrencyType(CurrencyType currencyType);

    int getCounts_page(Map<String,Object> map);

    //02-序时簿
    List<CurrencyType> CurrencyType_page(Map<String,Object> map);

    //02-所有客户分类信息
    List<CurrencyType> CurrencyType_all();

    //删除
    Integer del_CurrencyType(Integer fid);

    //获取一个
    CurrencyType getCurrencyTypeById(Integer fid);

    //更新
    Integer update_CurrencyType(CurrencyType currencyType);

    //是否存在
    Integer isexits(CurrencyType currencyType);
}
