package com.myr.Service.Impl.othermodel;


import com.myr.Bean.Bomentry;
import com.myr.Bean.Unit;
import com.myr.Bean.othermodel.OtherModel;
import com.myr.Bean.othermodel.OtherModelEntry;
import com.myr.Mapper.UnitMapper;
import com.myr.Mapper.othermodel.OtherModelMapper;
import com.myr.Service.UnitService;
import com.myr.Service.othermodel.OtherModelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
public class OtherModelServiceImpl implements OtherModelService {
    @Resource
    OtherModelMapper otherModelMapper;

    @Override
    @Transactional
    public Integer addOtherModel(OtherModel otherModel,List<OtherModelEntry> modelList) {
        Integer count = otherModelMapper.addOtherModel(otherModel);//1、更新头
        if(count>0){
            for (OtherModelEntry model : modelList) {
                model.setMid(otherModel.getFid());
                otherModelMapper.addOtherModelEntry(model);
            }
        }else{
            return 0;
        }
        return 1;
    }

    @Override
    public int getCounts(Map<String, Object> map) {
        return otherModelMapper.getCounts(map);
    }

    @Override
    public List<OtherModel> OTHER_MODEL_Index(Map<String, Object> map) {
        return otherModelMapper.OTHER_MODEL_Index(map);
    }

    @Override
    public List<OtherModelEntry> GetDataByid(Integer fid) {
        return otherModelMapper.GetDataByid(fid);
    }
}
