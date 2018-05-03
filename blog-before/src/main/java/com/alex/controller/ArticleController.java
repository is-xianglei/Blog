package com.alex.controller;

import com.alex.entity.User;
import com.alex.entity.vo.ArticleVO;
import com.alex.service.ArticleService;
import com.alex.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
     * @param
     * @param request
     * @return
     */
    @PostMapping(value = "/addArticle")
    @ResponseBody
    public String addArticle(@RequestBody ArticleVO articleVO, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        //模拟数据
        ArticleVO article = new ArticleVO();
        article.setArticleId(UUIDUtils.getUUID());
        article.setUserId(user.getId());
        article.setContent(articleVO.getContent());
        article.setTitle("测试文章标题1");
        article.setType_id("4");
        System.out.println(article);
        int num = articleService.addArticle(article);
        if(0 < num){
            return "添加成功";
        }
        return "添加失败";

    }

    /**
     * 查看文章内容
     * @param articleId 文章id
     * @return
     */
    @RequestMapping("/articleContent")
    public String articleContent(String articleId, Model model){

        ArticleVO articleContent = articleService.selectByArticleId(articleId);

        model.addAttribute("articleContent", articleContent);

        return "detail";
    }

}
