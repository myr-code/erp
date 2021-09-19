package com.myr.Service.Impl;

import com.myr.Bean.MrpProductplan;
import com.myr.Bean.MrpPurReq;
import com.myr.Mapper.mrp.Mrp_ProductPlanMapper;
import com.myr.Mapper.mrp.PurReqMapper;
import com.myr.Service.ProductPlanService;
import com.myr.Service.PurReqService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class PurReqServiceImpl implements PurReqService {
    @Resource
    PurReqMapper purReqMapper;

    @Override
    public Integer add_PurReq(List<MrpPurReq> mrpPurReqs) {
        for (MrpPurReq mrpPurReq : mrpPurReqs) {
            purReqMapper.add_PurReq(mrpPurReq);
        }
        return 1;
    }

    @Override
    public String getBillNo(String dates) {
        return purReqMapper.getBillNo(dates);
    }

    @Override
    public int getCounts(Map<String, Object> map) {
        return purReqMapper.getCounts(map);
    }

    @Override
    public List<MrpPurReq> PurReq_page(Map<String, Object> map) {
        return purReqMapper.PurReq_page(map);
    }

    @Override
    public List<MrpPurReq> getPurReqById(Integer mid) {
        return purReqMapper.getPurReqById(mid);
    }

    @Override
    public Integer delPurReq(String billNo) {
        return purReqMapper.delPurReq(billNo);
    }
}
