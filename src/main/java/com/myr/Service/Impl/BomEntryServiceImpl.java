package com.myr.Service.Impl;

import com.myr.Bean.Bomentry;
import com.myr.Mapper.BomEntryMapper;
import com.myr.Service.BomEntryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BomEntryServiceImpl implements BomEntryService {
    @Resource
    BomEntryMapper bomEntryMapper;

    @Override
    public Integer addBomEntry(Bomentry bomentry) {
        return bomEntryMapper.addBomEntry(bomentry);
    }

    @Override
    public Integer delBomEntry(Integer mid) {
        return bomEntryMapper.delBomEntry(mid);
    }

    @Override
    public List<Bomentry> getBomEntryById(Integer mid) {
        return bomEntryMapper.getBomEntryById(mid);
    }
}
