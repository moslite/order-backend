package com.moslite.orderbackend.dto;

import com.moslite.orderbackend.domain.Estado;

import java.io.Serializable;

public class EstadoDTO implements Serializable {

    private static final long serialVersionUID = 1995448240803738363L;

    private Integer id;

    private String nome;

    public EstadoDTO(Estado obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
