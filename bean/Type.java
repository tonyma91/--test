package com.zr0817.news0817.bean;

import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_type")
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "分类名称不能为空")
    private String name;
    //一对多的关系//mappedBy不需要生成中间表
    @OneToMany(mappedBy = "type")
    private List<News> news=new ArrayList<>();

    public Type(){};

    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
