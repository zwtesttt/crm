package com.zw.gongong.domain;

import com.zw.domain.Clue;

import java.util.List;

public class ClueRespon {
    List<Clue> lit;
    String count;

    public List<Clue> getLit() {
        return lit;
    }

    public void setLit(List<Clue> lit) {
        this.lit = lit;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
