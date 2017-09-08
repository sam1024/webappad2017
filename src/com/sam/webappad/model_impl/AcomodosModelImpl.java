package com.sam.webappad.model_impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sam.webappad.entity.AcomodosEntity;
import com.sam.webappad.entity.AcomodosEntity_;
import com.sam.webappad.entity.RecursosEntity;
import com.sam.webappad.model.AcomodosModelInterface;

@Transactional
@Repository
public class AcomodosModelImpl implements AcomodosModelInterface {
	
	@Autowired
    private SessionFactory session_factory;
    
    private Session getSession() {
        return session_factory.getCurrentSession();
    }

	@Override
	public List<AcomodosEntity> findAll() {
		return getSession().createQuery("From AcomodosEntity").list();
	}

	@Override
	public AcomodosEntity findById(int id) {
		CriteriaBuilder criteria_builder = getSession().getCriteriaBuilder();
		CriteriaQuery<AcomodosEntity> criteria_query = criteria_builder.createQuery(AcomodosEntity.class);
		Root<AcomodosEntity> root = criteria_query.from(AcomodosEntity.class);
		criteria_query.select(root).where(criteria_builder.equal(root.get(AcomodosEntity_.id), id));
		AcomodosEntity acomodos_entity = getSession().createQuery(criteria_query).uniqueResult();
		return acomodos_entity;
	}

}
