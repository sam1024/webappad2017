package com.sam.webappad.model_impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sam.webappad.entity.InventarioEntity;
import com.sam.webappad.entity.InventarioEntity_;
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
		CriteriaBuilder criteria_builder = getSession().getCriteriaBuilder();
		CriteriaQuery<InventarioEntity> criteria_query = criteria_builder.createQuery(InventarioEntity.class);
		Root<InventarioEntity> root = criteria_query.from(InventarioEntity.class);
		criteria_query.select(root).where(criteria_builder.equal(root.get(InventarioEntity_.status), 1));
		return getSession().createQuery(criteria_query).getResultList();
	}	

}
