package com.zr0817.news0817.service;

import com.zr0817.news0817.bean.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//import java.awt.print.Pageable;
import java.util.List;

public interface TypeService {
    Page<Type> listType(Pageable pageable);
    Type saveType(Type type);
    Type getTypeByName(String name);
    Type getType(Long id);
    Type updateType(Long id,Type type);
    void deleteById(Long id);

    List<Type> listType();

    List<Type> listTypeTop(Integer size);
}
