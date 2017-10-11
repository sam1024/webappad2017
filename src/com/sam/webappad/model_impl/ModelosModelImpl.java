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

import com.sam.webappad.entity.ModelosEntity;
import com.sam.webappad.entity.ModelosEntity_;
import com.sam.webappad.model.ModelosModelInterface;

@Transactional
@Repository
public class ModelosModelImpl implements ModelosModelInterface {
	
	@Autowired
    private SessionFactory session_factory;
        
    private Session getSession() {
        return session_factory.getCurrentSession();
    }

	@Override
	public void save(ModelosEntity modelos_entity) {
		
	}

	@Override
	public List<ModelosEntity> findAll() {
		CriteriaBuilder criteria_builder = getSession().getCriteriaBuilder();
		CriteriaQuery<ModelosEntity> criteria_query = criteria_builder.createQuery(ModelosEntity.class);
		Root<ModelosEntity> root = criteria_query.from(ModelosEntity.class);
		criteria_query.select(root);
		criteria_query.orderBy(criteria_builder.asc(root.get(ModelosEntity_.modelo)));
		return getSession().createQuery(criteria_query).list();
	}

	@Override
	public ModelosEntity findById(int id) {
		CriteriaBuilder criteria_builder = getSession().getCriteriaBuilder();
		CriteriaQuery<ModelosEntity> criteria_query = criteria_builder.createQuery(ModelosEntity.class);
		Root<ModelosEntity> root = criteria_query.from(ModelosEntity.class);
		criteria_query.select(root).where(criteria_builder.equal(root.get(ModelosEntity_.id), id));
		return getSession().createQuery(criteria_query).getSingleResult();
	}

}
