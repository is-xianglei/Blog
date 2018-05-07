package com.alex.controller;

import com.alex.entity.Type;
import com.alex.entity.vo.ArticleVO;
import com.alex.entity.vo.HotArticle;
import com.alex.entity.vo.ResultVO;
import com.alex.service.ArticleService;
import com.alex.service.HotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    @Autowired
    private HotService hotArticle;

    /**
     * 访问首页展示文章列表信息内容
     * @return
     */
    @GetMapping(value = "/index")
    @ResponseBody
    public ResultVO<List<ArticleVO>> getHomeHtml(Model model,HttpServletRequest request,@RequestParam(value = "page",defaultValue = "1")Integer page){

        ResultVO<List<ArticleVO>> articleAll = articleService.findArticleAll(page, 6, "", "");
        return articleAll;
    }

    /**
     * 显示热门文章
     *
     * @return
     */
    @GetMapping(value = "/index/hotArticle")
    @ResponseBody
    public List<HotArticle> getHotArticle(){
        List<HotArticle> articleVOS = hotArticle.hotArticle();
        return articleVOS;
    }

    /**
     * 转发到home页面
     * @return
     */
    @GetMapping(value = "/")
    public String getHomeHtml2(){
        return "home";
    }

    /**
     * 展示发表文章页面
     * @param
     * @param
     * @return
     */
    @GetMapping(value = "/editArticle")
    public String editArticle(Model model){
        List<Type> typeList = articleService.getTypeList();
        model.addAttribute("typeList",typeList);

        return "editArticle";

    }

    /**
     * 转发到文章专栏页面
     *
     * @return
     */
    @GetMapping(value = "/article")
    public String getArticleHtml(){

        return "article";
    }



}
