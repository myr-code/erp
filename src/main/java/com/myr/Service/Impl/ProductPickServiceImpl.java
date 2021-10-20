package com.myr.Service.Impl;

import com.myr.Bean.MrpProductpick;
import com.myr.Bean.MrpProductplan;
import com.myr.Mapper.mrp.Mrp_ProductPlanMapper;
import com.myr.Mapper.mrp.ProductPickMapper;
import com.myr.Service.ProductPickService;
import com.myr.Service.ProductPlanService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
public class ProductPickServiceImpl implements ProductPickService {
    @Resource
    ProductPickMapper productPickMapper;

    @Override
    public Integer add_ProductPick(List<MrpProductpick> mrpProductpicks) {
        int count =0;
        for (MrpProductpick mrpProductpick : mrpProductpicks) {
            Integer i = productPickMapper.add_ProductPick(mrpProductpick);
            count += i;
        }
        return count;
    }

    @Override
    public String getBillNo(String dates) {
        return productPickMapper.getBillNo(dates);
    }

    @Override
    public int getCounts(Map<String, Object> map) {
        return productPickMapper.getCounts(map);
    }

    @Override
    public int getCounts_sour(Map<String, Object> map) {
        return productPickMapper.getCounts_sour(map);
    }

    @Override
    public List<MrpProductpick> ProductPick_sour(Map<String, Object> map) {
        return productPickMapper.ProductPick_sour(map);
    }

    @Override
    public List<MrpProductpick> ProductPick_page(Map<String, Object> map) {
        return productPickMapper.ProductPick_page(map);
    }

    @Override
    public List<MrpProductpick> get_ProductPick_ById(Integer mid) {
        return productPickMapper.get_ProductPick_ById(mid);
    }

    @Override
    public Integer del_ProductPick(String billNo) {
        return productPickMapper.del_ProductPick(billNo);
    }

    @Override
    @Transactional
    public Integer update_ProductPick(List<MrpProductpick> mrpProductpicks) {
        int rs = 0;
        MrpProductpick pick = new MrpProductpick();
        if(mrpProductpicks.size()>0&&mrpProductpicks!=null){
            pick = mrpProductpicks.get(0);

            if(productPickMapper.del_ProductPick(pick.getBillNo())>0){
                for (MrpProductpick mrpProductpick1 : mrpProductpicks) {
                    productPickMapper.add_ProductPick(mrpProductpick1);
                }
                rs = 1;
            }
        }

        return rs;
    }


}
