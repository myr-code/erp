package com.myr.Service;

import com.myr.Bean.LogUser;
import com.myr.Bean.User;

public interface UserService {
    User getUserByName(User user);

    Integer log_user_add(LogUser logUser);
}
