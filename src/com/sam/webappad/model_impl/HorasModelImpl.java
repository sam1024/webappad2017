package com.sam.webappad.model_impl;

import com.sam.webappad.entity.HorasEntity;
import com.sam.webappad.entity.HorasEntity_;
import com.sam.webappad.model.HorasModelInterface;
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
public class HorasModelImpl implements HorasModelInterface {

    @Autowired
    private SessionFactory session_factory;
    
    private Session getSession() {
        return session_factory.getCurrentSession();
    }
    
    @Override
    public List<HorasEntity> findAll() {
        return getSession().createQuery("From HorasEntity").list();
    }

	@Override
	public HorasEntity findById(int id) {
		CriteriaBuilder criteria_builder = getSession().getCriteriaBuilder();
		CriteriaQuery<HorasEntity> criteria_query = criteria_builder.createQuery(HorasEntity.class);
		Root<HorasEntity> root = criteria_query.from(HorasEntity.class);
		criteria_query.select(root).where(criteria_builder.equal(root.get(HorasEntity_.id), id));
		HorasEntity horas_entity = getSession().createQuery(criteria_query).uniqueResult();
		return horas_entity;
	}
}
