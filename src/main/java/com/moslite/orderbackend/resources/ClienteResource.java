package com.moslite.orderbackend.resources;

import com.moslite.orderbackend.domain.Cliente;
import com.moslite.orderbackend.dto.ClienteDTO;
import com.moslite.orderbackend.dto.ClienteNewDTO;
import com.moslite.orderbackend.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<List<ClienteDTO>> findAll() {
        List<Cliente> categorias = clienteService.findAll();
        List<ClienteDTO> list = categorias.stream().map(ClienteDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/page")
    @PreAuthorize("hasAnyRole('ADMIN')")
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

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO dto) {
        Cliente obj = clienteService.fromDTO(dto);
        obj = clienteService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

}
