package com.myr.Mapper.finance;

import com.myr.Bean.Account;
import com.myr.Bean.Unit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AccountMapper {

    //02-所有记录
    List<Account> Account_all();

    /*//01-添加计量单位
    Integer addUnit(Unit unit);

    //获取总条数
    int getCounts_page(Map<String,Object> map);*/

    //02-序时簿
    /*List<Account> Account_page(Map<String,Object> map);

    //删除
    Integer delUnit(Integer fid);

    //获取一个
    Unit getUnitById(Integer fid);

    //更新
    Integer updateUnit(Unit unit);

    //是否存在
    Integer isexits(Unit unit);*/
}
