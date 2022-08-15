package com.zw.service.impl;

import com.zw.dao.ClueRemarkMapper;
import com.zw.domain.ClueRemark;
import com.zw.service.ClueRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("clueremarkservice")
public class ClueRemarkServiceImpl implements ClueRemarkService {
    @Autowired
    private ClueRemarkMapper cluemapper;
    @Override
    public List<ClueRemark> selectClueRemark(String id) {
        return cluemapper.selectClueRemark(id);
    }

    @Override
    public int insertClueRemark(ClueRemark clueRemark) {
        return cluemapper.insertClueRemark(clueRemark);
    }
}
