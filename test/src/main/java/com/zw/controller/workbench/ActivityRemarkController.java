package com.zw.controller.workbench;

import com.zw.domain.ActivityRemark;
import com.zw.domain.user;
import com.zw.gongong.changliang.ReturnObject;
import com.zw.gongong.domain.ResponMessage;
import com.zw.gongong.tools.DateFormat;
import com.zw.gongong.tools.UuidTools;
import com.zw.service.ActivityRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class ActivityRemarkController {
    @Autowired
    private ActivityRemarkService acremrkser;

    @RequestMapping("/workbench/activity/addActivityRemark.do")
    @ResponseBody
    public Object addActivityRemark(ActivityRemark remark, HttpSession session){
        user u1 =(user)session.getAttribute(ReturnObject.SESSION_USER);
        remark.setId(UuidTools.returnUuid());
        remark.setCreate_time(DateFormat.datefor(new Date()));
        remark.setCreate_by(u1.getId());
        remark.setEdit_flag(ReturnObject.ACTIVITYREMARK_EDIT_FLAG_NOTEDIT);
        ResponMessage resp=new ResponMessage();
        try {
            int ss=acremrkser.insertActivityRemark(remark);
            if (ss==1){
                resp.setCode(ReturnObject.RETURN_OBJECT_CODE_CG);
                resp.setXiangy(remark);
            }else {
                resp.setCode(ReturnObject.RETURN_OBJECT_CODE_SB);
                resp.setMessage("添加备注失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            resp.setCode(ReturnObject.RETURN_OBJECT_CODE_SB);
            resp.setMessage("添加备注失败");
        }

        return resp;
    }
}
