package com.alex.controller;

import com.alex.entity.User;
import com.alex.entity.vo.ArticleVO;
import com.alex.entity.vo.ResultVO;
import com.alex.enums.ResultEnum;
import com.alex.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Alex isidea@outlook.com
 * @Date: 2018/4/27 0027 16:08
 */
@RequestMapping("/")
@Slf4j
@Controller
public class IndexController {

    @Autowired
    private ArticleService articleService;

    /**
     * 首页面展示文章列表数据
     * @param page
     * @param limit
     * @param search
     * @param type
     * @return
     */
    @GetMapping("/index")
    @ResponseBody
    public ResultVO<List<ArticleVO>> index(@RequestParam(name ="page",required=false,defaultValue="1")Integer page,
                      @RequestParam(name = "limit",required=false,defaultValue="10")Integer limit,
                      @RequestParam(name = "search",required=false)String search,
                      @RequestParam(name = "type",required=false)String type){
        ResultVO<List<ArticleVO>> articleAll = articleService.findArticleAll(page, limit, search, type);
        log.info("【获取文章数据列表】:{}",articleAll);
        return articleAll;
    }

    /**
     * 访问首页展示文章列表信息内容
     *
     * @return
     */
    @GetMapping(value = "/home")
    public ModelAndView getHomeHtml(){

        ResultVO<List<ArticleVO>> articleAll = articleService.findArticleAll(1, 10, "", "");
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("ResultVO",articleAll);

        System.err.println(articleAll);

        return modelAndView;
    }

    /**
     * 展示文章的详细信息
     *
     * @return
     */
    @GetMapping(value = "/detail")
    public ModelAndView getDetail(){

        ModelAndView modelAndView = new ModelAndView("detail");
        return modelAndView;

    }

    /**
     * 展示发表文章页面
     *
     * @param request
     * @param modelAndView
     * @return
     */
    @GetMapping(value = "/editArticle")
    public ModelAndView editArticle(HttpServletRequest request,ModelAndView modelAndView){

        User user2 = new User();
        user2.setId("666666");

        request.getSession().setAttribute("user",user2);

        User user = (User) request.getSession().getAttribute("user");

        if(null == user){

            Map<String,String> map = new HashMap<>();
            map.put("msg",ResultEnum.LOGIN_EXCEPTION.getMessage());
            modelAndView.setViewName("login");
            modelAndView.addObject(map);
            return modelAndView;
        }

        modelAndView.setViewName("editArticle");
        return modelAndView;

    }



}
