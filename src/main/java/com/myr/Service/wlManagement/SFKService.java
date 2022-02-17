package com.myr.Service.wlManagement;

import com.myr.Bean.Dz;
import com.myr.Bean.SFK;

import java.util.List;
import java.util.Map;

public interface SFKService {
    //01-添加
    Integer addSFK(List<SFK> sks);

    //获取系统单号  收款单
    String getBillNo_SK(String dates);

    //获取系统单号  付款单
    String getBillNo_FK(String dates);

    //获取总条数 收款单
    int getCounts_index_SK(Map<String,Object> map);

    //获取总条数 付款单
    int getCounts_index_FK(Map<String,Object> map);

    //序时簿 收款单
    List<SFK> SK_index(Map<String,Object> map);

    //序时簿 收款单
    List<SFK> FK_index(Map<String,Object> map);

    //get一个对象
    List<SFK> getSFKById(int fid);

    //删除
    Integer SFK_del(String billNo);

    //更新
    Integer SK_update(List<SFK> sfks);
}
