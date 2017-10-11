package com.sam.webappad.model;

import java.util.List;

import com.sam.webappad.entity.MarcasEntity;

public interface MarcasModelInterface {
	
	public void save(MarcasEntity marcasEntity);
	public List<MarcasEntity> findAll();
	public MarcasEntity findById(int id);

}
