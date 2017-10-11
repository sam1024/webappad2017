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

import com.sam.webappad.entity.MarcasEntity;
import com.sam.webappad.entity.MarcasEntity_;
import com.sam.webappad.model.MarcasModelInterface;

@Transactional
@Repository
public class MarcasModelImpl implements MarcasModelInterface {
	
	@Autowired
    private SessionFactory session_factory;
        
    private Session getSession() {
        return session_factory.getCurrentSession();
    }

	@Override
	public void save(MarcasEntity marcasEntity) {
				
	}

	@Override
	public List<MarcasEntity> findAll() {
		CriteriaBuilder criteria_builder = getSession().getCriteriaBuilder();
		CriteriaQuery<MarcasEntity> criteria_query = criteria_builder.createQuery(MarcasEntity.class);
		Root<MarcasEntity> root = criteria_query.from(MarcasEntity.class);
		criteria_query.select(root);
		criteria_query.orderBy(criteria_builder.asc(root.get(MarcasEntity_.marca)));
		return getSession().createQuery(criteria_query).list();
	}

	@Override
	public MarcasEntity findById(int id) {
		CriteriaBuilder criteria_builder = getSession().getCriteriaBuilder();
		CriteriaQuery<MarcasEntity> criteria_query = criteria_builder.createQuery(MarcasEntity.class);
		Root<MarcasEntity> root = criteria_query.from(MarcasEntity.class);
		criteria_query.select(root).where(criteria_builder.equal(root.get(MarcasEntity_.id), id));
		return getSession().createQuery(criteria_query).uniqueResult();
	}

}
