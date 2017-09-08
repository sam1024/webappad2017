package com.sam.webappad.model;

import com.sam.webappad.entity.RolesEntity;
import java.util.List;

/** @author sam **/

public interface RolesModel {
    
    public boolean save(RolesEntity roles_entity);
    public List<RolesEntity> findAll();
    public boolean update(RolesEntity roles_entity);
    public boolean delete(int id);
    
}
