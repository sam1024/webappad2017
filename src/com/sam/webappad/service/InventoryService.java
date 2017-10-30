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
	
	public List<InventarioEntity> findAll() {
		return inventory_model_interface.findAll();
	}
	
	public InventarioEntity findBySerie(String serie) {
		return inventory_model_interface.findBySerie(serie);
	}
	
	public List<InventarioEntity> findByIdArticulo(int id_articulo) {
		return inventory_model_interface.findByIdArticulo(id_articulo);
	}
	
	public InventarioEntity findById(int id) {
		return inventory_model_interface.findById(id);
	}
	
	public List<InventarioEntity> findByUbicacion(int id_ubicacion) {
		return inventory_model_interface.findByUbicacion(id_ubicacion);
	}
	
	public List<InventarioEntity> findArticulosBaja() {
		return inventory_model_interface.findArticulosBaja();
	}

}
