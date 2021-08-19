package com.myr.Service.Impl;

import com.myr.Bean.MrpProductplan;
import com.myr.Mapper.mrp.Mrp_ProductPlanMapper;
import com.myr.Service.ProductPlanService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ProductPlanServiceImpl implements ProductPlanService {
    @Resource
    Mrp_ProductPlanMapper productPlanMapper;

    @Override
    public Integer addMrp_ProductPlan(MrpProductplan mrpProductplan) {
        return productPlanMapper.addMrp_ProductPlan(mrpProductplan);
    }

    @Override
    public String getBillNo(String dates) {
        return productPlanMapper.getBillNo(dates);
    }

    @Override
    public int getCounts(Map<String, Object> map) {
        return productPlanMapper.getCounts(map);
    }

    @Override
    public List<MrpProductplan> Mrp_ProductPlan_page(Map<String, Object> map) {
        return productPlanMapper.Mrp_ProductPlan_page(map);
    }
}
