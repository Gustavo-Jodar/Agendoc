package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Profissional;

public class ProfissionalDAO extends GenericDAO {
    public void insert(Profissional profissional) {
        String sql = "INSERT INTO Users(cpf, nome, email, senha, nascimento, papel) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, profissional.getCpf());
            statement.setString(2, profissional.getnome());
            statement.setString(3, profissional.getEmail());
            statement.setString(4, profissional.getSenha());
            java.sql.Date sqlDNascimento = new java.sql.Date(profissional.getNascimento().getTime());
            statement.setDate(5, sqlDNascimento);
            statement.setString(6, "PROFISSIONAL");
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        sql = "INSERT INTO Profissionais(cpf, area, especialidade, bio) VALUES (?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, profissional.getCpf());
            statement.setString(2, profissional.getArea());
            statement.setString(3, profissional.getEspecialidade());
            statement.setString(4, profissional.getBio());

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Profissional> getAll() {

        List<Profissional> listaProfissionais = new ArrayList<>();

        String sql = "SELECT Users.cpf, nome, email, senha, nascimento, area, especialidade, bio FROM Users, Profissionais WHERE (Users.cpf=Profissionais.cpf);";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String cpf = resultSet.getString("cpf");
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String bio = resultSet.getString("bio");
                String area = resultSet.getString("area");
                String especialidade = resultSet.getString("especialidade");
                Date nascimento = resultSet.getDate("nascimento");

                Profissional profissional = new Profissional(cpf, nome, email, senha, bio, area, especialidade,
                        nascimento);
                listaProfissionais.add(profissional);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaProfissionais;
    }
}
