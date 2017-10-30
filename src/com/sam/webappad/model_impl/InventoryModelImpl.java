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
		getSession().saveOrUpdate(inventario_entity);
	}

	@Override
	public List<InventarioEntity> findAll() {
		CriteriaBuilder criteria_builder = getSession().getCriteriaBuilder();
		CriteriaQuery<InventarioEntity> criteria_query = criteria_builder.createQuery(InventarioEntity.class);
		Root<InventarioEntity> root = criteria_query.from(InventarioEntity.class);
		criteria_query.select(root).where(criteria_builder.equal(root.get(InventarioEntity_.status), 1));
		return getSession().createQuery(criteria_query).getResultList();
	}

	@Override
	public InventarioEntity findBySerie(String serie) {
		CriteriaBuilder criteria_builder = getSession().getCriteriaBuilder();
		CriteriaQuery<InventarioEntity> criteria_query = criteria_builder.createQuery(InventarioEntity.class);
		Root<InventarioEntity> root = criteria_query.from(InventarioEntity.class);
		criteria_query.select(root).where(criteria_builder.equal(root.get(InventarioEntity_.serie), serie));
		return getSession().createQuery(criteria_query).getSingleResult();
	}

	@Override
	public List<InventarioEntity> findByIdArticulo(int id_articulo) {
		CriteriaBuilder criteria_builder = getSession().getCriteriaBuilder();
		CriteriaQuery<InventarioEntity> criteria_query = criteria_builder.createQuery(InventarioEntity.class);
		Root<InventarioEntity> root = criteria_query.from(InventarioEntity.class);
		criteria_query.select(root).where(criteria_builder.equal(root.get(InventarioEntity_.articulos_entity), id_articulo),
				criteria_builder.and(criteria_builder.notEqual(root.get(InventarioEntity_.status), 0)));
		criteria_query.orderBy(criteria_builder.asc(root.get(InventarioEntity_.recursos_entity_inventario)));
		return getSession().createQuery(criteria_query).getResultList();
	}
	
	@Override
	public InventarioEntity findById(int id) {
		CriteriaBuilder criteria_builder = getSession().getCriteriaBuilder();
		CriteriaQuery<InventarioEntity> criteria_query = criteria_builder.createQuery(InventarioEntity.class);
		Root<InventarioEntity> root = criteria_query.from(InventarioEntity.class);
		criteria_query.select(root).where(criteria_builder.equal(root.get(InventarioEntity_.id), id));
		return getSession().createQuery(criteria_query).getSingleResult();
	}

	@Override
	public List<InventarioEntity> findByUbicacion(int id_ubicacion) {
		CriteriaBuilder criteria_builder = getSession().getCriteriaBuilder();
		CriteriaQuery<InventarioEntity> criteria_query = criteria_builder.createQuery(InventarioEntity.class);
		Root<InventarioEntity> root = criteria_query.from(InventarioEntity.class);
		criteria_query.select(root).where(criteria_builder.equal(root.get(InventarioEntity_.recursos_entity_inventario), id_ubicacion));
		return getSession().createQuery(criteria_query).getResultList();
	}

	@Override
	public List<InventarioEntity> findArticulosBaja() {
		CriteriaBuilder criteria_builder = getSession().getCriteriaBuilder();
		CriteriaQuery<InventarioEntity> criteria_query = criteria_builder.createQuery(InventarioEntity.class);
		Root<InventarioEntity> root = criteria_query.from(InventarioEntity.class);
		criteria_query.select(root).where(criteria_builder.equal(root.get(InventarioEntity_.status), 0));
		return getSession().createQuery(criteria_query).getResultList();
	}	

}
