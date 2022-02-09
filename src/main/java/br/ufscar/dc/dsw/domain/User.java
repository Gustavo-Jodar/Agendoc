package br.ufscar.dc.dsw.domain;

import java.util.Date;

//objeto usuário (admin, cliente e profissional herdam seus atributos)
public class User {

    private String cpf;
    private String nome;
    private String email;
    private String senha;
    private Date nascimento;
    private String papel;

    public User(String cpf, String nome, String email, String senha, Date nascimento, String papel) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.nascimento = nascimento;
        this.papel = papel;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public String getnome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public String getPapel() {
        return papel;
    }

    public void setPapel(String papel) {
        this.papel = papel;
    }

}
