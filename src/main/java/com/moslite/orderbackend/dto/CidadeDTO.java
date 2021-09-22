package com.moslite.orderbackend.dto;

import com.moslite.orderbackend.domain.Cidade;
import com.moslite.orderbackend.domain.Estado;

import java.io.Serializable;

public class CidadeDTO implements Serializable {

    private static final long serialVersionUID = -7757892286096527679L;

    private Integer id;

    private String nome;

    public CidadeDTO(Cidade obj) {
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
