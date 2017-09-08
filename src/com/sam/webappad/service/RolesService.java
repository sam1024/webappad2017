package com.sam.webappad.service;

import com.sam.webappad.entity.RolesEntity;
import com.sam.webappad.model.RolesModel;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** @author sam **/

@Service
public class RolesService {
    
    @Autowired
    private RolesModel roles_model;
    
    public boolean save(RolesEntity roles_entity) {
        return roles_model.save(roles_entity);
    }
    
    public List<RolesEntity> findAll() {
        return roles_model.findAll();
    }
    
}
