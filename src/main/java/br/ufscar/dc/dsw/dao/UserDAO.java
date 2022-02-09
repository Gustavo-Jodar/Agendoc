package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import br.ufscar.dc.dsw.domain.User;

public class UserDAO extends GenericDAO {
    // função que retorna um objeto do tipo User a partir do seu email
    public User getbyLogin(String LoginEmail) {
        User usuario = null;

        String sql = "SELECT * from Users WHERE email = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, LoginEmail);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String cpf = resultSet.getString("cpf");
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String papel = resultSet.getString("papel");
                Date nascimento = resultSet.getDate("nascimento");

                usuario = new User(cpf, nome, email, senha, nascimento, papel);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuario;
    }
}
