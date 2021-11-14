package com.myr.Mapper;

import com.myr.Bean.CurrencyType;
import com.myr.Bean.ItemType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CurrencyTypeMapper {
    //01-添加
    Integer add_CurrencyType(CurrencyType currencyType);

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
