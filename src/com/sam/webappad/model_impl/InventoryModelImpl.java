package com.sam.webappad.model_impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sam.webappad.entity.InventarioEntity;
import com.sam.webappad.model.InventoryModelInterface;

@Transactional
@Repository
public class InventoryModelImpl implements InventoryModelInterface {
	
	@Autowired
    private SessionFactory session_factory;
        
    private Session getSession() {
        return session_factory.getCurrentSession();
    }
	
	@Override
	public void save(InventarioEntity inventario_entity) {
		getSession().save(inventario_entity);
	}

	@Override
	public List<InventarioEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}	

}
