package com.zr0817.news0817.controller.admin;

import com.zr0817.news0817.bean.Type;
import com.zr0817.news0817.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.data.domain.Pageable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class TYpeController {
    @Autowired
    private TypeService typeService;

    @RequestMapping("/types")
    public String type(@PageableDefault(size = 5,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable, Model model){
        model.addAttribute("page",typeService.listType(pageable));
        return "admin/types";
    }

    @RequestMapping("/types/input")
    public String toinput(Model model){
        model.addAttribute("type",new Type());
        return "admin/types-input";
    }

    @RequestMapping("/types/add")
    public String add(@Valid Type type, BindingResult result, RedirectAttributes attributes){
        Type type1=typeService.getTypeByName(type.getName());
        if (type1!=null){
            result.rejectValue("name","nameError","不能添加重复的分类");//错误位，错误地址，错误信息
        }
        if (result.hasErrors()){
            return "admin/types-input";
        }
        Type type2=typeService.saveType(type);
        if (type2==null){
            attributes.addFlashAttribute("messages","新增失败");
        }else {
            attributes.addFlashAttribute("messages","新增成功");
        }
        return "redirect:/admin/types";
    }

    @RequestMapping("/types/{id}/toUpdate")
    public String toUpdate(@PathVariable Long id,Model model){
        model.addAttribute("type",typeService.getType(id));
        return  "admin/types-input";
    }

    @RequestMapping("/types/update/{id}")
    public String update(@Valid Type type,BindingResult result,@PathVariable Long id,RedirectAttributes attributes){
        Type type1=typeService.getTypeByName(type.getName());
        if (type1!=null){
            result.rejectValue("name","nameError","该类别已存在");
        }
        if (result.hasErrors()){
            return "admin/types-input";
        }
        Type type2=typeService.updateType(id,type);
        if (type2!=null){
            attributes.addFlashAttribute("message","success");
        }else{
            attributes.addFlashAttribute("message","false");
        }
return "redirect:/admin/types";
    }

    @RequestMapping("/types/{id}/delete")
    public  String delete(@PathVariable Long id){
        typeService.deleteById(id);
        return "redirect:/admin/types";
    }
}
