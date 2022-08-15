package com.zw.controller.workbench;

import com.zw.domain.*;
import com.zw.gongong.changliang.ReturnObject;
import com.zw.gongong.domain.ClueRespon;
import com.zw.gongong.domain.ResponMessage;
import com.zw.gongong.tools.DateFormat;
import com.zw.gongong.tools.UuidTools;
import com.zw.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.print.attribute.standard.JobKOctets;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class ClueController {
    @Autowired
    private DivValueService divser;
    @Autowired
    private ClueService clueser;
    @Autowired
    private UserService userser;
    @Autowired
    private ClueRemarkService clueremarkser;

    @Autowired
    private activityService actservice;

    @Autowired
    ClueActivityReService cars;

    @RequestMapping("/workbench/clue/index")
    public String index(HttpServletRequest request){
//        调用service方法
        List<user> list=userser.selectUsers();
        List<DivValue> appellationlist=divser.selectDivValue("appellation");
        List<DivValue> cluestatelist=divser.selectDivValue("clueState");
        List<DivValue> sourcelist=divser.selectDivValue("source");
        request.setAttribute("userList",list);
        request.setAttribute("appellationList",appellationlist);
        request.setAttribute("clueStateList",cluestatelist);
        request.setAttribute("sourceList",sourcelist);
        return "workbench/clue/index";

    }
    @RequestMapping("/workbench/clue/saveClue.do")
    @ResponseBody
    public Object saveClue(Clue clue, HttpSession session){

        clue.setId(UuidTools.returnUuid());
        clue.setCreate_time(DateFormat.datefor(new Date()));
        user u1=(user) session.getAttribute(ReturnObject.SESSION_USER);
        clue.setCreate_by(u1.getId());

        ResponMessage re=new ResponMessage();
        try{
            int stu=clueser.insertClue(clue);
            if (stu>0){
                re.setCode(ReturnObject.RETURN_OBJECT_CODE_CG);
                re.setMessage("添加成功");
            }else {
                re.setCode(ReturnObject.RETURN_OBJECT_CODE_SB);
                re.setMessage("添加失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            re.setCode(ReturnObject.RETURN_OBJECT_CODE_SB);
            re.setMessage("添加失败");
        }
        return re;
    }
    @RequestMapping("/workbench/clue/chaxunclue.do")
    @ResponseBody
    public Object chaxunclue(String fullname,String owner,String company,String phone,String mphone,String source,String state,Integer start_flg,Integer pageflg){
        Map<String,Object> map = new HashMap<>();
        map.put("fullname",fullname);
        map.put("owner",owner);
        map.put("company",company);
        map.put("phone",phone);
        map.put("mphone",mphone);
        map.put("source",source);
        map.put("state",state);
        map.put("start_flg",(start_flg-1)*pageflg);
        map.put("pageflg",pageflg);
        List<Clue> lit=clueser.selectClue(map);
        int cont=clueser.selectCon(map);
        ClueRespon re=new ClueRespon();
        re.setLit(lit);
        re.setCount(String.valueOf(cont));
        return re;

    }
    @RequestMapping("/workbench/clue/detailClue.do")
    public String detailClue(String id,HttpServletRequest request){
        Clue clue=clueser.selectClueDetail(id);
        List<ClueRemark> clueremarklist=clueremarkser.selectClueRemark(id);
        List<activity> activitylist=actservice.queryActivityForClueId(id);
        request.setAttribute("clue",clue);
        request.setAttribute("clueRemarklist",clueremarklist);
        request.setAttribute("activityList",activitylist);
        return "workbench/clue/detail";
    }
    @RequestMapping("/workbench/clue/queryActivityByNameByClueId.do")
    @ResponseBody
    public Object queryActivityByNameByClueId(String activiName,String clueId){
        Map<String,Object> map=new HashMap<>();
        map.put("activityName",activiName);
        map.put("clueId",clueId);
        List<activity> lit = actservice.selectActivityByNameByClueId(map);
        return lit;
    }
    @RequestMapping("/workbench/clue/saveClueActivityRe.do")
    @ResponseBody
    public Object saveClueActivityRe(String[] activityId,String clueId){
        ClueActivityRelation aclue=null;
        List<ClueActivityRelation> list=new ArrayList<>();
        ResponMessage re=new ResponMessage();
        for (String obj: activityId) {
            aclue=new ClueActivityRelation();
            aclue.setId(UuidTools.returnUuid());
            aclue.setClue_id(clueId);
            aclue.setActivity_id(obj);
            list.add(aclue);
        }
        try {
            int stu= cars.saveClueActivityRe(list);

            if (stu>0){
                List<activity> acts=actservice.selectActivityForDetailByids(activityId);
                re.setCode(ReturnObject.RETURN_OBJECT_CODE_CG);
                re.setXiangy(acts);
                re.setMessage("保存成功");
            }else {
                re.setCode(ReturnObject.RETURN_OBJECT_CODE_SB);
                re.setMessage("保存失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            re.setCode(ReturnObject.RETURN_OBJECT_CODE_SB);
            re.setMessage("保存失败");
        }
        return re;
    }
    @RequestMapping("/workbench/clue/deleteBund.do")
    @ResponseBody
    public Object deleteBund(ClueActivityRelation relation){
        ResponMessage re=new ResponMessage();
        try{
            int des=cars.deleteClueActivityRelation(relation);
            if (des>0){
                re.setCode(ReturnObject.RETURN_OBJECT_CODE_CG);
                re.setMessage("删除成功");
            }else{
                re.setCode(ReturnObject.RETURN_OBJECT_CODE_SB);
                re.setMessage("删除失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            re.setCode(ReturnObject.RETURN_OBJECT_CODE_SB);
            re.setMessage("删除失败");
        }
        return re;
    }
//    跳转到转换线索页面
    @RequestMapping("/workbench/clue/ClueTrart.do")
    public String ClueTrart(String id,HttpServletRequest request){
        Clue clue=clueser.selectClueDetail(id);
        request.setAttribute("cluetra",clue);
        List<DivValue> divlist=divser.selectDivValue("stage");
        request.setAttribute("stageList",divlist);
        return "workbench/clue/convert";
    }
    @RequestMapping("/workbench/clue/returnActivityByClueId.do")
    @ResponseBody
    public Object returnActivityByClueId(String actitiName,String clueId){
        Map<String,Object> map=new HashMap<>();
        map.put("activiName",actitiName);
        map.put("clueId",clueId);
        List<activity> li=actservice.returnActivityForDetail(map);
        return li;
    }
    @RequestMapping("/workbench/clue/convertClue.do")
    @ResponseBody
    public Object convertClue(String clueId,String money,String name,String expectedDate,String stage,String activityId,String isConvert,HttpSession session){
        Map<String,Object> map=new HashMap<>();
        map.put("clueId",clueId);
        map.put("money",money);
        map.put("name",name);
        map.put("expectedDate",expectedDate);
        map.put("stage",stage);
        map.put("activityId",activityId);
        map.put("isConvert",isConvert);
        user user=(user)session.getAttribute(ReturnObject.SESSION_USER);
        map.put(ReturnObject.SESSION_USER,user);
        ResponMessage re=new ResponMessage();
        try {
            clueser.saveConvert(map);
            re.setCode(ReturnObject.RETURN_OBJECT_CODE_CG);
        }catch (Exception e){
            e.printStackTrace();
            re.setCode(ReturnObject.RETURN_OBJECT_CODE_SB);
            re.setMessage("系统忙，请重试");
        }

        return re;
    }
    @RequestMapping("/workbench/clue/addClueRemark.do")
    @ResponseBody
    public Object addClueRemark(ClueRemark clueRemark,HttpSession session){
        clueRemark.setId(UuidTools.returnUuid());
        clueRemark.setEdit_flag("0");
        user u1=(user) session.getAttribute(ReturnObject.SESSION_USER);
        clueRemark.setCreate_time(DateFormat.datefor(new Date()));
        clueRemark.setCreate_by(u1.getId());
        ResponMessage re=new ResponMessage();
        try {
            int stu=clueremarkser.insertClueRemark(clueRemark);
            if (stu>0){
                re.setCode(ReturnObject.RETURN_OBJECT_CODE_CG);
                re.setXiangy(clueRemark);
                re.setMessage("添加成功");
            }else {
                re.setCode(ReturnObject.RETURN_OBJECT_CODE_SB);
                re.setMessage("添加失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            re.setCode(ReturnObject.RETURN_OBJECT_CODE_SB);
            re.setMessage("添加失败");
        }

        return re;
    }
}
