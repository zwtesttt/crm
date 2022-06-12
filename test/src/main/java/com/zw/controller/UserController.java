package com.zw.controller;

import com.zw.domain.user;
import com.zw.gongong.changliang.ReturnObject;
import com.zw.gongong.domain.ResponMessage;
import com.zw.gongong.tools.DateFormat;
import com.zw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    UserService service;
    @RequestMapping("/settings/qx/user/tologin")
    public String userLogin(){
        return "settings/qx/user/login";
    }


    @RequestMapping("/settings/qx/user/login.do")
    @ResponseBody
    public Object userDoLogin(HttpSession session, HttpServletRequest request, HttpServletResponse response, String username, String passwd, String islogin){
        Map<String,Object> map = new HashMap<>();
        map.put("login_act",username);
        map.put("login_pwd",passwd);
        ResponMessage re=new ResponMessage();
        //运行service方法，返回用户对象
        user u = service.selectUser(map);
        if(u==null){
            re.setCode(ReturnObject.RETURN_OBJECT_CODE_SB);
            re.setMessage("用户不存在或密码错误");
            System.out.println("用户不存在或密码错误");
        }else{
            //将用户账号的过期时间和当前时间进行相减，判断是否过期

            if(DateFormat.datefor(new Date()).compareTo(u.getExpire_time())<0){
                re.setCode(ReturnObject.RETURN_OBJECT_CODE_SB);
                re.setMessage("以过期");
                //判断用户账号的状态是否可用
            }else if("0".equals(u.getLock_state())){
                re.setCode(ReturnObject.RETURN_OBJECT_CODE_SB);
                re.setMessage("您的账号已经被锁定");
                //判断用户登录是的ip是否在允许访问的ip里
                //因为有取反，所以如果结果是包含的话返回false，不包含才返回true进入代码块
            } else if (!u.getAllow_ips().contains(request.getRemoteAddr())) {
                re.setCode(ReturnObject.RETURN_OBJECT_CODE_SB);
                re.setMessage("您的ip已被拉入黑名单");
            }else {
                re.setCode(ReturnObject.RETURN_OBJECT_CODE_CG);
                re.setMessage("登录成功");
                //把登录成功的用户对象存到会话域中
                session.setAttribute(ReturnObject.SESSION_USER,u);
                //判断是否勾选十天免登录
                if ("true".equals(islogin)){
                    //将cookie发送到浏览器
                    Cookie co1=new Cookie("username",username);
                    //设置cookie的时间
                    co1.setMaxAge(60*60*24*10);//以秒为单位60*60*24*10表示有效期10天
                    response.addCookie(co1);
                    Cookie co2=new Cookie("passwd",passwd);
                    //设置cookie的时间
                    co2.setMaxAge(60*60*24*10);
                    response.addCookie(co2);
                }else {
                    //如果再次登录时未勾选则将cookie的时间设置为0，浏览器自动删除
                    Cookie co1=new Cookie("username","1");
                    //设置cookie的时间
                    co1.setMaxAge(0);//以秒为单位60*60*24*10表示有效期10天
                    response.addCookie(co1);
                    Cookie co2=new Cookie("passwd","1");
                    //设置cookie的时间
                    co2.setMaxAge(0);
                    response.addCookie(co2);
                }
            }
        }
        return re;
    }
}
