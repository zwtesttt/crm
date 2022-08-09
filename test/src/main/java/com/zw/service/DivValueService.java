package com.zw.service;

import com.zw.domain.DivValue;

import java.util.List;

public interface DivValueService {
    List<DivValue> selectDivValue(String typeCode);
}
