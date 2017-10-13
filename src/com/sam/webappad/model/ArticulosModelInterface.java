package com.sam.webappad.model;

import java.util.List;

import com.sam.webappad.entity.ArticulosEntity;

public interface ArticulosModelInterface {
	
	public void save(ArticulosEntity articulos_entity);
	public List<ArticulosEntity> findAll();
	public ArticulosEntity findById(int id);

}
