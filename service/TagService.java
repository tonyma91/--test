package com.zr0817.news0817.service;

import com.zr0817.news0817.bean.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagService {
    Page<Tag> listTag(Pageable pageable);
    Tag saveTag(Tag tag);
    Tag getTagByName(String name);
    Tag getTag(Long id);
    Tag updateTag(Long id,Tag tag);
    void deleteById(Long id);

    List<Tag> listTag();

    List<Tag> listTag(String ids);

    List<Tag> listTypeTop(Integer size);
}
