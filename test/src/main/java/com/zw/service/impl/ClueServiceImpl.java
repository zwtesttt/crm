package com.zw.service.impl;

import com.zw.dao.ClueMapper;
import com.zw.domain.Clue;
import com.zw.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("clueservice")
public class ClueServiceImpl implements ClueService {
    @Autowired
    private ClueMapper cluemapper;
    @Override
    public int insertClue(Clue clue) {
        return cluemapper.insertClue(clue);
    }

    @Override
    public List<Clue> selectClue(Map<String,Object> map) {
        return cluemapper.selectClue(map);
    }

    @Override
    public int selectCon(Map<String, Object> map) {
        return cluemapper.selectCon(map);
    }

    @Override
    public Clue selectClueDetail(String id) {
        return cluemapper.selectClueDetail(id);
    }
}
