package br.ufscar.dc.dsw.domain;

import java.util.Date;

//objeto do tipo cliente que herda atributos do tipo User
public class Cliente extends User {

    private String telefone;
    private String sexo;

    public Cliente(String cpf, String nome, String email, String senha, String telefone, String sexo, Date nascimento) {
        super(cpf, nome, email, senha, nascimento, "CLIENTE");

        this.telefone = telefone;
        this.sexo = sexo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
