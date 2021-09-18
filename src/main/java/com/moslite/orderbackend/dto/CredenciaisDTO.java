package com.moslite.orderbackend.dto;

import java.io.Serializable;

public class CredenciaisDTO implements Serializable {

    private static final long serialVersionUID = 5588085554493673183L;

    private String email;
    private String senha;

    public CredenciaisDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
