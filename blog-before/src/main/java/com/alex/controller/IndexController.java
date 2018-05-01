package com.alex.controller;

import com.alex.entity.vo.ArticleVO;
import com.alex.entity.vo.ResultVO;
import com.alex.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
     * 访问首页展示文章列表信息内容
     *
     * @return
     */
    @GetMapping(value = "/home")
    @ResponseBody
    public ResultVO<List<ArticleVO>> getHomeHtml(Model model,HttpServletRequest request,@RequestParam("page")String page){

        ResultVO<List<ArticleVO>> articleAll = articleService.findArticleAll(1, 10, "", "");
//        model.addAttribute("ResultVO",articleAll);

        request.getSession().setAttribute("ResultVO",articleAll);
        System.err.println(articleAll);

        return articleAll;
    }

    @GetMapping(value = "/home2")
    public String getHomeHtml2(Model model,HttpServletRequest request){

        ResultVO<List<ArticleVO>> articleAll = articleService.findArticleAll(1, 10, "", "");
//        model.addAttribute("ResultVO",articleAll);

        request.getSession().setAttribute("ResultVO",articleAll);

        return "home";
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
     * @param
     * @param
     * @return
     */
    @GetMapping(value = "/editArticle")
    public String editArticle(){

        return "editArticle";

    }



}
