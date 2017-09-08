package com.sam.webappad.controller;

import com.sam.webappad.entity.UsuariosEntity;
import com.sam.webappad.service.RolesService;
import com.sam.webappad.service.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/** @author sam **/

@Controller
public class UsuariosCtrl {
    
    @Autowired
    private UsuariosService usuarios_service;
    
    @Autowired
    private RolesService roles_service;
    
    @RequestMapping("/usuarios")
    public String usuarios(Model model, @ModelAttribute("resultado") String resultado) {
        model.addAttribute("usuario", new UsuariosEntity());/*SI NO SE MANDA UNA INSTACIA DE LA CLASE
    	MARCA EL ERROR Neither BindingResult nor plain target object*/
        model.addAttribute("resultado", resultado);
        model.addAttribute("lst_usuarios", usuarios_service.findAll());
        model.addAttribute("lst_roles", roles_service.findAll());
        return "usuarios";
    }
    
    @RequestMapping(value = "/usuarios/save", method = RequestMethod.POST)
    public String HandleUsuarios(@ModelAttribute("usuario") UsuariosEntity usuarios_entity, Model model, 
                                 RedirectAttributes redirect_attributes) { //, @RequestParam("rol") int id_role
        usuarios_service.save(usuarios_entity);
        redirect_attributes.addFlashAttribute("resultado", "Registro guardado con exitooo");
        System.out.println(usuarios_entity);
        return "redirect:/usuarios";
    }
    
}
