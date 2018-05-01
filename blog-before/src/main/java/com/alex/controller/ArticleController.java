package com.alex.controller;

import com.alex.entity.User;
import com.alex.entity.vo.ArticleVO;
import com.alex.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Alex isidea@outlook.com
 * @create 2018-04-30 14:51
 **/
@RequestMapping(value = "/article")
@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 处理用户发表的文章信息
     *
     * @param
     * @param request
     * @return
     */
    @PostMapping(value = "/addArticle")
    @ResponseBody
    public String addArticle(@RequestBody ArticleVO articleVO, HttpServletRequest request){

        User user = (User) request.getSession().getAttribute("user");
        return "OK";

    }

}
