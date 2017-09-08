package com.sam.webappad.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/** @author sam **/

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private CustomUserDetailsService custom_user_details_service;
    /*ESTE ATRIBUTO ES DE CustomUserDetailsService POR QUE MEDIANTE ESTE SERVICIO OBTENEMOS LOS DATOS DEL USUARIO*/
    
    @Autowired
    private BCryptPasswordEncoder b_crypt_password_encoder;
        
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    /*ESTE METODO RETORNA UN TOKEN DE SEGURIDAD CON LAS CREDENCIALES DEL USUARIO QUE YA INICIO SESION*/    
        String principal = authentication.getName();
        /*ESTE ATRIBUTO ALMACENARA LO QUE SEA QUE SE INTRODUJO COMO NOMBRE DE USUARIO EN EL FORMULARIO DE LOGIN, MISMO QUE SE OBTIEN CON: authentication.getName()*/
        String credentials = (String) authentication.getCredentials();/*ESTE ATRIBUTO ALMACENARA LA CONTRASEÑA*/
        User user_details = (User) custom_user_details_service.loadUserByUsername(principal);
        if (user_details != null) {
            if (b_crypt_password_encoder.matches(credentials, user_details.getPassword())) {
                return new UsernamePasswordAuthenticationToken(principal, credentials, user_details.getAuthorities());/*ESTE ES EL TOKEN CON TODOS LOS DATOS QUE DEBEMOS RETORNAR*/
            } else {
                throw new BadCredentialsException("Error de login, contraseña erronea para el usuario: " + principal);
            }
        } else {
            throw new BadCredentialsException("Error de login, no encotre datos relacionados con el usuario: " + principal);
        }
    }

    @Override
    public boolean supports(Class<?> type) {
        return true;/*SIEMPRE DEBE RETORNAR TRUE, DE LO CONTRARIO NO FUNCIONA*/
    }
    
}
