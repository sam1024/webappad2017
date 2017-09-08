package com.sam.webappad.service;

import com.sam.webappad.entity.HorasEntity;
import com.sam.webappad.model.HorasModelInterface;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** @author sam **/

@Service
public class HorasService {
    
    @Autowired
    private HorasModelInterface horas_model_interface;
    
    public List<HorasEntity> findAll() {
        return horas_model_interface.findAll();
    }
    
    public HorasEntity findById(int id) {
    	return horas_model_interface.findById(id);
    }
    
}
