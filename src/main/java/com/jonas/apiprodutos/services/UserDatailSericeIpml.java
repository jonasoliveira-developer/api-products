package com.jonas.apiprodutos.services;

import com.jonas.apiprodutos.domain.Usuario;
import com.jonas.apiprodutos.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserDatailSericeIpml implements UserDetailsService {
    @Autowired
    private UsuarioRepository repository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> user = repository.findByEmail(email);
        if (user.isPresent()) {
            return new Usuario(
                    user.get().getId(),
                    user.get().getNome(),
                    user.get().getEmail(),
                    user.get().getSenha(),
                    user.get().getRole(),
                    user.get().getPedidos());
        }
        throw new UsernameNotFoundException(email);
    }
}
