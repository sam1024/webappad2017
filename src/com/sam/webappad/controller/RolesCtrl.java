package com.sam.webappad.controller;

import com.sam.webappad.entity.RolesEntity;
import com.sam.webappad.service.RolesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/** @author sam **/

@Controller
public class RolesCtrl {
    
    @Autowired
    private RolesService roles_service;
    
    @RequestMapping("/roles")
    public String roles(Model model) {
        List<RolesEntity> lst_roles = roles_service.findAll();
        model.addAttribute("lst_roles", lst_roles);
        return "roles";
    }
    
}
