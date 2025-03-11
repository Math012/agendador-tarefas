package com.math012.agendadortarefas.infrastructure.security;

import com.math012.agendadortarefas.business.dto.UsuarioDTO;
import com.math012.agendadortarefas.infrastructure.client.UsuarioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl {

    @Autowired
    private UsuarioClient usuarioClient;


    public UserDetails carregaDadosDeUsuario(String email, String token){
        UsuarioDTO usuarioDTO = usuarioClient.buscarUsuarioPorEmail(email,token);
        return User
                .withUsername(usuarioDTO.getEmail())
                .password(usuarioDTO.getSenha())
                .build();
    }
}
