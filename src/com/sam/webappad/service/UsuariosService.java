package com.sam.webappad.service;

import com.sam.webappad.entity.RolesEntity;
import com.sam.webappad.entity.UsuariosEntity;
import com.sam.webappad.model.UsuariosModel;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/** @author sam **/

@Service
public class UsuariosService {
    
    @Autowired
    private UsuariosModel usuarios_model;
    
    @Autowired
    private BCryptPasswordEncoder b_crypt_password_encoder;
    
    public void save(UsuariosEntity usuarios_entity) {
        String clave_usuario = usuarios_entity.getPass();
        usuarios_entity.setPass(b_crypt_password_encoder.encode(clave_usuario));        
        usuarios_model.save(usuarios_entity);
    }
    
    public List<UsuariosEntity> findAll() {
        return usuarios_model.findAll();
    }
    
    public UsuariosEntity findByUsuario(String user_name) {
    	return usuarios_model.findByUsuario(user_name);
    }
    
}
