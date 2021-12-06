package com.myr.Service.Impl;

import com.myr.Bean.MrpProductplan;
import com.myr.Bean.MrpPurReq;
import com.myr.Mapper.mrp.Mrp_ProductPlanMapper;
import com.myr.Mapper.mrp.PurReqMapper;
import com.myr.Service.ProductPlanService;
import com.myr.Service.PurReqService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    public int getCounts_sour(Map<String, Object> map) {
        return purReqMapper.getCounts_sour(map);
    }

    @Override
    public List<MrpPurReq> MrpPurReq_sour(Map<String, Object> map) {
        return purReqMapper.MrpPurReq_sour(map);
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
    public Set<String> PurReq_isQuoted(String billNo) {
        return purReqMapper.PurReq_isQuoted(billNo);
    }

    @Override
    @Transactional
    public Integer delPurReq(String billNo) {
        int count = 0;
        Set<String> strings = purReqMapper.PurReq_isQuoted(billNo);
        if(strings.size()>0){
            //负数 单据被引用
            count = strings.size()*-1;
        }else{
            //删除 正数 单据被删除
            count = purReqMapper.delPurReq(billNo);
        }

        return count;
    }

    @Override
    @Transactional
    public Integer PurReq_update(MrpPurReq mrpPurReq, List<MrpPurReq> mrpPurReqs) {
        int rs = 0;
        if(mrpPurReqs.size()>0&&mrpPurReqs!=null){
            mrpPurReq = mrpPurReqs.get(0);

            if(purReqMapper.delPurReq(mrpPurReq.getBillNo())>0){
                for (MrpPurReq purReq : mrpPurReqs) {
                    purReqMapper.add_PurReq(purReq);
                }
                rs = 1;
            }

        }
        return rs;
    }
}
