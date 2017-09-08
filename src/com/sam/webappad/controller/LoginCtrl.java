package com.sam.webappad.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/** @author sam **/

@Controller
public class LoginCtrl {
    
    @RequestMapping("/login")
    public String login() {
        return "login";
    }
    
    @RequestMapping("/logout")
    public String logout() {
        //return "/";
        return "redirect:/";
    }
    
}
