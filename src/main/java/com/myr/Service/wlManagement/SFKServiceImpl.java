package com.myr.Service.wlManagement;

import com.myr.Bean.Dz;
import com.myr.Bean.Sk;
import com.myr.Mapper.ICStockBillEntryMapper;
import com.myr.Mapper.wlManagement.SFKMapper;
import com.myr.Mapper.wlManagement.SaleOutDZMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
public class SFKServiceImpl implements SFKService {
    @Resource
    SFKMapper sfkMapper;

    @Override
    public Integer addSFK(Sk sf) {
        return null;
    }

    @Override
    public String getBillNo_SK(String dates) {
        return null;
    }

    @Override
    public String getBillNo_FK(String dates) {
        return null;
    }

    @Override
    public int getCounts_index_SK(Map<String, Object> map) {
        return 0;
    }

    @Override
    public int getCounts_index_FK(Map<String, Object> map) {
        return 0;
    }

    @Override
    public List<Sk> SK_index(Map<String, Object> map) {
        return null;
    }

    @Override
    public List<Sk> FK_index(Map<String, Object> map) {
        return null;
    }

    @Override
    public List<Sk> getSFKById(int fid) {
        return null;
    }

    @Override
    public Integer SFK_del(String billNo) {
        return null;
    }
}
