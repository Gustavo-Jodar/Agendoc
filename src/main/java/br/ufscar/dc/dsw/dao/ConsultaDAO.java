package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Consulta;
import br.ufscar.dc.dsw.domain.Profissional;

public class ConsultaDAO extends GenericDAO {

    // função para inserir uma entidade do tipo Consulta no BD
    public void insert(Consulta consulta) {
        String sql = "INSERT INTO Consultas(cpf_profissional, cpf_cliente, , data_consulta, horario, link_meet, nome_profissional, nome_cliente) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, consulta.getCpfProfissional());
            statement.setString(2, consulta.getCpfCliente());
            java.sql.Date sqlDNascimento = new java.sql.Date(consulta.getData_consulta().getTime());
            statement.setDate(3, sqlDNascimento);
            statement.setInt(4, consulta.getHorario());
            statement.setString(5, consulta.getLink_meet());
            statement.setString(6, consulta.getNome_profissional());
            statement.setString(7, consulta.getNome_cliente());

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // função que retorna todas as consultas de um profissional do BD
    public List<Consulta> getConsultasProfissional(String cpf_prof) {

        List<Consulta> listaConsultas = new ArrayList<>();
        String sql = "SELECT cpf_profissional, cpf_cliente, data_consulta, horario, link_meet, nome_profissional, nome_cliente FROM Consultas WHERE Consultas.cpf_profissional = ? ;";

        try {
            cpf_prof = cpf_prof.replaceAll("\\s+", "");
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, cpf_prof);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String cpf_profissional = resultSet.getString("cpf_profissional");
                String cpf_cliente = resultSet.getString("cpf_cliente");
                Date data_consulta = resultSet.getDate("data_consulta");
                Integer horario = resultSet.getInt("horario");
                String link_meet = resultSet.getString("link_meet");
                String nome_profissional = resultSet.getString("nome_profissional");
                String nome_cliente = resultSet.getString("nome_cliente");

                Consulta consulta = new Consulta(cpf_profissional, cpf_cliente, data_consulta, horario, link_meet,
                        nome_profissional, nome_cliente);
                listaConsultas.add(consulta);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaConsultas;
    }

    // função que retorna todas as consultas de um profissional do BD
    public List<Consulta> getConsultasCliente(String cpf_client) {

        List<Consulta> listaConsultas = new ArrayList<>();
        String sql = "SELECT cpf_profissional, cpf_cliente, data_consulta, horario, link_meet, nome_profissional, nome_cliente FROM Consultas WHERE Consultas.cpf_cliente = ? ;";

        try {
            cpf_client = cpf_client.replaceAll("\\s+", "");
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, cpf_client);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String cpf_profissional = resultSet.getString("cpf_profissional");
                String cpf_cliente = resultSet.getString("cpf_cliente");
                Date data_consulta = resultSet.getDate("data_consulta");
                Integer horario = resultSet.getInt("horario");
                String link_meet = resultSet.getString("link_meet");
                String nome_profissional = resultSet.getString("nome_profissional");
                String nome_cliente = resultSet.getString("nome_cliente");

                Consulta consulta = new Consulta(cpf_profissional, cpf_cliente, data_consulta, horario, link_meet,
                        nome_profissional, nome_cliente);
                listaConsultas.add(consulta);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaConsultas;
    }
}