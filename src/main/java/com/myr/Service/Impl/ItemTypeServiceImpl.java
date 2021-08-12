package com.myr.Service.Impl;

import com.myr.Bean.CustType;
import com.myr.Bean.ItemType;
import com.myr.Mapper.CustTypeMapper;
import com.myr.Mapper.ItemTypeMapper;
import com.myr.Service.CustTypeService;
import com.myr.Service.ItemTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class ItemTypeServiceImpl implements ItemTypeService {
    @Resource
    ItemTypeMapper itemTypeMapper;

    @Override
    @Transactional
    public Integer addItemType(ItemType itemType) {
        Integer count = itemTypeMapper.isexits(itemType);
        if(count == 1){//已存在
            return -3;
        }else{//不存在
            return itemTypeMapper.addItemType(itemType);
        }

    }

    @Override
    public List<ItemType> ItemType_all() {
        return itemTypeMapper.ItemType_all();
    }

    @Override
    public Integer delItemType(Integer fid) {
        return itemTypeMapper.delItemType(fid);
    }

    @Override
    public ItemType getItemTypeById(Integer fid) {
        return itemTypeMapper.getItemTypeById(fid);
    }

    @Override
    @Transactional
    public Integer updateItemType(ItemType itemType) {
        Integer count = itemTypeMapper.isexits(itemType);
        if(count == 1){//已存在
            return -3;
        }else{//不存在
            return itemTypeMapper.updateItemType(itemType);
        }

    }

    @Override
    public Integer isexits(ItemType itemType) {
        return itemTypeMapper.isexits(itemType);
    }
}
