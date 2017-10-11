package com.sam.webappad.model;

import java.util.List;

import com.sam.webappad.entity.ModelosEntity;

public interface ModelosModelInterface {
	
	public void save(ModelosEntity modelos_entity);
	public List<ModelosEntity> findAll();
	public ModelosEntity findById(int id);

}
