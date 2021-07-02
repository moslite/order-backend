package com.moslite.orderbackend.resources;

import com.moslite.orderbackend.domain.Cliente;
import com.moslite.orderbackend.dto.ClienteDTO;
import com.moslite.orderbackend.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> find(@PathVariable Integer id) {
        Cliente cliente = clienteService.find(id);
        return ResponseEntity.ok().body(cliente);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO dto, @PathVariable Integer id) {
        Cliente obj = clienteService.fromDTO(dto);
        obj.setId(id);
        obj = clienteService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll() {
        List<Cliente> categorias = clienteService.findAll();
        List<ClienteDTO> list = categorias.stream().map(ClienteDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/page")
    public ResponseEntity<Page<ClienteDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction
    ) {
        Page<Cliente> list = clienteService.findPage(page, linesPerPage, orderBy, direction);
        Page<ClienteDTO> listDTO = list.map(ClienteDTO::new);
        return ResponseEntity.ok().body(listDTO);
    }

}
