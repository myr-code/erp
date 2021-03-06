package com.myr.Service.Impl;

import com.myr.Bean.MrpProductplan;
import com.myr.Mapper.mrp.Mrp_ProductPlanMapper;
import com.myr.Service.ProductPlanService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class ProductPlanServiceImpl implements ProductPlanService {
    @Resource
    Mrp_ProductPlanMapper productPlanMapper;

    @Override
    public Integer addMrp_ProductPlan(List<MrpProductplan> mrpProductplans) {
        for (MrpProductplan mrpProductplan : mrpProductplans) {
            productPlanMapper.addMrp_ProductPlan(mrpProductplan);
        }
        return 1;
    }

    @Override
    public String getBillNo(String dates) {
        return productPlanMapper.getBillNo(dates);
    }

    @Override
    public int getCounts_sour(Map<String, Object> map) {
        return productPlanMapper.getCounts_sour(map);
    }

    @Override
    public List<MrpProductplan> ProductPlan_sour(Map<String, Object> map) {
        return productPlanMapper.ProductPlan_sour(map);
    }

    @Override
    public int getCounts(Map<String, Object> map) {
        return productPlanMapper.getCounts(map);
    }

    @Override
    public List<MrpProductplan> Mrp_ProductPlan_page(Map<String, Object> map) {
        return productPlanMapper.Mrp_ProductPlan_page(map);
    }

    @Override
    public List<MrpProductplan> getMrp_ProductPlanById(Integer mid) {
        return productPlanMapper.getMrp_ProductPlanById(mid);
    }

    @Override
    public Set<String> Mrp_ProductPlan_isQuoted(String billNo) {
        return productPlanMapper.Mrp_ProductPlan_isQuoted(billNo);
    }

    @Override
    @Transactional
    public Integer delMrpProductPlan(String billNo) {
        int count = 0;
        Set<String> strings = productPlanMapper.Mrp_ProductPlan_isQuoted(billNo);
        if(strings.size()>0){
            //负数 单据被引用
            count = strings.size()*-1;
        }else{
            //删除 正数 单据被删除
            count = productPlanMapper.delMrpProductPlan(billNo);
        }

        return count;
    }

    @Override
    @Transactional
    public Integer Mrp_ProductPlan_update(List<MrpProductplan> mrpProductplans) {
        int rs = 0;
        MrpProductplan mrpProductplan = null;
        if(mrpProductplans!=null && mrpProductplans.size()>0){
            mrpProductplan = mrpProductplans.get(0);

            if(productPlanMapper.delMrpProductPlan(mrpProductplan.getBillNo())>0){
                for (MrpProductplan productplan : mrpProductplans) {
                    productPlanMapper.addMrp_ProductPlan(productplan);
                }
                rs = 1;
            }
        }

        return rs;
    }
}
