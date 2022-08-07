package com.zw.service.impl;

import com.zw.dao.ActivityRemarkMapper;
import com.zw.domain.ActivityRemark;
import com.zw.service.ActivityRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("activityRemarkservice")
public class ActivityRemarkServiceImpl implements ActivityRemarkService {
    @Autowired
    private ActivityRemarkMapper acm;
    @Override
    public List<ActivityRemark> queryActivityRemark(String id) {
        return acm.queryActiviRemarks(id);
    }

    @Override
    public int insertActivityRemark(ActivityRemark remark) {
        return acm.insertActivityRemark(remark);
    }

    @Override
    public int delectActivityRemark(String id) {

        return acm.deleteActivityRemark(id);
    }

    @Override
    public int updateActivityRemark(ActivityRemark remark) {
        return acm.updateActivityRemark(remark);
    }
}
