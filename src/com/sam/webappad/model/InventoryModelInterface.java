package com.sam.webappad.model;

import java.util.List;

import com.sam.webappad.entity.InventarioEntity;

public interface InventoryModelInterface {
	
	public void save(InventarioEntity inventario_entity);
	public List<InventarioEntity> findAll();
	public InventarioEntity findBySerie(String serie);
	public List<InventarioEntity> findByIdArticulo(int id_articulo);
	public InventarioEntity findById(int id);
	public List<InventarioEntity> findByUbicacion(int id_ubicacion);
	public List<InventarioEntity> findArticulosBaja();

}
