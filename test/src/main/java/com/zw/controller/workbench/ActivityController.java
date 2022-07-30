package com.zw.controller.workbench;

import com.zw.domain.activity;
import com.zw.domain.user;
import com.zw.gongong.changliang.ReturnObject;
import com.zw.gongong.domain.Activityrespon;
import com.zw.gongong.domain.ResponMessage;
import com.zw.gongong.tools.DateFormat;
import com.zw.service.UserService;
import com.zw.service.activityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

import static com.zw.gongong.changliang.ReturnObject.*;

@Controller
public class ActivityController {
    @Autowired
    UserService service;
    @Autowired
    activityService acser;
    @RequestMapping("/workbench/activity/index")
    public String index(HttpServletRequest request){
        List<user> li = service.selectUsers();
        request.setAttribute("userlist",li);
        return "workbench/activity/index";
    }
    @RequestMapping(value = "/workbench/activity/create.do",method = RequestMethod.POST)
    @ResponseBody
    public Object addactivity(activity ac, HttpSession session){
        String uuid= UUID.randomUUID().toString().replaceAll("-","");
        ac.setId(uuid);
        ac.setCreate_time(DateFormat.datefor(new Date()));
        user h=(user)session.getAttribute(ReturnObject.SESSION_USER);
        ac.setCreate_by(h.getId());
        int i = service.addactivity(ac);
        ResponMessage re = new ResponMessage();
        if (i!=0){
            re.setCode(RETURN_OBJECT_CODE_CG);
            re.setMessage("创建成功");
        }else {
            re.setCode(RETURN_OBJECT_CODE_SB);
            re.setMessage("创建失败");
        }
        return re;
    }
    @RequestMapping("/workbench/activity/chaxunac")
    @ResponseBody
    public Object chaxunac(String name,String owner,String start_date,String end_date,Integer start_flg,Integer pageflg){
        Map<String,Object> map=new HashMap<>();
        map.put("name",name);
        map.put("owner",owner);
        map.put("start_date",start_date);
        map.put("end_date",end_date);
        map.put("start_flg",(start_flg-1)*pageflg);
        map.put("pageflg",pageflg);
        List<activity> ali=acser.selectActivity(map);
        int act=acser.recount(map);
        Activityrespon re=new Activityrespon();
        re.setActivityList(ali);
        re.setCounts(String.valueOf(act));
        return re;
    }
    @RequestMapping("/workbench/activity/delteAct.do")
    @ResponseBody
    public Object delteAct(String[] id){
        ResponMessage reo=new ResponMessage();
        try{
            int resu=acser.delteac(id);
            if(resu>0){
                reo.setCode(RETURN_OBJECT_CODE_CG);
                reo.setMessage("删除成功");
            }else {
                reo.setCode(RETURN_OBJECT_CODE_SB);
                reo.setMessage("系统忙，请联系系统管理员");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return reo;
    }
}
