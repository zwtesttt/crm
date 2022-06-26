package com.zw.service.impl;

import com.zw.dao.activityMapper;
import com.zw.domain.activity;
import com.zw.service.activityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("activiservice")
public class activityServiceImpl implements activityService {
    @Autowired
    activityMapper ac;
    @Override
    public List<activity> selectActivity(Map<String, Object> map) {
        return ac.queryactivity(map);
    }

    @Override
    public int recount(Map<String, Object> map) {
        return ac.selectCon(map);
    }

}
