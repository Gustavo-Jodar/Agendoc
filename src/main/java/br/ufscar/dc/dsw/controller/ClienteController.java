package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.ClienteDAO;
import br.ufscar.dc.dsw.domain.Cliente;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/clientes/*")

public class ClienteController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ClienteDAO dao;

    @Override
    public void init() {
        dao = new ClienteDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {

        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                // rota para salvar um cliente no BD
                case "/saveCliente":
                    saveCliente(request, response);
                    break;
                // rota para apresentar login de cliente (passível remoção)
                case "/showLogin":
                    apresentaFormLogin(request, response);
                    break;
                // rota para mostra forms de cadastro de cliente

                case "/showCadastroCliente":
                    apresentaFormCadastro(request, response);
                    break;
                // rota para mostra página de cliente
                case "/showPaginaCliente":
                    apresentaPaginaCliente(request, response);
                    break;
                // passível de remoção
                default:
                    lista(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException | ParseException e) {
            throw new ServletException(e);
        }
    }

    private void lista(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Cliente> listaClientes = dao.getAll();
        request.setAttribute("listaClientes", listaClientes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/cliente/lista.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/cliente/login.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaPaginaCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/cliente/index.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/cliente/cadastro.jsp");
        dispatcher.forward(request, response);
    }

    private void saveCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        request.setCharacterEncoding("UTF-8");
        String cpf = request.getParameter("cpf");
        String nome = request.getParameter("name");
        String email = request.getParameter("email");
        String senha = request.getParameter("pass");
        String telefone = request.getParameter("telefone");
        String sexo = request.getParameter("sexo");

        String startDateStrNascimento = request.getParameter("birth-date");
        startDateStrNascimento = startDateStrNascimento.replace('/', '-');

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");

        try {
            Date nascimento = sdf.parse(startDateStrNascimento);

            Cliente cliente = new Cliente(cpf, nome, email, senha, telefone, sexo, nascimento);
            dao.insert(cliente);

            // NAO É NO LISTA QUE É PRA REDIRECIONAAAAAAAAAAAAAAAAAAAR
            response.sendRedirect("lista");

        } catch (RuntimeException | ParseException | IOException e) {
            throw new ServletException(e);
        }

    }

}
