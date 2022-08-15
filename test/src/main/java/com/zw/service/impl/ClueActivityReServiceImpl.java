package com.zw.service.impl;

import com.zw.dao.ClueActivityRelationMapper;
import com.zw.domain.ClueActivityRelation;
import com.zw.service.ClueActivityReService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("clueActivityre")
public class ClueActivityReServiceImpl implements ClueActivityReService {
    @Autowired
    private ClueActivityRelationMapper clueactivityremapper;
    @Override
    public int saveClueActivityRe(List<ClueActivityRelation> list) {
        return clueactivityremapper.saveClueActivityGuanxi(list);
    }

    @Override
    public int deleteClueActivityRelation(ClueActivityRelation relation) {
        return clueactivityremapper.removeClueActivityGuanlian(relation);
    }
}
