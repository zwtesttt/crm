package com.zw.controller.workbench;

import com.zw.domain.*;
import com.zw.gongong.changliang.ReturnObject;
import com.zw.gongong.domain.ResponMessage;
import com.zw.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.PushBuilder;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

@Controller
public class TranController {
    @Autowired
    private DivValueService divValueService;

    @Autowired
    private UserService userService;

    @Autowired
    private TranService tranService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private TranRemarkService tranRemarkService;

    @Autowired
    private TranHistoryService tranHistoryService;

    @RequestMapping("/workbench/tran/index.do")
    public String index(HttpServletRequest request){
//        调用service
        List<DivValue> trantype=divValueService.selectDivValue("transactionType");
        List<DivValue> source=divValueService.selectDivValue("source");
        List<DivValue> stageList=divValueService.selectDivValue("stage");

        request.setAttribute("stageList",stageList);
        request.setAttribute("transactionType",trantype);
        request.setAttribute("sourceList",source);

        return "workbench/transaction/index";
    }
    @RequestMapping("/workbench/tran/tosave.do")
    public String toCreate(HttpServletRequest request){
        //        调用service
        List<DivValue> trantype=divValueService.selectDivValue("transactionType");
        List<DivValue> source=divValueService.selectDivValue("source");
        List<DivValue> stageList=divValueService.selectDivValue("stage");
        List<user> userlist=userService.selectUsers();

        request.setAttribute("userlist",userlist);
        request.setAttribute("stageList",stageList);
        request.setAttribute("transactionType",trantype);
        request.setAttribute("sourceList",source);

        return "workbench/transaction/save";
    }
    @RequestMapping("/workbench/tran/keNengXingReturn.do")
    @ResponseBody
    public Object keNengXingReturn(String stageValue){
        ResourceBundle refile=ResourceBundle.getBundle("stage");
        String knx=refile.getString(stageValue);
        return knx;
    }
    @RequestMapping("/workbench/tran/queryTranCustomer.do")
    @ResponseBody
    public Object queryTranCustomer(String custName){
        List<String> list=customerService.queryAllCustomer(custName);
        return list;
    }
    @RequestMapping("/workbench/tran/saveTran.do")
    @ResponseBody
    public Object saveTran(@RequestParam Map<String,Object> map, HttpSession session){
        user u1=(user)session.getAttribute(ReturnObject.SESSION_USER);
        map.put(ReturnObject.SESSION_USER,u1);
        ResponMessage re=new ResponMessage();
        try {
            tranService.saveTran(map);
            re.setCode(ReturnObject.RETURN_OBJECT_CODE_CG);
        }catch (Exception e){
            e.printStackTrace();
            re.setCode(ReturnObject.RETURN_OBJECT_CODE_SB);
            re.setMessage("系统繁忙请重试");
        }

        return re;
    }
    @RequestMapping("/workbench/tran/queryAllTran.do")
    @ResponseBody
    public Object queryAllTran(){
        List<Tran> list=tranService.queryAllTran();

        return list;
    }
    @RequestMapping("/workbench/tran/detailTran.do")
    public String detailTran(String id,HttpServletRequest request){
        Tran tran=tranService.queryTranDetail(id);
        List<TranRemark> remarklist=tranRemarkService.queryTranRemarks(id);
        List<TranHistory> historyList=tranHistoryService.queryTranHistory(id);

//        从配置文件获取可能性
        ResourceBundle bundle=ResourceBundle.getBundle("stage");
        String knx= bundle.getString(tran.getStage());
        tran.setKnx(knx);


        request.setAttribute("tran",tran);
        request.setAttribute("tranremark",remarklist);
        request.setAttribute("tranhistory",historyList);

//        调用service方法查询交易所有的阶段
        List<DivValue> list=divValueService.selectDivValue("stage");
        request.setAttribute("stage",list);


        return "workbench/transaction/detail";
    }
}
