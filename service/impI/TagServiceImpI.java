package com.zr0817.news0817.service.impI;

import com.zr0817.news0817.bean.Tag;
import com.zr0817.news0817.dao.TagDao;
import com.zr0817.news0817.service.TagService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpI implements TagService {
    @Autowired
    private TagDao tagDao;


    @Override
    public Page<Tag> listTag(Pageable pageable) {
        return tagDao.findAll(pageable);
    }

    @Override
    public Tag saveTag(Tag tag) {
        return tagDao.save(tag);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagDao.findAllByName(name);
    }

    @Override
    public Tag getTag(Long id) {
        return tagDao.findById(id).orElse(null);
    }

    @Override
    public Tag updateTag(Long id, Tag tag) {
        Tag tag1 =tagDao.findById(id).orElse(null);
        if (tag1==null){
            System.out.println("未获得更新对象");
            return null;
        }
        BeanUtils.copyProperties(tag,tag1);
        return tagDao.save(tag1);

    }

    @Override
    public void deleteById(Long id) {
        tagDao.deleteById(id);
    }

    @Override
    public List<Tag> listTag() {
        return tagDao.findAll();
    }

    @Override
    public List<Tag> listTag(String ids) {
        return tagDao.findAllById(converToList(ids));
    }

    @Override
    public List<Tag> listTypeTop(Integer size) {
        Sort sort=Sort.by(Sort.Direction.DESC,"newsList.size");
        Pageable pageable= PageRequest.of(0,size,sort);
        return tagDao.findTop(pageable);
    }

    private List<Long> converToList(String ids){
        List<Long> listLong = new ArrayList<>();
        if (!"".equals(ids)&&ids!=null){
            String[] idArry =ids.split(",");
            for (int i=0;i<idArry.length;i++){
                listLong.add(new Long(idArry[i]));
            }
        }
        return listLong;
    }
}
