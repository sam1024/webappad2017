package com.sam.webappad.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sam.webappad.entity.RecursosEntity;
import com.sam.webappad.model.RecursosModelInterface;

/** @author sam **/

@Service
public class RecursosService {
    
    @Autowired
    private RecursosModelInterface recursos_model_interface;
    
    public List<RecursosEntity> findAll() {
        return recursos_model_interface.findAll();
    }
    
    public RecursosEntity findById(int id) {
    	return recursos_model_interface.findById(id);
    }
    
}
