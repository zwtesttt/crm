package com.zw.service;

import com.zw.domain.activity;

import java.util.List;
import java.util.Map;

public interface activityService {
    List<activity> selectActivity(Map<String,Object> map);
    int recount(Map<String,Object> map);
    int delteac(String[] des);
}
