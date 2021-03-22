package com.zr0817.news0817.dao;

import com.zr0817.news0817.bean.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentDao extends JpaRepository<Comment ,Long> {
    List<Comment> findByNewsIdAndParentCommentNull(Long id, Sort sort);
}
