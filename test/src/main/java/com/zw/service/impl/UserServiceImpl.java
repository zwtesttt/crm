package com.zw.service.impl;

import com.zw.dao.activityMapper;
import com.zw.dao.userMapper;
import com.zw.domain.activity;
import com.zw.domain.user;
import com.zw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("userserviceid")
public class UserServiceImpl implements UserService {
    @Autowired
    userMapper userdao;

    @Autowired
    activityMapper activity;

    @Override
    public user selectUser(Map<String, Object> map) {

        return userdao.selectUser(map);
    }
    public List<user> selectUsers(){
        return userdao.selectusers();
    }
    public int addactivity(activity record){
        return activity.addactivity(record);
    }
}
