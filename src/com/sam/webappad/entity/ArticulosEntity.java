package com.sam.webappad.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "articulos")
public class ArticulosEntity {
	
	@Id
	private int id;
	private String nombre;
	
	@OneToMany(mappedBy = "articulos_entity")
	private Set<InventarioEntity> articulos_entity;

	public ArticulosEntity() {}

	public ArticulosEntity(String nombre) {
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<InventarioEntity> getArticulos_entity() {
		return articulos_entity;
	}

	public void setArticulos_entity(Set<InventarioEntity> articulos_entity) {
		this.articulos_entity = articulos_entity;
	}	

}
