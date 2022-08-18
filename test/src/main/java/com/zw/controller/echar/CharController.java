package com.zw.controller.echar;

import com.zw.domain.FunnelVO;
import com.zw.service.TranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CharController {
    @Autowired
    private TranService tranService;

    @RequestMapping("workbench/chart/transaction/toIndex")
    public String toIndex(){
        return "workbench/chart/transaction/index";
    }


    @RequestMapping("workbench/chart/transaction/queryCountTran.do")
    @ResponseBody
    public Object queryCountTran(){
        List<FunnelVO> list=tranService.selectTranCount();

        return list;
    }
}
