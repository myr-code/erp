package com.myr.Service.Impl;


import com.myr.Bean.LogUser;
import com.myr.Bean.User;
import com.myr.Mapper.UserMapper;
import com.myr.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User getUserByName(User user) {
        return userMapper.getUserByName(user);
    }

    @Override
    public Integer log_user_add(LogUser logUser) {
        return userMapper.log_user_add(logUser);
    }
}
