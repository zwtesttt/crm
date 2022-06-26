package com.zw.gongong.domain;

import com.zw.domain.activity;

import java.util.List;

public class Activityrespon {
    List<activity> activityList;
    String counts;

    public List<activity> getActivityList() {
        return activityList;
    }

    public void setActivityList(List<activity> activityList) {
        this.activityList = activityList;
    }

    public String getCounts() {
        return counts;
    }

    public void setCounts(String counts) {
        this.counts = counts;
    }
}
