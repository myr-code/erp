package com.myr.Service.wlManagement;

import com.myr.Bean.Dz;
import com.myr.Bean.SFK;
import com.myr.Mapper.wlManagement.SFKMapper;
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
    @Transactional
    public Integer addSFK(List<SFK> sks) {
        int count = 0;
        for (SFK sk : sks) {
            if(sfkMapper.addSFK(sk)>0){
                count++;
            }else{
                return  -1;//有至少一条不成功
            }
        }
        return count;
    }

    @Override
    public String getBillNo_SK(String dates) {
        return sfkMapper.getBillNo_SK(dates);
    }

    @Override
    public String getBillNo_FK(String dates) {
        return sfkMapper.getBillNo_FK(dates);
    }

    @Override
    public int getCounts_index_SK(Map<String, Object> map) {
        return sfkMapper.getCounts_index_SK(map);
    }

    @Override
    public int getCounts_index_FK(Map<String, Object> map) {
        return sfkMapper.getCounts_index_FK(map);
    }

    @Override
    public List<SFK> SK_index(Map<String, Object> map) {
        return sfkMapper.SK_index(map);
    }

    @Override
    public List<SFK> FK_index(Map<String, Object> map) {
        return sfkMapper.FK_index(map);
    }

    @Override
    public List<SFK> getSFKById(int fid) {
        return sfkMapper.getSFKById(fid);
    }

    @Override
    public Integer SFK_del(String billNo) {
        return sfkMapper.SFK_del(billNo);
    }

    @Override
    @Transactional
    public Integer SFK_update(List<SFK> sfks) {
        int rs = 0;
        SFK sfk = new SFK();
        if(sfks.size()>0&&sfks!=null){
            sfk = sfks.get(0);

            if(sfkMapper.SFK_del(sfk.getBillNo())>0){
                for (SFK sfk1 : sfks) {
                    sfkMapper.addSFK(sfk1);
                }
                rs = 1;
            }
        }

        return rs;
    }
}
