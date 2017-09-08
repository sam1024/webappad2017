package com.sam.webappad.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InvetoryCtrl {
	
	@RequestMapping("/inventario")
	public String invetory() {
		return "inventory";
	}

}
