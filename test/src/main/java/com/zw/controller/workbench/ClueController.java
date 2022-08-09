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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}
