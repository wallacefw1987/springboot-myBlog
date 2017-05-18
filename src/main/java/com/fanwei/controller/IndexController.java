package com.fanwei.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.java2d.pipe.SpanIterator;

/**
 * 登录controller
 * Created by Administrator on 2017/4/29.
 */
@Controller
public class IndexController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/main")
    public String main(){
        return "main";
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }
}
