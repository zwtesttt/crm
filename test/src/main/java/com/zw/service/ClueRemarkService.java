package com.zw.service;

import com.zw.domain.ClueRemark;

import java.util.List;

public interface ClueRemarkService {
    List<ClueRemark> selectClueRemark(String id);
    int insertClueRemark(ClueRemark clueRemark);
}
