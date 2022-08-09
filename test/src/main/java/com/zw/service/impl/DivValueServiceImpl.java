package com.zw.service.impl;

import com.zw.domain.DivValue;
import com.zw.service.DivValueService;
import com.zw.dao.DivValueMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("DivValueService")
public class DivValueServiceImpl implements DivValueService {
    @Autowired
    private DivValueMapper divValuemapper;
    @Override
    public List<DivValue> selectDivValue(String typeCode) {
        return divValuemapper.selectDivValue(typeCode);
    }
}
