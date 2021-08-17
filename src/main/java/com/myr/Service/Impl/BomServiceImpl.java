package com.myr.Service.Impl;


import com.myr.Bean.Bom;
import com.myr.Bean.Bomentry;
import com.myr.Bean.SaleOrderEntry;
import com.myr.Mapper.BomEntryMapper;
import com.myr.Mapper.BomMapper;
import com.myr.Service.BomService;
import com.myr.utils.DateOption;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
public class BomServiceImpl implements BomService {
    @Resource
    BomMapper bomMapper;

    @Resource
    BomEntryMapper bomEntryMapper;

    @Override
    @Transactional
    public Integer addBom(Bom bom, List<Bomentry> bomentries) {
        Integer count = bomMapper.addBom(bom);//新增头
        if(count>0){
            for (Bomentry bomentry : bomentries) {
                bomentry.setMid(bom.getFid());//sql 已设置返回fid主键
                bomEntryMapper.addBomEntry(bomentry);//新增体
            }
        }else {
            return 0;
        }
        return 1;
    }

    @Override
    public String getBillNo(String dates) {
        return bomMapper.getBillNo(dates);
    }

    @Override
    public int getCounts(Map<String, Object> map) {
        return bomMapper.getCounts(map);
    }

    @Override
    public List<Bom> Bom_page(Map<String,Object> map) {
        return bomMapper.Bom_page(map);
    }

    @Override
    public List<Bom> Bom_pageGj(DateOption dateOption) {
        return bomMapper.Bom_pageGj(dateOption);
    }

    @Override
    public Integer delBom(int fid) {
        return bomMapper.delBom(fid);
    }

    @Override
    public Integer Bom_isexit(int fid, int muid) {
        return bomMapper.Bom_isexit(fid,muid);
    }

    @Override
    public List<Bom> Bom_grade(int muid) {
        return bomMapper.Bom_grade(muid);
    }

    @Override
    @Transactional
    public Integer Bom_update(Bom bom, List<Bomentry> bomentries) {
        Integer count = bomMapper.Bom_update(bom);//1、更新头
        System.out.println("count="+count);
        if(count>0){
            bomEntryMapper.delBomEntry(bom.getFid());//2、删除原本行

            for (Bomentry bomentry : bomentries) {//3、新增新行
                bomentry.setMid(bom.getFid());//sql 已设置返回fid主键
                bomEntryMapper.addBomEntry(bomentry);//4、新增体
            }
        }else{
            return 0;
        }
        return 1;
    }

    @Override
    public Bom getBomById(Integer fid) {
        return bomMapper.getBomById(fid);
    }
}
