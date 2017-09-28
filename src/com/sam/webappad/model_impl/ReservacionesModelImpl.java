package com.sam.webappad.model_impl;

import com.sam.webappad.entity.ReservacionesEntity;
import com.sam.webappad.entity.ReservacionesEntity_;
import com.sam.webappad.model.ReservacionesModelInterface;
import java.sql.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/** @author sam **/

@Transactional
@Repository
public class ReservacionesModelImpl implements ReservacionesModelInterface {
    
    @Autowired
    private SessionFactory session_factory;
        
    private Session getSession() {
        return session_factory.getCurrentSession();
    }

    @Override
    public void save(ReservacionesEntity reservaciones_entity) {
    	getSession().save(reservaciones_entity);
    }
    
    @Override
	public void saveOrUpd(ReservacionesEntity reservaciones_entity) {
    	getSession().saveOrUpdate(reservaciones_entity);		
	}

    @Override
    public ReservacionesEntity findReservacionById(int id) {
        CriteriaBuilder criteria_builder = getSession().getCriteriaBuilder();
        CriteriaQuery<ReservacionesEntity> criteria_query = criteria_builder.createQuery(ReservacionesEntity.class);
        Root<ReservacionesEntity> root = criteria_query.from(ReservacionesEntity.class);
        criteria_query.select(root).where(criteria_builder.equal(root.get(ReservacionesEntity_.id), id));
        ReservacionesEntity reservaciones_entity = getSession().createQuery(criteria_query).uniqueResult();        
        return reservaciones_entity;
    }

	@Override
	public List<ReservacionesEntity> findReservacionByFecha(Date fecha, int id_reservacion) {
		CriteriaBuilder criteria_builder = getSession().getCriteriaBuilder();
		CriteriaQuery<ReservacionesEntity> criteria_query = criteria_builder.createQuery(ReservacionesEntity.class);
		Root<ReservacionesEntity> root = criteria_query.from(ReservacionesEntity.class);
		criteria_query.select(root).where(
				criteria_builder.equal(root.get(ReservacionesEntity_.fecha), fecha),
				criteria_builder.and(criteria_builder.notEqual(root.get(ReservacionesEntity_.id), id_reservacion)),
				criteria_builder.and(criteria_builder.equal(root.get(ReservacionesEntity_.tipo), 1)),
				criteria_builder.and(criteria_builder.equal(root.get(ReservacionesEntity_.cancelada), 0)));
		criteria_query.orderBy(criteria_builder.asc(root.get(ReservacionesEntity_.horas_entity_id_horaini)));
		List<ReservacionesEntity> lst_reservaciones = getSession().createQuery(criteria_query).getResultList();		
		return lst_reservaciones;
	}

	@Override
	public List<ReservacionesEntity> findReservacionByFecha(Date fecha) {
		CriteriaBuilder criteria_builder = getSession().getCriteriaBuilder();
        CriteriaQuery<ReservacionesEntity> criteria_query = criteria_builder.createQuery(ReservacionesEntity.class);
        Root<ReservacionesEntity> root = criteria_query.from(ReservacionesEntity.class);
        criteria_query.select(root).where(
        		criteria_builder.equal(root.get(ReservacionesEntity_.fecha), fecha),
        		criteria_builder.and(criteria_builder.equal(root.get(ReservacionesEntity_.tipo), 1)),
        		criteria_builder.and(criteria_builder.equal(root.get(ReservacionesEntity_.cancelada), 0)));
        criteria_query.orderBy(criteria_builder.asc(root.get(ReservacionesEntity_.horas_entity_id_horaini)));
        List<ReservacionesEntity> lst_reservaciones_entity = getSession().createQuery(criteria_query).getResultList();        
        return lst_reservaciones_entity;
	}

}

/*** SOLUCIONAR EL ERROR PROXY NO SESSION ****/
/*for(ReservacionesEntity re : lst_reservaciones) {
	Hibernate.initialize(re.getHoras_entity_id_horafin());
}*/