package com.myr.Service.finance;


import com.myr.Bean.Account;
import com.myr.Bean.Unit;
import com.myr.Mapper.UnitMapper;
import com.myr.Mapper.finance.AccountMapper;
import com.myr.Service.UnitService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
public class AccountServiceImpl implements AccountService {
    @Resource
    AccountMapper accountMapper;


    @Override
    public List<Account> Account_all() {
        return accountMapper.Account_all();
    }
}
