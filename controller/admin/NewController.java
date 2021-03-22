package com.zr0817.news0817.controller.admin;

import com.zr0817.news0817.bean.NewQuery;
import com.zr0817.news0817.bean.News;
import com.zr0817.news0817.bean.User;
import com.zr0817.news0817.service.NewService;
import com.zr0817.news0817.service.TagService;
import com.zr0817.news0817.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/admin")
public class NewController {

    private static final String INPUT="admin/news-input";
    private static final String LIST="admin/news";
    private static final String REDIRECT_LIST="redirect:/admin/news";

    @Autowired
    private NewService newService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;



    @RequestMapping("/news")
    public String news(@PageableDefault(size = 3,sort = {"updateTime"},direction = Sort.Direction.DESC)Pageable pageable, Model model){
       model.addAttribute("types",typeService.listType());
        model.addAttribute("page",newService.listNew(pageable));
        return LIST;
    }

    @RequestMapping("/news/search")
    public String search(@PageableDefault(size = 3,sort = {"updateTime"},direction = Sort.Direction.DESC)Pageable pageable,
                         NewQuery newQuery,Model model){
        model.addAttribute("page",newService.listNew(pageable,newQuery));
        return "admin/news::newsList";

    }

    @RequestMapping("/news/input")
    public String iuput(Model model){
        model.addAttribute("types",typeService.listType());
        model.addAttribute("tags",tagService.listTag());
        model.addAttribute("news",new News());
        return INPUT;
    }
    @RequestMapping("/news/add")
    public String post(News news, RedirectAttributes attributes, HttpSession session){
        news.setUser((User) session.getAttribute("user"));
        news.setType(typeService.getType(news.getType().getId()));
        news.setTags(tagService.listTag(news.getTagIds()));
        News news1;
        if (news.getId()==null){
            news1=newService.saveNew(news);
        }else {
            news1=newService.updateNew(news,news.getId());
        }
//        news1=newService.saveNew(news);
        if (news1==null){
            attributes.addFlashAttribute("message","false");
        }else {
            attributes.addFlashAttribute("message","success");
        }
//        return REDIRECT_LIST;
        return REDIRECT_LIST;
    }

    @RequestMapping("/news/{id}/toUpdate")
    public String editInput(@PathVariable Long id,Model model){
        model.addAttribute("types",typeService.listType());
        model.addAttribute("tags",tagService.listTag());
        News news=newService.getNew(id);
        news.init();
        model.addAttribute("news",news);
        return INPUT;
//        return REDIRECT_LIST;
    }

    @RequestMapping("/news/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        newService.deleteNew(id);
        attributes.addFlashAttribute("message","delete-success");
        return REDIRECT_LIST;

    }



}
