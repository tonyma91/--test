package com.zr0817.news0817.service;

import com.zr0817.news0817.bean.Comment;

import java.util.List;

public interface CommentService {


    List<Comment> listCommentByNewID(Long id);

    Comment saveComment(Comment comment);
}
