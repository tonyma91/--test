package com.zr0817.news0817.dao;

import com.zr0817.news0817.bean.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface TypeDao extends JpaRepository<Type,Long> {

    Type findAllByName(String name);

    @Query("select t from  Type t ")
    List<Type>  findTop(Pageable pageable);
}
