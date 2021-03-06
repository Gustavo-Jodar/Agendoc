package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.ClienteDAO;
import br.ufscar.dc.dsw.dao.ProfissionalDAO;
import br.ufscar.dc.dsw.dao.UserDAO;
import br.ufscar.dc.dsw.util.Erro;
import br.ufscar.dc.dsw.util.Formata;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
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
import javax.servlet.http.Part;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.MultipartConfig;

@WebServlet(urlPatterns = "/users/*")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)

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
                // rota para login_logout mas como nao tem formul??rio de envio faz o logout
                case "/logout":
                    login_logout(request, response);
                    // rota para mostrar p??gina de login
                case "/showLogin":
                    apresentaFormLogin(request, response);
                    break;
                // pass??vel de remo????o
                case "/showIndex":
                    retornaIndex(request, response);
                    break;
                case "/showProfissionais":
                    apresentaListaFiltradaProfissionais(request, response);
                    break;
                // fun????o que verifica se j?? existe um usu??rio logado, se tiver manda para sua
                // p??gina,
                // caso nao, manda para listagem de profissionais
                case "/verificaUsuarioLogado":
                    verificaUsuarioLogado(request, response);
                    break;
                // verifica se h?? usuario logado
                case "/verificaEstaLogado":
                    verificaEstaLogado(request, response);
                    break;
                // verifica se o usuario deve ser encaminhado para o login ou perfil:
                case "/verificaLogin":
                    verificaLogin(request, response);
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
        // checa se j?? tem um usu??rio logado, se tiver redireciona para a p??gina dele
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

    private void verificaEstaLogado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // checa se j?? tem um usu??rio logado
        User usuarioLogado = (User) request.getSession().getAttribute("usuarioLogado");

        if (usuarioLogado != null) {
            // se ja tiver logado abre link pra marcar consulta.
            String cpf_profissional = request.getParameter("cpf");
            Profissional profissional_escolhido = daoProfissional.getByCpf(cpf_profissional);
            request.setAttribute("profissionalEscolhido", profissional_escolhido);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/cliente/appointment.jsp");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/user/login.jsp");
            dispatcher.forward(request, response);
        }

    }

    private void verificaLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // checa se j?? tem um usu??rio logado, se tiver redireciona para a p??gina dele
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
            RequestDispatcher dispatcher = request.getRequestDispatcher("/user/login.jsp");
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
                erros.add("Login n??o informado!");
            }
            if (senha == null || senha.isEmpty()) {
                erros.add("Senha n??o informada!");
            }

            if (!erros.isExisteErros()) {

                User usuario = daoUser.getbyLogin(email);
                if (usuario != null) {

                    if (usuario.getSenha().equals(senha)) {

                        // verifica se o papel do login ?? de admin
                        if (usuario.getPapel().replaceAll("\\P{L}+", "").equals("ADMIN")) {
                            request.getSession().setAttribute("usuarioLogado", usuario);
                            RequestDispatcher dispatcher = request.getRequestDispatcher("/admins/showPaginaAdmin");
                            dispatcher.forward(request, response);
                        }
                        // verifica se o papel do login ?? de profissional e pega infos do profissional
                        if (usuario.getPapel().replaceAll("\\P{L}+", "").equals("PROFISSIONAL")) {
                            Profissional profissional = daoProfissional.getbyLogin(usuario);
                            request.getSession().setAttribute("usuarioLogado", profissional);

                            RequestDispatcher dispatcher = request
                                    .getRequestDispatcher("/profissionais/showPaginaProfissional");
                            dispatcher.forward(request, response);

                        }
                        // verifica se o papel do login ?? de cliente e pega infos do cliente
                        if (usuario.getPapel().replaceAll("\\P{L}+", "").equals("CLIENTE")) {
                            Cliente cliente = daoCliente.getbyLogin(usuario);
                            request.getSession().setAttribute("usuarioLogado", cliente);

                            RequestDispatcher dispatcher = request
                                    .getRequestDispatcher("/clientes/showPaginaCliente");
                            dispatcher.forward(request, response);

                        }

                        return;
                    } else {
                        erros.add("Senha inv??lida!\nInvalid Password!");
                    }
                } else {
                    erros.add("Usu??rio n??o encontrado!\nUser not found!");
                }
            }
        }
        request.getSession().invalidate();

        request.setAttribute("mensagens", erros);

        request.getAttribute("javax.servlet.forward.request_uri");

        RequestDispatcher dispatcher = request
                .getRequestDispatcher("/user/login.jsp");
        dispatcher.forward(request, response);

    }

    private void saveProfissional(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {

        Erro erros = new Erro();
        request.setCharacterEncoding("UTF-8");
        String cpf = request.getParameter("cpf");
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String bio = request.getParameter("bio");
        String especialidade = request.getParameter("especialidade");

        Random random = new Random();
        Integer n = random.nextInt(100000);
        String novoNome = "curriculo-" + n.toString() + ".pdf";

        try {
            Part filePart = request.getPart("file");
            String fileName = filePart.getSubmittedFileName();

            for (Part part : request.getParts()) {
                part.write("/home/gustavo/Documentos/facul/WEB1/T1/Agendoc/src/main/webapp/uploads/" + novoNome);
                /// Users/sophiaschuster/Agendoc/src/main/webapp/uploads/"
            }
        } catch (RuntimeException | IOException e) {

            request.setAttribute("mensagens", erros);
            erros.add(
                    "Opera????o n??o sucedida, verifique se o seu pdf ?? v??lido est??o corretos!\nOperation failed, please check if your pdf is valid!");
            User usuario = (User) request.getSession().getAttribute("usuarioLogado");
            if (usuario == null) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/profissional/cadastro.jsp");
                dispatcher.forward(request, response);
                return;
            } else if (usuario.getPapel().replaceAll("\\P{L}+", "").equals("ADMIN")) {
                RequestDispatcher rd = request.getRequestDispatcher("/admin/addProfissional.jsp");
                rd.forward(request, response);
                return;
            }

        }

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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date nascimento = sdf.parse(startDateStrNascimento);

            Profissional profissional = new Profissional(cpf, nome, email, senha, bio, area, especialidade, nascimento,
                    novoNome);
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

            request.setAttribute("mensagens", erros);
            erros.add(
                    "Opera????o n??o sucedida, verifique se seu dados est??o corretos!\nOperation failed, please check if your data is correct!");
            User usuario = (User) request.getSession().getAttribute("usuarioLogado");
            if (usuario == null) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/profissional/cadastro.jsp");
                dispatcher.forward(request, response);
                return;
            } else if (usuario.getPapel().replaceAll("\\P{L}+", "").equals("ADMIN")) {
                RequestDispatcher rd = request.getRequestDispatcher("/admin/addProfissional.jsp");
                rd.forward(request, response);
                return;
            }

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
        Erro erros = new Erro();

        String cpf = request.getParameter("cpf");
        String nome = request.getParameter("name");
        String email = request.getParameter("email");
        String senha = request.getParameter("pass");
        String telefone = request.getParameter("telefone");
        String sexo = request.getParameter("sexo");

        String startDateStrNascimento = request.getParameter("birth-date");
        startDateStrNascimento = startDateStrNascimento.replace('/', '-');

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

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
            request.setAttribute("mensagens", erros);
            erros.add(
                    "Opera????o n??o sucedida, verifique se seu dados est??o corretos!\nOperation failed, please check if your data is correct!");
            User usuario = (User) request.getSession().getAttribute("usuarioLogado");
            if (usuario == null) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/cliente/cadastro.jsp");
                dispatcher.forward(request, response);
                return;
            } else if (usuario.getPapel().replaceAll("\\P{L}+", "").equals("ADMIN")) {
                RequestDispatcher rd = request.getRequestDispatcher("/admin/addCliente.jsp");
                rd.forward(request, response);
                return;
            }
        }

    }

    private void apresentaFormCadastroCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/cliente/cadastro.jsp");
        dispatcher.forward(request, response);
    }
}
