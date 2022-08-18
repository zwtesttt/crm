package com.zw.domain;

public class FunnelVO {
    private String name;
    private int value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "FunnelVO{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
