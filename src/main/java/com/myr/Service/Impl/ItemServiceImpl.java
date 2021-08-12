package com.myr.Service.Impl;


import com.myr.Bean.Item;
import com.myr.Mapper.ItemMapper;
import com.myr.Service.ItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
public class ItemServiceImpl implements ItemService {
    @Resource
    ItemMapper itemMapper;

    @Override
    @Transactional
    public Integer addItem(Item item) {
        Integer count = itemMapper.isexits(item);
        if(count == 1){//已存在
            return -3;
        }else{//不存在
            return itemMapper.addItem(item);
        }

    }

    @Override
    public List<Item> Item_page(String str) {
        return itemMapper.Item_page(str);
    }

    @Override
    public List<Item> Item_pageGj(Item item) {
        return itemMapper.Item_pageGj(item);
    }

    @Override
    public List<Item> ItemStock(Map<String, Object> map) {
        return itemMapper.ItemStock(map);
    }

    @Override
    public int getCounts(String cnm) {
        return itemMapper.getCounts(cnm);
    }

    @Override
    public List<Item> Item_queryByCNM(Map<String,Object> map) {
        return itemMapper.Item_queryByCNM(map);
    }

    @Override
    public Integer delItem(Integer fid) {
        return itemMapper.delItem(fid);
    }

    @Override
    public Item getItemById(Integer fid) {
        return itemMapper.getItemById(fid);
    }

    @Override
    @Transactional
    public Integer updateItem(Item item) {
        Integer count = itemMapper.isexits(item);
        if(count == 1){//已存在
            return -3;
        }else{//不存在
            return itemMapper.updateItem(item);
        }

    }

    @Override
    public Integer isexits(Item item) {
        return itemMapper.isexits(item);
    }
}
