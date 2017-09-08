package com.sam.webappad.configuration;

import com.sam.webappad.entity.UsuariosEntity;
import com.sam.webappad.model.UsuariosModel;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/** @author sam **/

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuariosModel usuarios_model;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    /*ESTE METODO DEVUELVE UN USUARIO ESPECIAL DE SPRING, BASANDONOS EN NUESTRA IMPLEMENTACION DE LOS DAO, CON ESTE METODO
    PERSONALIZAMOS EL COMPORTAMIENTO DE DETAILSSERVICE*/
        List<GrantedAuthority> authorities = new ArrayList<>();
        UsuariosEntity usuario = usuarios_model.findByUsuario(username);
        if(usuario != null) {
            authorities.add(new SimpleGrantedAuthority(usuario.getRoles_entity().getRole()));            
            User user_details = new User(usuario.getUsuario(), usuario.getPass(), authorities);            
            return user_details;
        } else {
            throw new UsernameNotFoundException("No pude encontrar al usuario: " + username);
        }        
    }
    
}
