package com.sam.webappad.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sam.webappad.entity.InventarioEntity;
import com.sam.webappad.model.InventoryModelInterface;

@Service
public class InventoryService {
	
	@Autowired
	private InventoryModelInterface inventory_model_interface;
	
	public void save(InventarioEntity inventario_entity) {
		inventory_model_interface.save(inventario_entity);
	}
	
	public List<InventarioEntity> showAll() {
		return inventory_model_interface.findAll();
	}

}
