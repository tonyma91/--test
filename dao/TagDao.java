package com.zr0817.news0817.dao;

import com.zr0817.news0817.bean.Tag;
//import com.zr0817.news0817.bean.Type;
import com.zr0817.news0817.bean.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.util.List;

public interface TagDao extends JpaRepositoryImplementation<Tag,Long> {
    Tag findAllByName(String name);

    @Query("select t from  Tag t ")
    List<Tag> findTop(Pageable pageable);
}
