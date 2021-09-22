package com.myr.Service.Impl;

import com.myr.Bean.MrpBackmater;
import com.myr.Bean.MrpProductplan;
import com.myr.Mapper.mrp.BackMaterMapper;
import com.myr.Mapper.mrp.Mrp_ProductPlanMapper;
import com.myr.Service.BackMaterService;
import com.myr.Service.ProductPlanService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class BackMaterServiceImpl implements BackMaterService {
    @Resource
    BackMaterMapper backMaterMapper;


    @Override
    public Integer add_BackMater(List<MrpBackmater> mrpBackmater) {
        for (MrpBackmater backmater : mrpBackmater) {
            backMaterMapper.add_BackMater(backmater);
        }
        return 1;
    }

    @Override
    public String getBillNo(String dates) {
        return backMaterMapper.getBillNo(dates);
    }

    @Override
    public int getCounts(Map<String, Object> map) {
        return backMaterMapper.getCounts(map);
    }

    @Override
    public List<MrpBackmater> BackMater_page(Map<String, Object> map) {
        return backMaterMapper.BackMater_page(map);
    }

    @Override
    public List<MrpProductplan> getMrp_ProductPlanById(Integer mid) {
        return null;
    }

    @Override
    public Integer delMrpProductPlan(String billNo) {
        return null;
    }
}
