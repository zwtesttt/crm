package com.zw.service;

import com.zw.domain.ClueActivityRelation;

import java.util.List;

public interface ClueActivityReService {
    int saveClueActivityRe(List<ClueActivityRelation> list);
    int deleteClueActivityRelation(ClueActivityRelation relation);
}
