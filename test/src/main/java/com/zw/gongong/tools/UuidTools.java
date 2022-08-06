package com.zw.gongong.tools;

import java.util.UUID;

public class UuidTools {
    public static String returnUuid(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
