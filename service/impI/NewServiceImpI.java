package com.zr0817.news0817.service.impI;

import com.zr0817.news0817.bean.NewQuery;
import com.zr0817.news0817.bean.News;
import com.zr0817.news0817.dao.NewDao;
import com.zr0817.news0817.service.NewService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class NewServiceImpI implements NewService {

    @Autowired
    private NewDao newDao;

    @Override
    public Page<News> listNew(Pageable pageable) {
        return newDao.findAll(pageable);
    }

    @Override
    public Page<News> listNew(Pageable pageable, NewQuery newQuery) {
        return newDao.findAll(new Specification<News>() {  //Specification查询构造器
            @Override
            public Predicate toPredicate(Root<News> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates =new ArrayList<>();
                if (!"".equals(newQuery.getTitle())&&newQuery.getTitle()!=null){
                    predicates.add(criteriaBuilder.like(root.get("title"),"%"+newQuery.getTitle()+"%")) ;
                }
                if (newQuery.getTypeId()!=null){
                    predicates.add(criteriaBuilder.equal(root.get("type").get("id"),newQuery.getTypeId()));
                }
                if(newQuery.isRecommend()){//判断是否被推荐（是被推荐的）
                    predicates.add(criteriaBuilder.equal(root.get("recommend"),newQuery.isRecommend()));
                }
                criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        },pageable);
    }

    @Override
    public News saveNew(News news) {
        if (news.getId()==null){
            news.setCreatTime(new Date());
            news.setUpdateTime(new Date());
        }

        return newDao.save(news);
    }

    @Override
    public News updateNew(News news, Long id) {
        News news1=newDao.findById(id).orElse(null);
        if (news1==null){
            System.out.println("未获得更新对象");
        }
        BeanUtils.copyProperties(news,news1);
        news1.setUpdateTime(new Date());
        return newDao.save(news1);
    }

    @Override
    public News getNew(Long id) {
        return newDao.findById(id).orElse(null);
    }

    @Override
    public void deleteNew(Long id) {
        newDao.deleteById(id);
    }

    @Override
    public List<News> listRecommendNewTop(Integer size) {
        Sort sort=Sort.by(Sort.Direction.DESC,"updateTime");
        Pageable pageable= PageRequest.of(0,size,sort);
        return newDao.findTop(pageable);
    }

    @Override
    public Page<News> liseSerchNew(String query, Pageable pageable) {
        return newDao.findByQuery(query,pageable);
    }
}
