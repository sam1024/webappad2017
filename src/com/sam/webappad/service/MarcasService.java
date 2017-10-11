package com.sam.webappad.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sam.webappad.entity.MarcasEntity;
import com.sam.webappad.model.MarcasModelInterface;

@Service
public class MarcasService {
	
	@Autowired
	private MarcasModelInterface marcas_model_interface;
	
	public void save(MarcasEntity marcas_entity) {
		marcas_model_interface.save(marcas_entity);
	}
	
	public List<MarcasEntity> findAll() {
		return marcas_model_interface.findAll();
	}
	
	public MarcasEntity findById(int id) {
		return marcas_model_interface.findById(id);
	}

}
