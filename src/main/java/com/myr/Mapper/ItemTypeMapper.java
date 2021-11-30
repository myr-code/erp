package com.myr.Mapper;

import com.myr.Bean.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ItemTypeMapper {
    //01-添加
    Integer addItemType(ItemType itemType);

    //获取总条数
    int getCounts_page(Map<String,Object> map);

    //02-序时簿
    List<ItemType> ItemType_page(Map<String,Object> map);

    //02-所有客户分类信息
    List<ItemType> ItemType_all();

    //删除
    Integer delItemType(Integer fid);

    //获取一个
    ItemType getItemTypeById(Integer fid);

    //更新
    Integer updateItemType(ItemType itemType);

    //是否存在
    Integer isexits(ItemType itemType);
}
