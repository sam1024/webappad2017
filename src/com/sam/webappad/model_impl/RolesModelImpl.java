package com.sam.webappad.model_impl;

import com.sam.webappad.entity.RolesEntity;
import com.sam.webappad.model.RolesModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/** @author sam **/

@Transactional
@Repository
public class RolesModelImpl implements RolesModel {
    
    @Autowired
    private SessionFactory session_factory;
    
    private Session getSession() {
        return session_factory.getCurrentSession();
    }

    @Override
    public boolean save(RolesEntity roles_entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RolesEntity> findAll() {
        //Query query = getSession().createQuery("FROM roles");
        return getSession().createQuery("FROM RolesEntity").list();
        /*** SE PONE RolesEntity(NOMBRE DE LA CLASE) Y NO ROLES(NOMBRE DE LA TABLA POR QUE HIBERNATE USA OBJETOS Y NO LAS
         TABLAS ***/
    }

    @Override
    public boolean update(RolesEntity roles_entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
