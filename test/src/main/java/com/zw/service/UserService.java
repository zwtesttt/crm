package com.zw.service;

import com.zw.domain.activity;
import com.zw.domain.user;

import java.util.List;
import java.util.Map;

public interface UserService {
    user selectUser(Map<String,Object> map);
    List<user> selectUsers();
    int addactivity(activity record);
}
