package br.ufscar.dc.dsw.domain;

import java.util.Date;

//objeto do tipo consulta
public class Consulta {

    private String cpf_profissional;
    private String cpf_cliente;
    private Date data_consulta;
    private int horario;
    private String link_meet;
    private String nome_profissional;
    private String nome_cliente;

    public Consulta(String cpf_profissional, String cpf_cliente, Date data_consulta, int horario, String link_meet,
            String nome_profissional, String nome_cliente) {
        this.cpf_profissional = cpf_profissional;
        this.cpf_cliente = cpf_cliente;
        this.data_consulta = data_consulta;
        this.horario = horario;
        this.link_meet = link_meet;
        this.nome_profissional = nome_profissional;
        this.nome_cliente = nome_cliente;
    }

    public String getCpf_profissional() {
        return cpf_profissional;
    }

    public void setProfissional(String cpf_profissional) {
        this.cpf_profissional = cpf_profissional;
    }

    public String getCpf_cliente() {
        return cpf_cliente;
    }

    public void setCliente(String cpf_cliente) {
        this.cpf_cliente = cpf_cliente;
    }

    public Date getData_consulta() {
        return data_consulta;
    }

    public void setData_consulta(Date data_consulta) {
        this.data_consulta = data_consulta;
    }

    public int getHorario() {
        return horario;
    }

    public void setHorario(int horario) {
        this.horario = horario;
    }

    public String getLink_meet() {
        return link_meet;
    }

    public void setLink_meet(String link_meet) {
        this.link_meet = link_meet;
    }

    public String getNome_cliente() {
        return nome_cliente;
    }

    public void setNome_cliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
    }

    public String getNome_profissional() {
        return nome_profissional;
    }

    public void setNome_profissional(String nome_profissional) {
        this.nome_profissional = nome_profissional;
    }
}
