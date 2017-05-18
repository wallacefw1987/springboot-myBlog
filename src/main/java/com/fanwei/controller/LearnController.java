package com.fanwei.controller;

import com.alibaba.fastjson.JSONObject;
import com.fanwei.service.LearnService;
import com.fanwei.tools.Page;
import com.fanwei.tools.ServletUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * springboot教程学习Controller
 * Created by Administrator on 2017/5/6.
 */
@Controller
@RequestMapping("/learn")
public class LearnController {
    @Autowired
    private LearnService learnServiceImpl;
    private Logger logger = LoggerFactory.getLogger(LearnController.class);

    @RequestMapping
    public String learn(){
        return "learn-resource";
    }

    @RequestMapping(value = "/queryLeanList",method = RequestMethod.POST)
    @ResponseBody
    public void queryLeanList(HttpServletRequest request, HttpServletResponse response){
        String page = request.getParameter("page");//获取当前页
        String rows = request.getParameter("rows");//获取每页行数，这里使用jqgrid自身参数
        String author = request.getParameter("author");
        String title = request.getParameter("title");
        Map<String,Object> parames = new HashMap<>();
        parames.put("page",page);parames.put("rows",rows);parames.put("author",author);parames.put("title",title);
        Page pageObj = learnServiceImpl.queryLearnResourceList(parames);
        List<Map<String,Object>> learnList = pageObj.getResultList();
        JSONObject jo = new JSONObject();
        jo.put("rows",learnList);jo.put("total",pageObj.getTotalPages());jo.put("records",pageObj.getTotalRows());
        ServletUtil.createSuccessResponse(200,jo,response);
    }
}
