package com.zw.controller.workbench;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    @RequestMapping("/workbench/main/index")
    public String mainindex(){
        return "/workbench/main/index";
    }
}
