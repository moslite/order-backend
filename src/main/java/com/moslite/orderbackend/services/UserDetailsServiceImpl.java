package com.moslite.orderbackend.services;

import com.moslite.orderbackend.domain.Cliente;
import com.moslite.orderbackend.repositories.ClienteRepository;
import com.moslite.orderbackend.security.UserSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Cliente cliente = clienteRepository.findByEmail(email);
        if (cliente == null) {
            throw new UsernameNotFoundException(email);
        }

        return new UserSecurity(cliente.getId(), cliente.getEmail(), cliente.getSenha(), cliente.getPerfis());
    }

}
