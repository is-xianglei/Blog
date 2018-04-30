package com.alex.controller;

import com.alex.entity.vo.ArticleVO;
import com.alex.entity.vo.ResultVO;
import com.alex.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping(value = "/{url}")
    public String getHtml(@PathVariable("url")String url){
        log.info("【开始请求，请求路径为】:{}",url);
        return url;
    }

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



}
