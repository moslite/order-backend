package com.moslite.orderbackend.services;

import com.moslite.orderbackend.domain.Categoria;
import com.moslite.orderbackend.domain.Cidade;
import com.moslite.orderbackend.domain.Cliente;
import com.moslite.orderbackend.domain.Endereco;
import com.moslite.orderbackend.domain.enums.TipoCliente;
import com.moslite.orderbackend.dto.ClienteDTO;
import com.moslite.orderbackend.dto.ClienteNewDTO;
import com.moslite.orderbackend.repositories.ClienteRepository;
import com.moslite.orderbackend.repositories.EnderecoRepository;
import com.moslite.orderbackend.services.exceptions.DataIntegrityException;
import com.moslite.orderbackend.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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
            throw new DataIntegrityException("Não é possível excluir uma entidade com pedidos relacionados.");
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
        return new Cliente(dto.getId(), dto.getNome(), dto.getEmail(), null, null, null);
    }

    public Cliente fromDTO(ClienteNewDTO dto) {
        Cliente cliente = new Cliente(null, dto.getNome(), dto.getEmail(), dto.getCpfCnpj(), TipoCliente.toEnum(dto.getTipo()), bCryptPasswordEncoder.encode(dto.getSenha()));
        Cidade cidade = new Cidade(dto.getCidadeId(), null, null);
        Endereco endereco = new Endereco(null, dto.getLogradouro(), dto.getNumero(), dto.getComplemento(), dto.getBairro(), dto.getCep(), cliente, cidade);
        cliente.getEnderecos().add(endereco);
        cliente.getTelefones().add(dto.getTelefone1());
        if (dto.getTelefone2() != null) {
            cliente.getTelefones().add(dto.getTelefone2());
        }
        if (dto.getTelefone3() != null) {
            cliente.getTelefones().add(dto.getTelefone3());
        }
        return cliente;
    }

    public Cliente insert(Cliente obj) {
        obj.setId(null);
        obj = clienteRepository.save(obj);
        enderecoRepository.saveAll(obj.getEnderecos());
        return obj;
    }

    public Cliente findByEmail(String email) {
        return clienteRepository.findByEmail(email);
    }

    private void updateData(Cliente cliente, Cliente obj) {
        cliente.setNome(obj.getNome());
        cliente.setEmail(obj.getEmail());
    }

}
