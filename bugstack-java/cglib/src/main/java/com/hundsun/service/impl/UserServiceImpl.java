package com.hundsun.service.impl;

import com.hundsun.service.IUserService;

public class UserServiceImpl implements IUserService {
    @Override
    public String queryUserNameById(String userId) {
        return "hi user " + userId;
    }
}
