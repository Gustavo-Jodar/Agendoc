package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.ClienteDAO;
import br.ufscar.dc.dsw.dao.ProfissionalDAO;
import br.ufscar.dc.dsw.dao.UserDAO;
import br.ufscar.dc.dsw.util.Erro;
import br.ufscar.dc.dsw.util.Formata;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;

import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.User;

import java.io.IOException;
import java.util.List;

import java.text.ParseException;
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
                case "/showProfissionais":
                    apresentaListaFiltradaProfissionais(request, response);
                    break;
                // função que verifica se já existe um usuário logado, se tiver manda para sua
                // página,
                // caso nao, manda para listagem de profissionais
                case "/verificaUsuarioLogado":
                    verificaUsuarioLogado(request, response);
                    break;
                // rota para salvar Profissional no BD
                case "/saveProfissional":
                    saveProfissional(request, response);
                    break;
                // rota para mostrar forms de cadastro de profissional
                case "/showCadastroProfissional":
                    apresentaFormCadastroProfissional(request, response);
                    break;
                // rota para salvar um cliente no BD
                case "/saveCliente":
                    saveCliente(request, response);
                    break;
                // rota para mostra forms de cadastro de cliente
                case "/showCadastroCliente":
                    apresentaFormCadastroCliente(request, response);
                    break;
                case "/retornaIndex":
                    retornaIndex(request, response);
                    break;
                default:
                    retornaIndex(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException | ParseException e) {
            throw new ServletException(e);
        }
    }

    private void apresentaFormLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/user/login.jsp");
        dispatcher.forward(request, response);
    }

    private void verificaUsuarioLogado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // checa se já tem um usuário logado, se tiver redireciona para a página dele
        User usuarioLogado = (User) request.getSession().getAttribute("usuarioLogado");

        if (usuarioLogado != null) {
            if (usuarioLogado.getPapel().replaceAll("\\P{L}+", "").equals("ADMIN")) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/admins/showPaginaAdmin");
                dispatcher.forward(request, response);
            }
            if (usuarioLogado.getPapel().replaceAll("\\P{L}+", "").equals("CLIENTE")) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/clientes/showPaginaCliente");
                dispatcher.forward(request, response);
            }
            if (usuarioLogado.getPapel().replaceAll("\\P{L}+", "").equals("PROFISSIONAL")) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/profissionais/showPaginaProfissional");
                dispatcher.forward(request, response);
            }
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/users/showProfissionais");
            dispatcher.forward(request, response);
        }

    }

    private void apresentaListaFiltradaProfissionais(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Formata f = new Formata();
        String area = request.getParameter("area");
        String especialidade = request.getParameter("especialidade");

        if ((area == null && especialidade == null) || (area == "" && especialidade == "")) {
            List<Profissional> listaProfissionais = daoProfissional.getAll();
            request.setAttribute("listaProfissionais", listaProfissionais);
        } else {
            especialidade = f.formataString(especialidade);
            area = f.formataString(area);
            List<Profissional> listaProfissionais = daoProfissional.getWithFilter(area, especialidade);
            request.setAttribute("listaProfissionais", listaProfissionais);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/seeProf.jsp");
        dispatcher.forward(request, response);
    }

    private void retornaIndex(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Profissional> listaProfissionais = daoProfissional.getAll();
        request.setAttribute("numProfissionais", listaProfissionais.size());

        User usuarioLogado = (User) request.getSession().getAttribute("usuarioLogado");

        if (usuarioLogado != null) {
            if (usuarioLogado.getPapel().replaceAll("\\P{L}+", "").equals("ADMIN")) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/admins/showPaginaAdmin");
                dispatcher.forward(request, response);
                return;
            }
            if (usuarioLogado.getPapel().replaceAll("\\P{L}+", "").equals("CLIENTE")) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/clientes/showPaginaCliente");
                dispatcher.forward(request, response);
                return;
            }
            if (usuarioLogado.getPapel().replaceAll("\\P{L}+", "").equals("PROFISSIONAL")) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/profissionais/showPaginaProfissional");
                dispatcher.forward(request, response);
                return;
            }
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);
            return;
        }

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

    private void saveProfissional(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        request.setCharacterEncoding("UTF-8");
        String cpf = request.getParameter("cpf");
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String bio = request.getParameter("bio");
        String especialidade = request.getParameter("especialidade");

        Formata f = new Formata();
        especialidade = f.formataString(especialidade);

        String area = request.getParameter("area");
        if (area.substring(0, 1).equals("1"))
            area = "MEDICINA";
        if (area.substring(0, 1).equals("2"))
            area = "ADVOCACIA";
        if (area.substring(0, 1).equals("3"))
            area = "PSICOLOGIA";
        if (area.substring(0, 1).equals("4"))
            area = "EDUCACAO";
        if (area.substring(0, 1).equals("5"))
            area = "NUTRICAO";
        if (area.substring(0, 1).equals("6"))
            area = "TERAPIA";

        area = f.formataString(area);

        String startDateStrNascimento = request.getParameter("nascimento");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");

        try {
            Date nascimento = sdf.parse(startDateStrNascimento);

            Profissional profissional = new Profissional(cpf, nome, email, senha, bio, area, especialidade, nascimento);
            daoProfissional.insert(profissional);

            User usuario = (User) request.getSession().getAttribute("usuarioLogado");

            if (usuario == null) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/users/showLogin");
                dispatcher.forward(request, response);
                return;
            } else if (usuario.getPapel().replaceAll("\\P{L}+", "").equals("ADMIN")) {
                RequestDispatcher rd = request.getRequestDispatcher("/admins/listaProfissionais");
                rd.forward(request, response);
                return;
            }

        } catch (RuntimeException | ParseException | IOException e) {
            throw new ServletException(e);
        }

    }

    private void apresentaFormCadastroProfissional(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/profissional/cadastro.jsp");
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
            daoCliente.insert(cliente);

            User usuario = (User) request.getSession().getAttribute("usuarioLogado");

            if (usuario == null) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/users/showLogin");
                dispatcher.forward(request, response);
                return;
            } else if (usuario.getPapel().replaceAll("\\P{L}+", "").equals("ADMIN")) {
                RequestDispatcher rd = request.getRequestDispatcher("/admins/listaClientes");
                rd.forward(request, response);
                return;
            }

        } catch (RuntimeException | ParseException | IOException e) {
            throw new ServletException(e);
        }

    }

    private void apresentaFormCadastroCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/cliente/cadastro.jsp");
        dispatcher.forward(request, response);
    }
}
