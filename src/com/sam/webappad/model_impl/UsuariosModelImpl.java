package com.sam.webappad.model_impl;

import com.sam.webappad.entity.UsuariosEntity;
import com.sam.webappad.model.UsuariosModel;
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
public class UsuariosModelImpl implements UsuariosModel {
    
    @Autowired
    private SessionFactory session_factory;
        
    private Session getSession() {
        return session_factory.getCurrentSession();
    }

    @Override
    public void save(UsuariosEntity usuarios_entity) {
        getSession().save(usuarios_entity);
        //GetSession().getSession().save(usuarios_entity);
    }

    @Override
    public List<UsuariosEntity> findAll() {
        return getSession().createQuery("FROM UsuariosEntity").list();
        //return GetSession.getSession().createQuery("FROM UsuariosEntity").list();
    }

    @Override
    public UsuariosEntity findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UsuariosEntity> findByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(UsuariosEntity usuarios_entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }        

    @Override
    public UsuariosEntity findByUsuario(String user_name) {
        CriteriaBuilder criteria_builder = getSession().getCriteriaBuilder();
        CriteriaQuery<UsuariosEntity> criteria_query = criteria_builder.createQuery(UsuariosEntity.class);
        Root<UsuariosEntity> root = criteria_query.from(UsuariosEntity.class);
        /*criteria_query.select(root);
        criteria_query.where(criteria_builder.equal(root.get("usuario"), user_name));*/
        criteria_query.select(root).where(criteria_builder.equal(root.get("usuario"), user_name));
        UsuariosEntity user_entity = getSession().createQuery(criteria_query).uniqueResult();
        return user_entity;
    }
    
}