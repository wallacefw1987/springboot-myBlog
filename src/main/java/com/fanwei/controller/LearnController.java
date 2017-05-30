package com.fanwei.controller;

import com.alibaba.fastjson.JSONObject;
import com.fanwei.pojo.LearnResource;
import com.fanwei.service.LearnService;
import com.fanwei.tools.Page;
import com.fanwei.tools.ServletUtil;
import com.fanwei.tools.StringUtil;
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

    /**
     * 分页展示
     * @param request
     * @param response
     */
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

    /**
     * 添加教程
     * @param request
     * @param response
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public void addLearn(HttpServletRequest request,HttpServletResponse response){
        JSONObject result = new JSONObject();
        String author = request.getParameter("author");
        String title = request.getParameter("title");
        String url = request.getParameter("url");
        if (StringUtil.isNull(author)){
            result.put("message","作者不能为空!");
            result.put("flag",false);
            ServletUtil.createSuccessResponse(200,result,response);
            return;
        }
        if (StringUtil.isNull(title)){
            result.put("message","标题不能为空!!");
            result.put("flag",false);
            ServletUtil.createSuccessResponse(200,result,response);
            return;
        }
        if (StringUtil.isNull(url)){
            result.put("message","url不能为空!!!");
            result.put("flag",false);
            ServletUtil.createSuccessResponse(200,result,response);
            return;
        }
        LearnResource resource = new LearnResource();
        resource.setAuthor(author);
        resource.setTitle(title);
        resource.setUrl(url);

        Integer index = learnServiceImpl.add(resource);
        System.out.println("index is :"+index);
        if (index > 0){
            result.put("message","添加成功");
            result.put("flag",true);
        }else {
            result.put("message","添加失败");
            result.put("flag",false);
        }
        ServletUtil.createSuccessResponse(200,result,response);
    }

    /**
     * 修改教程
     * @param request
     * @param response
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public void updateLearn(HttpServletRequest request,HttpServletResponse response){
        JSONObject result = new JSONObject();
        String id = request.getParameter("id");
        String author = request.getParameter("author");
        String title = request.getParameter("title");
        String url = request.getParameter("url");
        LearnResource resource = learnServiceImpl.queryLearnResourceById(Integer.valueOf(id));
        if (StringUtil.isNull(author)){
            result.put("message","作者不能为空!");
            result.put("flag",false);
            ServletUtil.createSuccessResponse(200,result,response);
            return;
        }
        if (StringUtil.isNull(title)){
            result.put("message","标题不能为空!!");
            result.put("flag",false);
            ServletUtil.createSuccessResponse(200,result,response);
            return;
        }
        if (StringUtil.isNull(url)){
            result.put("message","url不能为空!!!");
            result.put("flag",false);
            ServletUtil.createSuccessResponse(200,result,response);
            return;
        }
        resource.setAuthor(author);
        resource.setTitle(title);
        resource.setUrl(url);
        int index = learnServiceImpl.update(resource);
        if (index>0){
            result.put("message","教程信息修改成功");
            result.put("flag",true);
        }else {
            result.put("message","教程信息修改失败");
            result.put("flag",false);
        }
        ServletUtil.createSuccessResponse(200,result,response);
    }

    /**
     * 删除教程
     * @param request
     * @param response
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public void deleteLearn(HttpServletRequest request,HttpServletResponse response){
        JSONObject result = new JSONObject();
        String ids = request.getParameter("ids");
        System.out.println("-------- ids="+ids);
        int index = learnServiceImpl.deleteByIds(ids);
        if (index>0){
            result.put("message","教程信息删除成功");
            result.put("flag",true);
        }else {
            result.put("message","教程信息删除失败");
            result.put("flag",false);
        }
        ServletUtil.createSuccessResponse(200,result,response);
    }
}
