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

    @Override
    public int delteac(String[] des) {
        return ac.deleteactivict(des);
    }

    @Override
    public activity modiac(String id) {
        return ac.modifyact(id);
    }

    @Override
    public int updateact(activity activity) {
        return ac.updateactivity(activity);
    }

    @Override
    public List<activity> queryAllact() {
        return ac.queryAllactivity();
    }

    @Override
    public List<activity> xzqueryActivity(String[] ids) {
        return ac.xzQueryActivity(ids);
    }

    @Override
    public int insertacti(List<activity> list) {
        return ac.daoRuActivity(list);
    }

    @Override
    public activity queryActivityDetail(String id) {
        return ac.queryActivityDetail(id);
    }

    @Override
    public List<activity> queryActivityForClueId(String clueid) {
        return ac.selectActivityforClue(clueid);
    }

    @Override
    public List<activity> selectActivityByNameByClueId(Map<String, Object> map) {
        return ac.selectActivityDetailByNameByclueid(map);
    }

}
