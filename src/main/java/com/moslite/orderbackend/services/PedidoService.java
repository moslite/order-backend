package com.moslite.orderbackend.services;

import com.moslite.orderbackend.domain.Pedido;
import com.moslite.orderbackend.repositories.PedidoRepository;
import com.moslite.orderbackend.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido buscar(Integer id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        return pedido.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + " Tipo: " +
                Pedido.class.getName()));
    }

}
