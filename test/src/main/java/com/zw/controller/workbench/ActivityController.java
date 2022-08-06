package com.zw.controller.workbench;

import com.zw.domain.ActivityRemark;
import com.zw.domain.activity;
import com.zw.domain.user;
import com.zw.gongong.changliang.ReturnObject;
import com.zw.gongong.domain.Activityrespon;
import com.zw.gongong.domain.ResponMessage;
import com.zw.gongong.tools.DateFormat;
import com.zw.gongong.tools.GetCellValue;
import com.zw.service.ActivityRemarkService;
import com.zw.service.UserService;
import com.zw.service.activityService;
import org.apache.logging.log4j.core.appender.OutputStreamManager;
import org.apache.logging.log4j.core.util.UuidUtil;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.print.attribute.standard.JobKOctets;
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

    @Autowired
    ActivityRemarkService acremser;
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
//            OutputStream os=new FileOutputStream("C:\\Users\\86147\\Desktop\\java\\activity.xls");
//            wb.write(os);
           // os.close();

            //设置返回值类型
            response.setContentType("application/octet-stream;charset=UTF-8");
//        获取输出流
            OutputStream out=response.getOutputStream();
            //设置响应头
            response.addHeader("Content-Disposition","attachment;filename=activity.xls");
            //读取服务器本地的excel文件（InputStream），再输出到浏览器（OutputStream）
//            InputStream in=new FileInputStream("C:\\Users\\86147\\Desktop\\java\\activity.xls");
            //设置每次读取多少个字节
//            byte[] buff=new byte[256];
//            int len=0;
//            while ((len=in.read(buff))!=-1){
//                out.write(buff,0,len);
//            }
//            in.close();
            wb.write(out);//将wb文件输出流的内容直接写入到响应输出流
            out.flush();
            wb.close();
        }
    }
    @RequestMapping("/workbench/activity/xzQueryActivity.do")
    public void xzQueryActivity(HttpServletResponse response,String[] id)throws Exception{
        List<activity> list=acser.xzqueryActivity(id);
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
        if (list!=null && list.size()>0){
            activity ac=null;
            //遍历市场活动list
            for (int i=0;i<list.size();i++) {
                ac= list.get(i);

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
            //设置返回值类型
            response.setContentType("application/octet-stream;charset=UTF-8");
//        获取输出流
            OutputStream out=response.getOutputStream();
            //设置响应头
            response.addHeader("Content-Disposition","attachment;filename=activity.xls");
            wb.write(out);//将wb文件输出流的内容直接写入到响应输出流
            out.flush();
            wb.close();
        }
    }
    /*
    * 配置springmvc的文件上传
    * */
    @RequestMapping("/workbench/activity/fileUpload.do")
    @ResponseBody
    //文件上传
    public Object fileUpload(String fileName, MultipartFile file) throws Exception {
        System.out.println("filename="+fileName);
        String fin=file.getOriginalFilename();
        file.transferTo(new File("C:\\Users\\86147\\Desktop\\java\\zuoye\\"+fin));
        ResponMessage redata=new ResponMessage();
        redata.setCode(RETURN_OBJECT_CODE_CG);
        redata.setMessage("上传成功");
        return redata;
    }
    @RequestMapping("/workbench/activity/uploadActivity.do")
    @ResponseBody
    public Object uploadActivity(MultipartFile activityfile,HttpSession session){
        ResponMessage redata=new ResponMessage();
        user user=(user) session.getAttribute(SESSION_USER);
        try {
//            String fin=activityfile.getOriginalFilename();
//            activityfile.transferTo(new File("C:\\Users\\86147\\Desktop\\java\\zuoye\\"+fin));
//
//            InputStream in = new FileInputStream("C:\\Users\\86147\\Desktop\\java\\zuoye\\"+fin);

            InputStream in=activityfile.getInputStream();

            HSSFWorkbook wb = new HSSFWorkbook(in);
            HSSFSheet sheet = wb.getSheetAt(0);
            HSSFRow row = null;
            HSSFCell cell = null;
            activity ac=null;
            String uuid="";
            List<activity> aclist=new ArrayList<>();
            for (int i=1;i<=sheet.getLastRowNum();i++){
                row=sheet.getRow(i);
                ac=new activity();
                uuid=UUID.randomUUID().toString().replaceAll("-","");
                ac.setId(uuid);
                System.out.println(uuid);
                ac.setOwner(user.getId());
                ac.setCreate_time(DateFormat.datefor(new Date()));
                ac.setCreate_by(user.getId());
                for (int k=0;k<row.getLastCellNum();k++){
                    cell=row.getCell(k);
                    //获取列中的值
                    String cellvalue=GetCellValue.getCellValue(cell);
                    if (k==0){
                        ac.setName(cellvalue);
                    }else if (k==1){
                        ac.setStart_date(cellvalue);
                    }else if (k==2){
                        ac.setEnd_date(cellvalue);
                    }else if(k==3){
                        ac.setCost(cellvalue);
                    }else if (k==4){
                        ac.setDescription(cellvalue);
                    }

                }
                aclist.add(ac);
            }
            int cgt=acser.insertacti(aclist);
            redata.setCode(RETURN_OBJECT_CODE_CG);
            redata.setMessage("成功导入"+cgt+"条数据");
        }catch (Exception e){
            e.printStackTrace();
            redata.setCode(RETURN_OBJECT_CODE_SB);
            redata.setMessage("系统忙");
        }



        return redata;
    }
    @RequestMapping("/workbench/activity/detailActivity.do")
    public String detailActivity(String id,HttpServletRequest request){
        activity act=acser.queryActivityDetail(id);
        List<ActivityRemark> lit=acremser.queryActivityRemark(id);
        System.out.println(act);
//        System.out.println(lit.get(0));
        //把数据存到request作用域
        request.setAttribute("activity",act);
        request.setAttribute("actremark",lit);

        return "workbench/activity/detail";
    }
}
