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
import br.ufscar.dc.dsw.domain.User;

public class ProfissionalDAO extends GenericDAO {
    // função para inserir uma entidade do tipo profissional no BD
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

    // função que retorna todas as entidades do tipo profissional do BD
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

    // função que retorna todas as entidades do tipo profissional do BD
    public List<Profissional> getWithFilter(String areaF, String especialidadeF) {

        List<Profissional> listaProfissionais = new ArrayList<>();
        String sql;
        if (especialidadeF == null && areaF != null || (especialidadeF == "" && areaF != null)) {
            sql = "SELECT Users.cpf, nome, email, senha, nascimento, area, especialidade, bio FROM Users, Profissionais WHERE (Users.cpf=Profissionais.cpf AND area=?);";
        } else if (areaF == null && especialidadeF != null || areaF == "" && especialidadeF != null) {
            sql = "SELECT Users.cpf, nome, email, senha, nascimento, area, especialidade, bio FROM Users, Profissionais WHERE (Users.cpf=Profissionais.cpf AND especialidade=?);";
        } else {
            sql = "SELECT Users.cpf, nome, email, senha, nascimento, area, especialidade, bio FROM Users, Profissionais WHERE (Users.cpf=Profissionais.cpf AND area=? AND especialidade=?);";
        }

        try {

            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            if (especialidadeF == null && areaF != null || especialidadeF == "" && areaF != null) {
                statement.setString(1, areaF);
            } else if (areaF == null && especialidadeF != null || areaF == "" && especialidadeF != null) {
                statement.setString(1, especialidadeF);
            } else {
                statement.setString(1, areaF);
                statement.setString(2, especialidadeF);
            }

            ResultSet resultSet = statement.executeQuery();
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

    // função que retorna um profissional a partir do seu objeto do tipo user
    public Profissional getbyLogin(User user) {
        Profissional profissional = null;

        String sql = "SELECT * from Profissionais WHERE cpf = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, user.getCpf());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String bio = resultSet.getString("bio");
                String area = resultSet.getString("area");
                String especialidade = resultSet.getString("especialidade");

                profissional = new Profissional(user.getCpf(),
                        user.getnome(), user.getEmail(), user.getSenha(),
                        bio, area, especialidade, user.getNascimento());
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return profissional;
    }

    // função para atualizar uma entidade do tipo profissional no BD
    public void update(Profissional profissional) {
        String sql = "UPDATE Users SET nome = ?, email = ?, senha = ?, nascimento = ? WHERE cpf = ? ";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, profissional.getnome());
            statement.setString(2, profissional.getEmail());
            statement.setString(3, profissional.getSenha());
            java.sql.Date sqlDNascimento = new java.sql.Date(profissional.getNascimento().getTime());
            statement.setDate(4, sqlDNascimento);
            statement.setString(5, profissional.getCpf());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        sql = "UPDATE Profissionais SET area = ? , especialidade = ?,bio = ? WHERE cpf = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, profissional.getArea());
            statement.setString(2, profissional.getEspecialidade());
            statement.setString(3, profissional.getBio());
            statement.setString(4, profissional.getCpf());

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
