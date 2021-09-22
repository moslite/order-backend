package com.moslite.orderbackend.services;

import com.moslite.orderbackend.domain.Cidade;
import com.moslite.orderbackend.repositories.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    public List<Cidade> findAll() {
        return cidadeRepository.findAll();
    }

    public List<Cidade> findAllByEstado(Integer estadoId) {
        return cidadeRepository.findCidades(estadoId);
    }

}
