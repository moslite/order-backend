package com.moslite.orderbackend.services;

import com.moslite.orderbackend.domain.Cliente;
import com.moslite.orderbackend.dto.ClienteDTO;
import com.moslite.orderbackend.repositories.ClienteRepository;
import com.moslite.orderbackend.services.exceptions.DataIntegrityException;
import com.moslite.orderbackend.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente find(Integer id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + " Tipo: " +
                Cliente.class.getName()));
    }

    public Cliente update(Cliente obj) {
        Cliente cliente = find(obj.getId());
        updateData(cliente, obj);
        return clienteRepository.save(cliente);
    }

    public void delete(Integer id) {
        find(id);

        try {
            clienteRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma entidade relacionada.");
        }
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return clienteRepository.findAll(pageRequest);
    }

    public Cliente fromDTO(ClienteDTO dto) {
        return new Cliente(dto.getId(), dto.getNome(), dto.getEmail(), null, null);
    }

    private void updateData(Cliente cliente, Cliente obj) {
        cliente.setNome(obj.getNome());
        cliente.setEmail(obj.getEmail());
    }

}
