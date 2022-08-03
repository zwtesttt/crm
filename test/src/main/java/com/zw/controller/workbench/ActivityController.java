package com.zw.controller.workbench;

import com.zw.domain.activity;
import com.zw.domain.user;
import com.zw.gongong.changliang.ReturnObject;
import com.zw.gongong.domain.Activityrespon;
import com.zw.gongong.domain.ResponMessage;
import com.zw.gongong.tools.DateFormat;
import com.zw.service.UserService;
import com.zw.service.activityService;
import org.apache.logging.log4j.core.appender.OutputStreamManager;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.http.HttpResponse;
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
    @RequestMapping("/workbench/activity/modify.do")
    @ResponseBody
    public Object modifyactivity(String id){
        activity reac=acser.modiac(id);
        return reac;
    }
    @RequestMapping("/workbench/activity/savemodify.do")
    @ResponseBody
    public Object saveupdateact(activity ac,HttpSession session){
        user us=(user)session.getAttribute(SESSION_USER);
//        封装参数
        ac.setEdit_time(DateFormat.datefor(new Date()));
        ac.setEdit_by(us.getId());

        ResponMessage rebo=new ResponMessage();
        try {
            int stu=acser.updateact(ac);
            if (stu>0){
                rebo.setCode(RETURN_OBJECT_CODE_CG);
                rebo.setMessage("修改成功");
            }else {
                rebo.setCode(RETURN_OBJECT_CODE_SB);
                rebo.setMessage("修改失败，请重试");
            }
        }catch (Exception e){
            e.printStackTrace();
            rebo.setCode(RETURN_OBJECT_CODE_SB);
            rebo.setMessage("修改失败，请重试");
        }
        return rebo;
    }
    @RequestMapping("/workbench/activity/filedownload.do")
    //接收下载文件的请求
    public void filedownload(HttpServletResponse response) throws IOException {
        //设置返回值类型
        response.setContentType("application/octet-stream;charset=UTF-8");
//        获取输出流
        OutputStream out=response.getOutputStream();
        //设置响应头
        response.addHeader("Content-Disposition","attachment;filename=stu.xls");
        //读取服务器本地的excel文件（InputStream），再输出到浏览器（OutputStream）
        InputStream in=new FileInputStream("C:\\Users\\86147\\Desktop\\java\\stu.xls");
        //设置每次读取多少个字节
        byte[] buff=new byte[256];
        int len=0;
        while ((len=in.read(buff))!=-1){
            out.write(buff,0,len);
        }
        in.close();
        out.flush();
    }
    //接收导出所有的市场活动请求
    @RequestMapping("/workbench/activity/exportAllActivity.do")
    public void exportAllActivity(HttpServletResponse response) throws Exception{
        List<activity> lis=acser.queryAllact();
        HSSFWorkbook wb=new HSSFWorkbook();
        HSSFSheet sh= wb.createSheet("市场活动");
        HSSFRow ro=sh.createRow(0);
        HSSFCell cell=ro.createCell(0);
        cell.setCellValue("ID");
        cell=ro.createCell(1);
        cell.setCellValue("所有者");
        cell=ro.createCell(2);
        cell.setCellValue("名称");
        cell=ro.createCell(3);
        cell.setCellValue("开始日期");
        cell=ro.createCell(4);
        cell.setCellValue("结束日期");
        cell=ro.createCell(5);
        cell.setCellValue("成本");
        cell=ro.createCell(6);
        cell.setCellValue("描述");
        cell=ro.createCell(7);
        cell.setCellValue("创建时间");
        cell=ro.createCell(8);
        cell.setCellValue("创建者");
        cell=ro.createCell(9);
        cell.setCellValue("修改时间");
        cell=ro.createCell(10);
        cell.setCellValue("修改者");
//      判断市场活动是否为空
        if (lis!=null && lis.size()>0){
            activity ac=null;
            //遍历市场活动list
            for (int i=0;i<lis.size();i++) {
                ac= lis.get(i);

                ro=sh.createRow(i+1);
                cell=ro.createCell(0);
                cell.setCellValue(ac.getId());
                cell=ro.createCell(1);
                cell.setCellValue(ac.getOwner());
                cell=ro.createCell(2);
                cell.setCellValue(ac.getName());
                cell=ro.createCell(3);
                cell.setCellValue(ac.getStart_date());
                cell=ro.createCell(4);
                cell.setCellValue(ac.getEnd_date());
                cell=ro.createCell(5);
                cell.setCellValue(ac.getCost());
                cell=ro.createCell(6);
                cell.setCellValue(ac.getDescription());
                cell=ro.createCell(7);
                cell.setCellValue(ac.getCreate_time());
                cell=ro.createCell(8);
                cell.setCellValue(ac.getCreate_by());
                cell=ro.createCell(9);
                cell.setCellValue(ac.getEdit_time());
                cell=ro.createCell(10);
                cell.setCellValue(ac.getEdit_by());
            }
//            根据wb创建excel文件
            OutputStream os=new FileOutputStream("C:\\Users\\86147\\Desktop\\java\\activity.xls");
            wb.write(os);
            os.close();
            wb.close();
            //设置返回值类型
            response.setContentType("application/octet-stream;charset=UTF-8");
//        获取输出流
            OutputStream out=response.getOutputStream();
            //设置响应头
            response.addHeader("Content-Disposition","attachment;filename=activity.xls");
            //读取服务器本地的excel文件（InputStream），再输出到浏览器（OutputStream）
            InputStream in=new FileInputStream("C:\\Users\\86147\\Desktop\\java\\activity.xls");
            //设置每次读取多少个字节
            byte[] buff=new byte[256];
            int len=0;
            while ((len=in.read(buff))!=-1){
                out.write(buff,0,len);
            }
            in.close();
            out.flush();
        }



    }
}
