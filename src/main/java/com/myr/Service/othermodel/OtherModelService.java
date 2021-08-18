package com.myr.Service.othermodel;

import com.myr.Bean.Unit;
import com.myr.Bean.othermodel.OtherModel;
import com.myr.Bean.othermodel.OtherModelEntry;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OtherModelService {
    //01-添加
    Integer addOtherModel(OtherModel otherModel,List<OtherModelEntry> modelList);

    //获取总条数
    int getCounts(Map<String,Object> map);

    //序时簿
    List<OtherModel> OTHER_MODEL_Index(Map<String,Object> map);

    List<OtherModelEntry> GetDataByid(Integer fid);

}
