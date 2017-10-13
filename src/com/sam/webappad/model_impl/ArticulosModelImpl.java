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

import com.sam.webappad.entity.ArticulosEntity;
import com.sam.webappad.model.ArticulosModelInterface;

@Transactional
@Repository
public class ArticulosModelImpl implements ArticulosModelInterface {
	
	@Autowired
    private SessionFactory session_factory;
        
    private Session getSession() {
        return session_factory.getCurrentSession();
    }

	@Override
	public void save(ArticulosEntity articulos_entity) {
		getSession().save(articulos_entity);
	}

	@Override
	public List<ArticulosEntity> findAll() {
		CriteriaBuilder criteria_builder = getSession().getCriteriaBuilder();
		CriteriaQuery<ArticulosEntity> criteria_query = criteria_builder.createQuery(ArticulosEntity.class);
		Root<ArticulosEntity> root = criteria_query.from(ArticulosEntity.class);
		criteria_query.select(root);
		return getSession().createQuery(criteria_query).getResultList();
	}

	@Override
	public ArticulosEntity findById(int id) {
		
		return null;
	}

}
