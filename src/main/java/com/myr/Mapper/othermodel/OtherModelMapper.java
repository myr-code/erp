package com.myr.Mapper.othermodel;

import com.myr.Bean.othermodel.OtherModel;
import com.myr.Bean.othermodel.OtherModelEntry;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface OtherModelMapper {
    //01-添加
    Integer addOtherModel(OtherModel otherModel);

    //01-行添加
    Integer addOtherModelEntry(OtherModelEntry otherModelEntry);

    //获取总条数
    int getCounts(Map<String,Object> map);

    //序时簿
    List<OtherModel> OTHER_MODEL_Index(Map<String,Object> map);

    //获取响应详情
    List<OtherModelEntry> GetDataByid(Integer fid);

}
