package com.zr0817.news0817.controller.admin;

import com.zr0817.news0817.bean.Tag;
import com.zr0817.news0817.bean.Type;
import com.zr0817.news0817.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sun.security.timestamp.TSRequest;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class TagControllere {

    @Autowired
    private TagService tagService;

    @RequestMapping("/tags")
    public String tags(@PageableDefault(size = 5,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable, Model model){
        model.addAttribute("page",tagService.listTag(pageable));
        return "admin/tags";
    }

    @RequestMapping("/tags/input")
    public String iuput(Model model){
        model.addAttribute("tag",new Tag());
        return "admin/tags-input";
    }

    @RequestMapping("/tags/add")
    public String add(@Valid Tag tag, BindingResult result, RedirectAttributes attributes ){
        Tag tag1=tagService.getTagByName(tag.getName());
        if (tag1!=null){
            result.rejectValue("name","nameError","不能添加重复的分类");//错误位，错误地址，错误信息
        }
        if (result.hasErrors()){
            return "admin/tags-input";
        }
        Tag tag2=tagService.saveTag(tag);
        if (tag2==null){
            attributes.addFlashAttribute("messages","新增失败");
        }else {
            attributes.addFlashAttribute("messages","新增成功");
        }
        return "redirect:/admin/tags";

    }

    @RequestMapping("/tags/{id}/toUpdate")
    public String toUpdate(@PathVariable Long id, Model model){
        model.addAttribute("tag",tagService.getTag(id));
        return  "admin/tags-input";
    }
    @RequestMapping("/tags/update/{id}")
    public String update(@Valid Tag tag,BindingResult result,@PathVariable Long id,RedirectAttributes attributes){
        Tag tag1=tagService.getTagByName(tag.getName());
        if (tag1!=null){
            result.rejectValue("name","nameError","不能添加重复类");
        }
        if (result.hasErrors()){
            return "admin/tags-input";
        }
        Tag tag2=tagService.updateTag(id,tag);
        if (tag2!=null){
            attributes.addFlashAttribute("message","success");
        }else{
            attributes.addFlashAttribute("message","false");
        }
        return "redirect:/admin/tags";
    }

    @RequestMapping("/tags/{id}/delete")
    public  String delete(@PathVariable Long id){
        tagService.deleteById(id);
        return "redirect:/admin/tags";
    }
}



