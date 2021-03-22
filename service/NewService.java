package com.zr0817.news0817.service;

import com.zr0817.news0817.bean.NewQuery;
import com.zr0817.news0817.bean.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NewService {
    Page<News> listNew(Pageable pageable);

    Page<News> listNew(Pageable pageable, NewQuery newQuery);

    News saveNew(News news);

    News updateNew(News news,Long id);

    News getNew(Long id);

    void deleteNew(Long id);

    List<News> listRecommendNewTop(Integer size);

    Page<News> liseSerchNew(String query,Pageable pageable);
}
