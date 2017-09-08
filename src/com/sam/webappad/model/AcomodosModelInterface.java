package com.sam.webappad.model;

import java.util.List;

import com.sam.webappad.entity.AcomodosEntity;

public interface AcomodosModelInterface {
	
	public List<AcomodosEntity> findAll();
	public AcomodosEntity findById(int id);

}
