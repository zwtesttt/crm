package com.zw.controller.workbench;

import com.zw.domain.activity;
import com.zw.domain.user;
import com.zw.gongong.domain.ResponMessage;
import com.zw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.zw.gongong.changliang.ReturnObject.RETURN_OBJECT_CODE_CG;
import static com.zw.gongong.changliang.ReturnObject.RETURN_OBJECT_CODE_SB;

@Controller
public class ActivityController {
    @Autowired
    UserService service;
    @RequestMapping("/workbench/activity/index")
    public String index(HttpServletRequest request){
        List<user> li = service.selectUsers();
        request.setAttribute("userlist",li);
        return "workbench/activity/index";
    }
    @RequestMapping("/workbench/activity/create.do")
    public Object addactivity(String owner, String name, String start_date,String end_date,String cost,String message){
        activity ac =new activity();
        ac.setOwner(owner);
        ac.setName(name);
        ac.setStart_date(start_date);
        ac.setEnd_date(end_date);
        ac.setCost(cost);
        ac.setDescription(message);
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
}
