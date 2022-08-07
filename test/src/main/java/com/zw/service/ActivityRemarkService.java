package com.zw.service;

import com.zw.domain.ActivityRemark;

import java.util.List;

public interface ActivityRemarkService {
    List<ActivityRemark> queryActivityRemark(String id);
    int insertActivityRemark(ActivityRemark remark);
    int delectActivityRemark(String id);
    int updateActivityRemark(ActivityRemark remark);
}
