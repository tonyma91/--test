package com.zr0817.news0817.service.impI;

import com.zr0817.news0817.bean.Comment;
import com.zr0817.news0817.dao.CommentDao;
import com.zr0817.news0817.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class CommentServiceImpI implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Override
    public List<Comment> listCommentByNewID(Long id) {
        Sort sort=Sort.by(("createTime"));
        //查询各个顶级的评论
        List<Comment> commentList=commentDao.findByNewsIdAndParentCommentNull(id,sort);
        return eachComment(commentList);
    }
    //发布评论
    @Override
    public Comment saveComment(Comment comment) {
        Long parentCommentId=comment.getParentComment().getId();
        if (parentCommentId!=-1){
            comment.setParentComment(commentDao.findById(parentCommentId).orElse(null));
        }else{
            comment.setParentComment(null);
        }
        comment.setCreateTime(new Date());
        return commentDao.save(comment);
    }

    private List<Comment> eachComment(List<Comment> comments){
        List<Comment> commentView=new ArrayList<>();
        for(Comment comment:comments){
            Comment comment1=new Comment();
            BeanUtils.copyProperties(comment,comment1);
            commentView.add(comment1);
        }//合并各评论的各层子代到第一及子代的集合中
        combineChildren(commentView);
        return commentView;
    }

//    private List<Comment> tempReplys= new ArrayList<>();//作为一个保存各个子评论的链

//    private void combineChildren(List<Comment> comments){
//        for (Comment comment:comments){
//            List<>
//        }
//    }

    private List<Comment> temReplys=new ArrayList<>();  //作为一个保存各个子评论的链


//回复评论//顶级回复
private void combineChildren(List<Comment> comments)
{
    for (Comment comment:comments)
    {
        List<Comment> replys1=comment.getReplyComments();
        for(Comment reply1:replys1)
        {
            //循环迭代，找出子代，再临时temreplies中
            recursively(reply1);
        }
        comment.setReplyComments(temReplys);
        temReplys=new ArrayList<>();//清空临时存放区
    }
}




    //循环迭代找是否有子代回复的回复
    private void recursively(Comment comment) {
        temReplys.add(comment);
        if (comment.getReplyComments().size() > 0) {
            List<Comment> replies = comment.getReplyComments();
            for (Comment reply : replies) {
                temReplys.add(reply);
                if (reply.getReplyComments().size() > 0) {
                    recursively(reply);
                }
            }
        }

    }

}
