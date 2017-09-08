package com.sam.webappad.model;

import com.sam.webappad.entity.HorasEntity;
import java.util.List;

/** @author sam **/

public interface HorasModelInterface {
    
    public List<HorasEntity> findAll();
    public HorasEntity findById(int id);
    
}
