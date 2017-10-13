package com.sam.webappad.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sam.webappad.entity.ArticulosEntity;
import com.sam.webappad.model.ArticulosModelInterface;

@Service
public class ArticulosService {
	
	@Autowired
	private ArticulosModelInterface articulos_model_interface;
	
	public void save(ArticulosEntity articulos_entity) {
		articulos_model_interface.save(articulos_entity);
	}
	
	public List<ArticulosEntity> findAll() {
		return articulos_model_interface.findAll();
	}

}
