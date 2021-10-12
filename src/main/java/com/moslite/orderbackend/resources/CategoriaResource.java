package com.moslite.orderbackend.resources;

import com.moslite.orderbackend.domain.Categoria;
import com.moslite.orderbackend.dto.CategoriaDTO;
import com.moslite.orderbackend.services.CategoriaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;

    @ApiOperation(value = "Busca por id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Categoria> find(@PathVariable Integer id) {
        Categoria categoria = categoriaService.find(id);
        return ResponseEntity.ok().body(categoria);
    }

    @ApiOperation(value = "Insere categoria")
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO dto) {
        Categoria obj = categoriaService.fromDTO(dto);
        obj = categoriaService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @ApiOperation(value = "Atualiza categoria")
    @PutMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO dto, @PathVariable Integer id) {
        Categoria obj = categoriaService.fromDTO(dto);
        obj.setId(id);
        obj = categoriaService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Não é possível excluir uma categoria que possui produtos"),
            @ApiResponse(code = 404, message = "Código inexistente")})
    @ApiOperation(value = "Deleta categoria")
    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Retorna todas categorias")
    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> findAll() {
        List<Categoria> categorias = categoriaService.findAll();
        List<CategoriaDTO> list = categorias.stream().map(CategoriaDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(list);
    }

    @ApiOperation(value = "Retorna todas categorias com paginação")
    @GetMapping(value = "/page")
    public ResponseEntity<Page<CategoriaDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction
    ) {
        Page<Categoria> list = categoriaService.findPage(page, linesPerPage, orderBy, direction);
        Page<CategoriaDTO> listDTO = list.map(CategoriaDTO::new);
        return ResponseEntity.ok().body(listDTO);
    }

}
