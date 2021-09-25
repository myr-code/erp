package com.myr.Service.Impl;

import com.myr.Bean.MrpBackmater;
import com.myr.Bean.MrpProductplan;
import com.myr.Mapper.mrp.BackMaterMapper;
import com.myr.Mapper.mrp.Mrp_ProductPlanMapper;
import com.myr.Service.BackMaterService;
import com.myr.Service.ProductPlanService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
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
    public List<MrpBackmater> getBackMaterById(Integer mid) {
        return backMaterMapper.getBackMaterById(mid);
    }

    @Override
    public Integer delBackMater(String billNo) {
        return backMaterMapper.delBackMater(billNo);
    }

    @Override
    @Transactional
    public Integer BackMater_update(MrpBackmater mrpBackmater, List<MrpBackmater> mrpBackmaters) {
        int rs = 0;
        if(mrpBackmaters.size()>0&&mrpBackmaters!=null){
            mrpBackmater = mrpBackmaters.get(0);

            if(backMaterMapper.delBackMater(mrpBackmater.getBillNo())>0){
                for (MrpBackmater backmater : mrpBackmaters) {
                    backMaterMapper.add_BackMater(backmater);
                }
                rs = 1;
            }
        }

        return rs;
    }
}
