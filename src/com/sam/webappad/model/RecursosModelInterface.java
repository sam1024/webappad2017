package com.sam.webappad.model;

import com.sam.webappad.entity.RecursosEntity;
import java.util.List;

/** @author sam **/

public interface RecursosModelInterface {
    
    public List<RecursosEntity> findAll();
    public RecursosEntity findById(int id);
    
}
