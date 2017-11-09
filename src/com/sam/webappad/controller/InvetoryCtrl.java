package com.sam.webappad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sam.webappad.entity.InventarioEntity;
import com.sam.webappad.service.ArticulosService;
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
	
	@Autowired
	private ArticulosService articulos_service;
	
	@RequestMapping("/inventario")
	public String invetory(Model model) {
		/*model.addAttribute("inventario", new InventarioEntity());/*SI NO SE MANDA UNA INSTACIA DE LA CLASE
    	MARCA EL ERROR Neither BindingResult nor plain target object*/
		model.addAttribute("lst_marcas", marcas_service.findAll());
		model.addAttribute("lst_modelos", modelos_service.findAll());
		model.addAttribute("lst_recursos", recursos_service.findAll());
		model.addAttribute("lst_articulos", articulos_service.findAll());
		return "inventory";
	}
	
	@RequestMapping(value = "/inventario/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("id_inventario") int id_inventario, @ModelAttribute("articulo") int id_articulo, @ModelAttribute("marca") int id_marca, @ModelAttribute("modelo") int id_modelo,
					   @ModelAttribute("serie") String serie, @ModelAttribute("recurso") int id_recurso, @ModelAttribute("horas") int horas,
					   @ModelAttribute("condiciones") String condiciones, @ModelAttribute("comentarios") String comentarios,
					   @ModelAttribute("status") int status) {
		
		InventarioEntity inventario_entity = new InventarioEntity(serie, horas, condiciones, comentarios, status);
		System.out.println(inventario_entity);
		//System.out.println("id inventario: " + id_inventario);
		if(id_inventario != 0) {
			inventario_entity.setId(id_inventario);
		}
		inventario_entity.setArticulos_entity(articulos_service.findById(id_articulo));
		inventario_entity.setMarcas_entity(marcas_service.findById(id_marca));
		inventario_entity.setModelos_entity(modelos_service.findById(id_modelo));
		inventario_entity.setRecursos_entity_inventario(recursos_service.findById(id_recurso));		
		
		System.out.println(inventario_entity);
		inventory_service.save(inventario_entity);
		return "";
	}
	
	@RequestMapping(value = "/inventario/articulos", method = RequestMethod.POST)
	public String articulos(Model model, @ModelAttribute("serie") String serie, @ModelAttribute("id_articulo") int id_articulo, 
			@ModelAttribute("id_ubicacion") int id_ubicacion, @ModelAttribute("flag") int flag) {
		if(!serie.equals("")) {
			System.out.println("BUSCAR POR SERIE");
			model.addAttribute("flag", 1);
			try {
				model.addAttribute("articulo", inventory_service.findBySerie(serie));
			} catch(Exception ex) {
				model.addAttribute("articulo", null);
			}
		} else if(id_articulo != 0) {
			System.out.println("BUSCAR POR articulo");
			model.addAttribute("flag", 0);
			model.addAttribute("lst_resultado", inventory_service.findByIdArticulo(id_articulo));
		} else if(id_ubicacion != 0) {
			System.out.println("BUSCAR POR ubicacion");
			model.addAttribute("flag", 0);
			model.addAttribute("lst_resultado", inventory_service.findByUbicacion(id_ubicacion));
		} else if(flag == 1){
			System.out.println("BUSCAR LOS ACTIVOS");
			model.addAttribute("flag", 0);
			model.addAttribute("lst_resultado", inventory_service.findAll());
		} else if(flag == 0){
			System.out.println("BUSCAR LAS BAJAS");
			model.addAttribute("flag", 0);
			model.addAttribute("lst_resultado", inventory_service.findArticulosBaja());
		}		
		return "articulos";
	}
	
	@RequestMapping(value = "/inventario/modificar", method = RequestMethod.POST)
	public String modificar(Model model, @ModelAttribute("id") int id) {
		System.out.println("METODO: modificar()\n ID: " + id);
		try {			
			model.addAttribute("inventario_articulo", inventory_service.findById(id));
		} catch(Exception ex) {
			System.out.println("ERROR: " + ex);
		}
		model.addAttribute("lst_marcas", marcas_service.findAll());
		model.addAttribute("lst_modelos", modelos_service.findAll());
		model.addAttribute("lst_recursos", recursos_service.findAll());
		return "modificar_articulo";
	}

}
