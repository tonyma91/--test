package com.zr0817.news0817.bean;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @date 2021/3/16
 * @des: 评论类
 * @author majinming@xiaomi.com
 */
@Entity
@Table(name = "t_comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * @des: 评论人姓名
     */
    private String nickname;
    private String email;
    /**
     * @des:　评论内容
     */
    private String content;
    /**
     * @des:头像地址
     */
    private String avatar;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    private boolean adminComment;

    @ManyToOne
    private News news;

    /**
     * @des: 自己与自己建立联系，与父级
     */
    @ManyToOne
    private Comment parentComment; 

    @OneToMany(mappedBy = "parentComment")
    private List<Comment> replyComments=new ArrayList<>();


    public Comment( ) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public boolean isAdminComment() {
        return adminComment;
    }

    public void setAdminComment(boolean adminComment) {
        this.adminComment = adminComment;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public Comment getParentComment() {
        return parentComment;
    }

    public void setParentComment(Comment parentComment) {
        this.parentComment = parentComment;
    }

    public List<Comment> getReplyComments() {
        return replyComments;
    }

    public void setReplyComments(List<Comment> replyComments) {
        this.replyComments = replyComments;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", content='" + content + '\'' +
                ", avatar='" + avatar + '\'' +
                ", createTime=" + createTime +
                ", adminComment=" + adminComment +
                ", news=" + news +
                ", parentComment=" + parentComment +
                ", replyComments=" + replyComments +
                '}';
    }
}
