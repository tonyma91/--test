package com.zr0817.news0817.service.impI;

import com.zr0817.news0817.bean.Type;
import com.zr0817.news0817.dao.TypeDao;
import com.zr0817.news0817.service.TypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.parser.Part;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpI implements TypeService {

    @Autowired
    private TypeDao typeDao;

    @Override
    public Page<Type> listType(Pageable pageable){
        return typeDao.findAll(pageable);
    }

    @Override
    public Type saveType(Type type) {
        return typeDao.save(type);
    }

    @Override
    public Type getTypeByName(String name) {

        return typeDao.findAllByName(name);
    }

    @Override
    public Type getType(Long id) {
        return typeDao.findById(id).orElse(null);
    }

    @Override
    public Type updateType(Long id, Type type) {
        Type type1=typeDao.findById(id).orElse(null);


        if (type1==null){
            System.out.println("未");
            return null;
        }
        BeanUtils.copyProperties(type,type1);
        return typeDao.save(type1);
    }

    @Override
    public void deleteById(Long id) {
        typeDao.deleteById(id);
    }

    @Override
    public List<Type> listType() {
        return typeDao.findAll();
    }

    @Override
    public List<Type> listTypeTop(Integer size) {
        Sort sort=Sort.by(Sort.Direction.DESC,"news.size");
        Pageable pageable= PageRequest.of(0,size,sort);
        return typeDao.findTop(pageable);
    }
}
