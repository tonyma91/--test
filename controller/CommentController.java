package com.zr0817.news0817.controller;


import com.zr0817.news0817.bean.Comment;
import com.zr0817.news0817.bean.News;
import com.zr0817.news0817.bean.User;
import com.zr0817.news0817.service.CommentService;
import com.zr0817.news0817.service.NewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
public class CommentController {

    private final static String avtar="https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3489820115,2512136291&fm=26&gp=0.jpg";

    @Autowired
    private CommentService commentService;

    @Autowired
    private NewService newService;

    @GetMapping("/comments/{newId}")
    public String comments(@PathVariable Long newId, Model model){
        List<Comment> commentList = commentService.listCommentByNewID(newId);
        model.addAttribute("comments",commentList);
        return "new :: commentList";
    }


    /**
     * @date 2021/3/16
     * @des: 评论创建
     * @author majinming@xiaomi.com
     */
    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");

        User user=(User) session.getAttribute("user");
        Long newId=comment.getNews().getId();
        if(user!=null){
            comment.setNickname(user.getNickname());
            comment.setEmail(user.getEmail());
            News news = newService.getNew(newId);
            /**
             * @des: 判断是不是博主
             */
            if (user.getId() == news.getUser().getId()){
                comment.setAdminComment(true);
            }else {
                comment.setAdminComment(false);
            }
            comment.setNews(news);
            /**
             * @des: 暂时直接使用固定的头像，后期进行拓展
             */
            comment.setAvatar(avtar);
            comment.setCreateTime(new Date());
            commentService.saveComment(comment);
        }else{
            //请登录后进行评论
            response.getWriter().write("<script>alert('请登录后进行评论！！');</script>");
            response.getWriter().flush();
        }
        return "redirect:/comments/"+newId;
    }

}
