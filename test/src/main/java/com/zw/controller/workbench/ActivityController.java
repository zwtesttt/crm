package com.zw.controller.workbench;

import com.zw.domain.activity;
import com.zw.domain.user;
import com.zw.gongong.changliang.ReturnObject;
import com.zw.gongong.domain.ResponMessage;
import com.zw.gongong.tools.DateFormat;
import com.zw.service.UserService;
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
}
