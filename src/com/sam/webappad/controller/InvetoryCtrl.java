package com.sam.webappad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sam.webappad.entity.InventarioEntity;
import com.sam.webappad.service.InventoryService;
import com.sam.webappad.service.MarcasService;
import com.sam.webappad.service.ModelosService;
import com.sam.webappad.service.RecursosService;

@Controller
public class InvetoryCtrl {
	
	@Autowired
	private MarcasService marcas_service;
	
	@Autowired
	private ModelosService modelos_service;
	
	@Autowired
	private RecursosService recursos_service;
	
	@Autowired
	private InventoryService inventory_service;
	
	@RequestMapping("/inventario")
	public String invetory(Model model) {
		model.addAttribute("inventario", new InventarioEntity());/*SI NO SE MANDA UNA INSTACIA DE LA CLASE
    	MARCA EL ERROR Neither BindingResult nor plain target object*/
		model.addAttribute("lst_marcas", marcas_service.findAll());
		model.addAttribute("lst_modelos", modelos_service.findAll());
		model.addAttribute("lst_recursos", recursos_service.findAll());
		return "inventory";
	}
	
	@RequestMapping(value = "/inventario/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("articulo") String articulo, @ModelAttribute("marca") int id_marca, @ModelAttribute("modelo") int id_modelo,
					   @ModelAttribute("serie") String serie, @ModelAttribute("recurso") int id_recurso, @ModelAttribute("horas") int horas,
					   @ModelAttribute("condiciones") String condiciones, @ModelAttribute("comentarios") String comentarios,
					   @ModelAttribute("status") int status) {
		System.out.println("Articulo: " + articulo + "\nDI Marca: " + id_marca + "\nID Modelo: " + id_modelo + "\nSerie: " + serie +
				"\nID Recurso: " + id_recurso + "\nHoras: " + horas + "\nCondiciones: " + condiciones + "\nComentarios: " + comentarios + "\nStatus: " + status);
		InventarioEntity inventario_entity = new InventarioEntity(articulo, serie, horas, condiciones, comentarios, status);
		inventario_entity.setRecursos_entity_inventario(recursos_service.findById(id_recurso));
		inventario_entity.setMarcas_entity(marcas_service.findById(id_marca));
		inventario_entity.setModelos_entity(modelos_service.findById(id_modelo));
		System.out.println("INVENTARIO ENTITY: " + inventario_entity.toString());
		inventory_service.save(inventario_entity);
		return "";
	}

}
