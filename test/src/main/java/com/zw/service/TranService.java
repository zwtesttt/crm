package com.zw.service;

import com.zw.domain.Tran;
import jdk.dynalink.linker.LinkerServices;

import java.util.List;
import java.util.Map;

public interface TranService {
    void saveTran(Map<String,Object> map);

    List<Tran> queryAllTran();
}
