package com.myr.Mapper;

import com.myr.Bean.ItemType;
import com.myr.Bean.LogUser;
import com.myr.Bean.User;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper {
    //登录 根据email获取user
    User getUserByName(User user);

    //是否存在
    /*Integer isexits(User user);*/

    /**
     * 用户操作日志记录
     */
    Integer log_user_add(LogUser logUser);
}
