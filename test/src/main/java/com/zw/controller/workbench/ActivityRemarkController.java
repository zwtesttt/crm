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
            if (ss>0){
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
    @RequestMapping("/workbench/activity/deleteActivityRemark.do")
    @ResponseBody
    public Object deleteActivityRemark(String id){
        ResponMessage reobj=new ResponMessage();
        try {
            int stu = acremrkser.delectActivityRemark(id);
            if (stu>0){
                reobj.setCode(ReturnObject.RETURN_OBJECT_CODE_CG);
                reobj.setMessage("删除成功");
            }else {
                reobj.setCode(ReturnObject.RETURN_OBJECT_CODE_SB);
                reobj.setMessage("删除失败，请重试");
            }
        }catch (Exception e){
            e.printStackTrace();
            reobj.setCode(ReturnObject.RETURN_OBJECT_CODE_SB);
            reobj.setMessage("删除失败，请重试");
        }
        return reobj;
    }

    @RequestMapping("/workbench/activity/updateActivityRemark.do")
    @ResponseBody
    public Object updateActivityRemark(ActivityRemark remark,HttpSession session){
        remark.setEdit_time(DateFormat.datefor(new Date()));
        user u1 = (user)session.getAttribute(ReturnObject.SESSION_USER);
        remark.setEdit_by(u1.getId());
        remark.setEdit_flag("1");
        ResponMessage reobj=new ResponMessage();
        try {
            int stu=acremrkser.updateActivityRemark(remark);
            if (stu>0){
                reobj.setCode(ReturnObject.RETURN_OBJECT_CODE_CG);
                reobj.setMessage("修改成功");
                reobj.setXiangy(remark);
            }else {
                reobj.setCode(ReturnObject.RETURN_OBJECT_CODE_SB);
                reobj.setMessage("修改失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            reobj.setCode(ReturnObject.RETURN_OBJECT_CODE_SB);
            reobj.setMessage("修改失败");
        }
       return reobj;
    }
}
