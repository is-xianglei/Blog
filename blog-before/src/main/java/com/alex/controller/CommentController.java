package com.alex.controller;

import com.alex.entity.Comment;
import com.alex.entity.User;
import com.alex.service.CommentService;
import com.alex.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * 评论区
 * @author zhangzhe
 **/
@Controller
@RequestMapping(value = "/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 提交评论
     * @param content
     * @param articleId
     * @return
     */
    @PostMapping(value = "/addComment")
    public String addComment(@RequestParam("commentContent") String content, @RequestParam("articleId")String articleId, HttpSession session){
        User user = (User) session.getAttribute("user");
        Comment com = new Comment();
        com.setArticle_id(articleId);
        com.setContent(content);
        com.setId(UUIDUtils.getUUID());
        com.setUser_id(user.getId());
        com.setCreate_data(new Date());

        int n = commentService.addComment(com);
        //评论成功把评轮信息返回出去
        if(0 < n){
            return "redirect:/article/articleContent?articleId="+articleId;
        }

        return "redirect:/article/articleContent?articleId="+articleId;
    }

}
