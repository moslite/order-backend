package com.moslite.orderbackend.services;

import com.moslite.orderbackend.domain.Estado;
import com.moslite.orderbackend.dto.EstadoDTO;
import com.moslite.orderbackend.repositories.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    public List<Estado> findAll() {
        return estadoRepository.findAllByOrderByNome();
    }

}
