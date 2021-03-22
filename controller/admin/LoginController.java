package com.zr0817.news0817.controller.admin;

import com.zr0817.news0817.bean.User;
import com.zr0817.news0817.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String loginPath(){return "admin/login";}


    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session, RedirectAttributes attributes){
        User user=userService.checkUsers(username,password);
        if(user!=null){
            user.setPassword(null);
            session.setAttribute("user",user);
            return "admin/index";
        }else {
            attributes.addFlashAttribute("message","用户密码错误");
            return "redirect:/admin";
        }
    }
}
