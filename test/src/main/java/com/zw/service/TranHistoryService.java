package com.zw.service;

import com.zw.domain.TranHistory;

import java.util.List;

public interface TranHistoryService {
    List<TranHistory> queryTranHistory(String id);
}
