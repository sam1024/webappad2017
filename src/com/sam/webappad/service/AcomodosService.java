package com.sam.webappad.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sam.webappad.entity.AcomodosEntity;
import com.sam.webappad.model.AcomodosModelInterface;

@Service
public class AcomodosService {
	
	@Autowired
	private AcomodosModelInterface acomodos_model_interface;
	
	public List<AcomodosEntity> findAll() {
		return acomodos_model_interface.findAll();
	}
	
	public AcomodosEntity findById(int id) {
		return acomodos_model_interface.findById(id);
	}

}
