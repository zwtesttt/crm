package com.zw.service.impl;

import com.zw.dao.TranRemarkMapper;
import com.zw.domain.TranRemark;
import com.zw.service.TranRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("tranRemarkService")
public class TranRemarkServiceImpl implements TranRemarkService {
    @Autowired
    private TranRemarkMapper tranRemarkMapper;
    @Override
    public List<TranRemark> queryTranRemarks(String id) {
        return tranRemarkMapper.selectTranRemark(id);
    }
}
