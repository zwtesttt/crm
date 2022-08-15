package com.zw.service;

import com.zw.domain.Clue;
import com.zw.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface ClueService {

     int insertClue(Clue clue);
     List<Clue> selectClue(Map<String,Object> map);
     int selectCon(Map<String,Object> map);
     Clue selectClueDetail(String id);
     void saveConvert(Map<String,Object> map);


}
