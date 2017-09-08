package com.sam.webappad.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/** @author sam **/

@Controller
public class IndexCtrl {
    
    @RequestMapping("/")
    public String showIndex() {
        return "index";
    }
    
}
