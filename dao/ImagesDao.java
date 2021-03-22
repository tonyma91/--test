package com.zr0817.news0817.dao;

import com.zr0817.news0817.bean.Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ImagesDao extends JpaRepository <Images,Long>, JpaSpecificationExecutor<Images> {
}
