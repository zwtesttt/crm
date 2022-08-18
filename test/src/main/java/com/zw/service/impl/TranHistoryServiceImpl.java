package com.zw.service.impl;

import com.zw.dao.TranHistoryMapper;
import com.zw.domain.TranHistory;
import com.zw.service.TranHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("TranHistoryService")
public class TranHistoryServiceImpl implements TranHistoryService {
    @Autowired
    private TranHistoryMapper tranHistoryMapper;
    @Override
    public List<TranHistory> queryTranHistory(String id) {
        return tranHistoryMapper.selectTranHitstory(id);
    }
}
