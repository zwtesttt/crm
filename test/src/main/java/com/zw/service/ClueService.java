package com.zw.service;

import com.zw.domain.Clue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface ClueService {

     int insertClue(Clue clue);
     List<Clue> selectClue(Map<String,Object> map);
     int selectCon(Map<String,Object> map);
     Clue selectClueDetail(String id);
}
