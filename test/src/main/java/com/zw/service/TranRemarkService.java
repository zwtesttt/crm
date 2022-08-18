package com.zw.service;

import com.zw.domain.Tran;
import com.zw.domain.TranRemark;

import java.util.List;

public interface TranRemarkService {
    List<TranRemark> queryTranRemarks(String id);
}
