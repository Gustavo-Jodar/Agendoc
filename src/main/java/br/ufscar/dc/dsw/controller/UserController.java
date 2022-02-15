package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.ClienteDAO;
import br.ufscar.dc.dsw.dao.ProfissionalDAO;
import br.ufscar.dc.dsw.dao.UserDAO;
import br.ufscar.dc.dsw.util.Erro;

import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.User;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/users/*")

public class UserController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UserDAO daoUser;
    private ClienteDAO daoCliente;
    private ProfissionalDAO daoProfissional;

    @Override
    public void init() {
        daoUser = new UserDAO();
        daoCliente = new ClienteDAO();
        daoProfissional = new ProfissionalDAO();
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
                // rota para fazer login
                case "/login":
                    login_logout(request, response);
                    break;
                // rota para login_logout mas como nao tem formulário de envio faz o logout
                case "/logout":
                    login_logout(request, response);
                    // rota para mostrar página de login
                case "/showLogin":
                    apresentaFormLogin(request, response);
                    break;
                // passível de remoção
                case "/showIndex":
                    retornaIndex(request, response);
                    break;
                // passível de remoção
                default:
                    retornaIndex(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
    }

    private void apresentaFormLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/user/login.jsp");
        dispatcher.forward(request, response);
    }

    private void retornaIndex(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }

    protected void login_logout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Erro erros = new Erro();
        if (request.getParameter("loginData") != null) {
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");

            if (email == null || email.isEmpty()) {
                erros.add("Login não informado!");
            }
            if (senha == null || senha.isEmpty()) {
                erros.add("Senha não informada!");
            }

            if (!erros.isExisteErros()) {

                User usuario = daoUser.getbyLogin(email);
                if (usuario != null) {

                    if (usuario.getSenha().equals(senha)) {

                        // verifica se o papel do login é de admin
                        if (usuario.getPapel().replaceAll("\\P{L}+", "").equals("ADMIN")) {
                            request.getSession().setAttribute("usuarioLogado", usuario);
                            RequestDispatcher dispatcher = request.getRequestDispatcher("/admins/showPaginaAdmin");
                            dispatcher.forward(request, response);
                        }
                        // verifica se o papel do login é de profissional e pega infos do profissional
                        if (usuario.getPapel().replaceAll("\\P{L}+", "").equals("PROFISSIONAL")) {
                            Profissional profissional = daoProfissional.getbyLogin(usuario);
                            request.getSession().setAttribute("usuarioLogado", profissional);

                            RequestDispatcher dispatcher = request
                                    .getRequestDispatcher("/profissionais/showPaginaProfissional");
                            dispatcher.forward(request, response);

                        }
                        // verifica se o papel do login é de cliente e pega infos do cliente
                        if (usuario.getPapel().replaceAll("\\P{L}+", "").equals("CLIENTE")) {
                            Cliente cliente = daoCliente.getbyLogin(usuario);
                            request.getSession().setAttribute("usuarioLogado", cliente);

                            RequestDispatcher dispatcher = request
                                    .getRequestDispatcher("/clientes/showPaginaCliente");
                            dispatcher.forward(request, response);

                        }

                        return;
                    } else {
                        erros.add("Senha inválida!");
                    }
                } else {
                    erros.add("Usuário não encontrado!");
                }
            }
        }
        request.getSession().invalidate();

        request.setAttribute("mensagens", erros);

        request.getAttribute("javax.servlet.forward.request_uri");
    }
}
