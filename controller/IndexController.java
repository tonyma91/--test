package com.zr0817.news0817.controller;


import com.zr0817.news0817.bean.News;
import com.zr0817.news0817.bean.resultBean.NewsDTO;
import com.zr0817.news0817.service.NewService;
import com.zr0817.news0817.service.TagService;
import com.zr0817.news0817.service.TypeService;
import com.zr0817.news0817.service.utilsService.ImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private NewService newService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @Autowired
    private ImagesService imagesService;



    @GetMapping("/")
    public  String index(@PageableDefault(size = 3,sort = {"updateTime"},direction = Sort.Direction.DESC)
                                     Pageable pageable, Model model){
        model.addAttribute("page",newService.listNew(pageable));
        model.addAttribute("types",typeService.listTypeTop(3));
        model.addAttribute("tags",tagService.listTypeTop(2));
        model.addAttribute("recommendNews",newService.listRecommendNewTop(3));
        return "index";
    }

    @GetMapping("/search")
    public String search(@PageableDefault(size = 2,sort = {"updateTime"},direction = Sort.Direction.DESC)
                                 Pageable pageable, @RequestParam String query, Model model){
        model.addAttribute("page",newService.liseSerchNew("%"+query+"%",pageable));
        model.addAttribute("query",query);
        return "search";
    }

    @RequestMapping("/news/{id}")
    public String news(@PathVariable Long id,Model model){
        List<String> imagse = imagesService.findNewImags(id);
        News news = newService.getNew(id);
        NewsDTO userDTO = new NewsDTO(news,imagse);
        model.addAttribute("news",newService.getNew(id));
        return "new";
    }

    @RequestMapping("/about")
    public String about(){

        return "about";
    }

    @RequestMapping("/archives")
    public String archives(){

        return "archives";
    }


}
