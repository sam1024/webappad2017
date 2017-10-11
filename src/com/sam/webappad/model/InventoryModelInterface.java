package com.sam.webappad.model;

import java.util.List;

import com.sam.webappad.entity.InventarioEntity;

public interface InventoryModelInterface {
	
	public void save(InventarioEntity inventario_entity);
	public List<InventarioEntity> findAll();

}
