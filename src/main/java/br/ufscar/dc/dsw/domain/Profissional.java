package br.ufscar.dc.dsw.domain;

import java.util.Date;

//objeto do tipo profissional que herda atributos do tipo user
public class Profissional extends User {

    private String bio;
    private String area;
    private String especialidade;

    public Profissional(String cpf, String nome, String email, String senha, String bio, String area,
            String especialidade, Date nascimento) {

        super(cpf, nome, email, senha, nascimento, "PROFISSIONAL");
        this.bio = bio;
        this.area = area;
        this.especialidade = especialidade;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}
