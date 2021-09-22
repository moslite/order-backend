package com.moslite.orderbackend.resources;

import com.moslite.orderbackend.domain.Cidade;
import com.moslite.orderbackend.domain.Estado;
import com.moslite.orderbackend.dto.CidadeDTO;
import com.moslite.orderbackend.dto.EstadoDTO;
import com.moslite.orderbackend.services.CidadeService;
import com.moslite.orderbackend.services.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/estados")
public class EstadoResource {

    @Autowired
    private EstadoService estadoService;

    @Autowired
    private CidadeService cidadeService;

    @GetMapping
    public ResponseEntity<List<EstadoDTO>> findAll() {
        List<Estado> estadoList = estadoService.findAll();
        List<EstadoDTO> estadoDTOList = estadoList.stream().map(EstadoDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(estadoDTOList);
    }

    @GetMapping(value = "/{estadoId}/cidades")
    public ResponseEntity<List<CidadeDTO>> findCidades(@PathVariable Integer estadoId) {
        List<Cidade> cidadeList = cidadeService.findAllByEstado(estadoId);
        List<CidadeDTO> cidadeDTOList = cidadeList.stream().map(CidadeDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(cidadeDTOList);
    }

}
