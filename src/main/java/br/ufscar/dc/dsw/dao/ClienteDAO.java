package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.User;

public class ClienteDAO extends GenericDAO {
    // função para inserir uma entidade do tipo cliente no BD
    public void insert(Cliente cliente) {
        String sql = "INSERT INTO Users(cpf, nome, email, senha, nascimento, papel) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, cliente.getCpf());
            statement.setString(2, cliente.getnome());
            statement.setString(3, cliente.getEmail());
            statement.setString(4, cliente.getSenha());
            java.sql.Date sqlDNascimento = new java.sql.Date(cliente.getNascimento().getTime());
            statement.setDate(5, sqlDNascimento);
            statement.setString(6, "CLIENTE");
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        sql = "INSERT INTO Clientes(cpf, telefone, sexo) VALUES (?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, cliente.getCpf());
            statement.setString(2, cliente.getTelefone());
            statement.setString(3, cliente.getSexo());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // função para listar todos os clientes do BD
    public List<Cliente> getAll() {
        List<Cliente> listaClientes = new ArrayList<>();

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            ResultSet resultSet = statement.executeQuery(
                    "SELECT Users.cpf, nome, email, senha, nascimento, telefone, sexo FROM Users, Clientes WHERE (Users.cpf=Clientes.cpf);");

            int contador = 0;
            while (resultSet.next()) {
                String cpf = resultSet.getString("cpf");
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String telefone = resultSet.getString("telefone");
                String sexo = resultSet.getString("sexo");
                Date nascimento = resultSet.getDate("nascimento");

                Cliente cliente = new Cliente(cpf, nome, email, senha, telefone, sexo, nascimento);
                listaClientes.add(cliente);
                contador = contador + 1;
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaClientes;
    }

    // função que retorna cliente a partir do seu objeto do tipo usuário
    public Cliente getbyLogin(User user) {
        Cliente cliente = null;

        String sql = "SELECT * from Clientes WHERE cpf = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, user.getCpf());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String telefone = resultSet.getString("telefone");
                String sexo = resultSet.getString("sexo");

                cliente = new Cliente(user.getCpf(),
                        user.getnome(), user.getEmail(), user.getSenha(),
                        telefone, sexo, user.getNascimento());
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cliente;
    }
}
