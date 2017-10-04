package com.sam.webappad.model_impl;

import com.sam.webappad.entity.RecursosEntity;
import com.sam.webappad.entity.RecursosEntity_;
import com.sam.webappad.model.RecursosModelInterface;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/** @author sam **/

@Transactional
@Repository
public class RecursosModelImpl implements RecursosModelInterface {

    @Autowired
    private SessionFactory session_factory;
    
    private Session getSession() {
        return session_factory.getCurrentSession();
    }
    
    @Override
    public List<RecursosEntity> findAll() {
    	CriteriaBuilder criteria_builder = getSession().getCriteriaBuilder();
    	CriteriaQuery<RecursosEntity> criteria_query = criteria_builder.createQuery(RecursosEntity.class);
    	Root<RecursosEntity> root = criteria_query.from(RecursosEntity.class);
    	criteria_query.select(root);
    	criteria_query.orderBy(criteria_builder.asc(root.get(RecursosEntity_.id)));
    	return getSession().createQuery(criteria_query).list();
        //return getSession().createQuery("FROM RecursosEntity").list();
    }

	@Override
	public RecursosEntity findById(int id) {
		CriteriaBuilder criteria_builder = getSession().getCriteriaBuilder();
		CriteriaQuery<RecursosEntity> criteria_query = criteria_builder.createQuery(RecursosEntity.class);
		Root<RecursosEntity> root = criteria_query.from(RecursosEntity.class);
		criteria_query.select(root).where(criteria_builder.equal(root.get(RecursosEntity_.id), id));
		RecursosEntity recursos_entity = getSession().createQuery(criteria_query).uniqueResult();
		return recursos_entity;
	}
    
}
