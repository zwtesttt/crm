package com.zw.service;

import com.zw.domain.user;

import java.util.Map;

public interface UserService {
    user selectUser(Map<String,Object> map);
}
