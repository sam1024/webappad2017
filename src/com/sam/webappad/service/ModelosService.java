package com.sam.webappad.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sam.webappad.entity.ModelosEntity;
import com.sam.webappad.model.ModelosModelInterface;

@Service
public class ModelosService {
	
	@Autowired
	private ModelosModelInterface modelos_model_interface;
	
	public List<ModelosEntity> findAll() {
		return modelos_model_interface.findAll();
	}
	
	public ModelosEntity findById(int id) {
		return modelos_model_interface.findById(id);
	}

}
