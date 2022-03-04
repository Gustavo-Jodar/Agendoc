package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.UserDAO;
import br.ufscar.dc.dsw.dao.ClienteDAO;
import br.ufscar.dc.dsw.dao.ProfissionalDAO;

import br.ufscar.dc.dsw.domain.User;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Profissional;

import br.ufscar.dc.dsw.util.Erro;
import br.ufscar.dc.dsw.util.Formata;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Date;

@WebServlet(urlPatterns = "/admins/*")

public class AdminController extends HttpServlet {

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
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User usuario = (User) request.getSession().getAttribute("usuarioLogado");
        Erro erros = new Erro();

        if (usuario == null) {
            response.sendRedirect(request.getContextPath());
            return;
        } else if (!usuario.getPapel().replaceAll("\\P{L}+", "").equals("ADMIN")) {
            erros.add("Acesso não autorizado!");
            erros.add("Apenas Papel [ADMIN] tem acesso a essa página");
            request.setAttribute("mensagens", erros);
            RequestDispatcher rd = request.getRequestDispatcher("/noAuth.jsp");
            rd.forward(request, response);
            return;
        }

        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                // rota para acessar página do admin
                case "/showPaginaAdmin":
                    apresentaPaginaAdmin(request, response);
                    break;
                case "/listaClientes":
                    listaClientes(request, response);
                    break;
                case "/listaProfissionais":
                    listaProfissionais(request, response);
                    break;
                case "/apresentaEdicaoCliente":
                    apresentaEdicaoCliente(request, response);
                    break;
                case "/apresentaEdicaoProfissional":
                    apresentaEdicaoProfissioanl(request, response);
                    break;
                case "/apresentaAdicionarProfissional":
                    apresentaAdicionarProfissional(request, response);
                    break;
                case "/apresentaAdicionarCliente":
                    apresentaAdicionarCliente(request, response);
                    break;
                case "/editaCliente":
                    editaCliente(request, response);
                    break;
                case "/editaProfissional":
                    editaProfissional(request, response);
                    break;
                case "/removerProfissional":
                    removerProfissional(request, response);
                    break;
                case "/removerCliente":
                    removerCliente(request, response);
                    break;
                default:
                    apresentaPaginaAdmin(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException | ParseException e) {
            throw new ServletException(e);
        }
    }

    // função para acessar página do admin
    private void apresentaPaginaAdmin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/index.jsp");
        dispatcher.forward(request, response);
    }

    // função para listagem de clientes
    private void listaClientes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Cliente> listaClientes = daoCliente.getAll();
        request.setAttribute("listaClientes", listaClientes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/listaClientes.jsp");
        dispatcher.forward(request, response);
    }

    // função para listar profissionais
    private void listaProfissionais(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Profissional> listaProfissionais = daoProfissional.getAll();
        request.setAttribute("listaProfissionais", listaProfissionais);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/listaProfissionais.jsp");
        dispatcher.forward(request, response);
    }

    // função para acessar página de edição de cliente
    private void apresentaEdicaoCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String emailCliente = request.getParameter("email");
        User clienteUser = daoUser.getbyLogin(emailCliente);
        Cliente cliente = daoCliente.getbyLogin(clienteUser);

        System.out.println(cliente.getCpf());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/editCliente.jsp");
        request.setAttribute("clienteEdit", cliente);

        dispatcher.forward(request, response);
    }

    // função para acessar página de edição de profissional
    private void apresentaEdicaoProfissioanl(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String emailProfissional = request.getParameter("email");
        User profissionalUser = daoUser.getbyLogin(emailProfissional);
        Profissional profissional = daoProfissional.getbyLogin(profissionalUser);

        System.out.println(profissional.getCpf());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/editProfissional.jsp");
        request.setAttribute("profissionalEdit", profissional);

        dispatcher.forward(request, response);
    }

    // função para acessar página de edição de profissional
    private void apresentaAdicionarProfissional(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/addProfissional.jsp");
        dispatcher.forward(request, response);
    }

    // função para acessar página de edição de profissional
    private void apresentaAdicionarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/addCliente.jsp");
        dispatcher.forward(request, response);
    }

    // função para edição de cliente
    private void editaCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        request.setCharacterEncoding("UTF-8");
        String cpf = request.getParameter("cpf");
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String telefone = request.getParameter("telefone");
        String sexo = request.getParameter("sexo");

        String startDateStrNascimento = request.getParameter("nascimento");
        startDateStrNascimento = startDateStrNascimento.replace('/', '-');

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date nascimento = sdf.parse(startDateStrNascimento);

            Cliente cliente = new Cliente(cpf, nome, email, senha, telefone, sexo, nascimento);
            daoCliente.update(cliente);

            List<Cliente> listaClientes = daoCliente.getAll();
            request.setAttribute("listaClientes", listaClientes);

            // NAO É NO LISTA QUE É PRA REDIRECIONAAAAAAAAAAAAAAAAAAAR
            response.sendRedirect("listaClientes");

        } catch (RuntimeException | ParseException | IOException e) {
            Erro erro = new Erro();
            erro.add(
                    "Operação não sucedida, verifique se seu dados estão corretos!\nOperation failed, please check if your data is correct!");
            request.setAttribute("mensagens", erro);
            RequestDispatcher rd = request.getRequestDispatcher("/admins/apresentaEdicaoCliente");
            rd.forward(request, response);
            // throw new ServletException(e);
        }

    }

    // função para edição de rpofissional
    private void editaProfissional(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        request.setCharacterEncoding("UTF-8");
        Formata f = new Formata();

        String cpf = request.getParameter("cpf");
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String bio = request.getParameter("bio");
        String especialidade = f.formataString(request.getParameter("especialidade"));
        String area = f.formataString(request.getParameter("area"));

        String startDateStrNascimento = request.getParameter("nascimento");
        startDateStrNascimento = startDateStrNascimento.replace('/', '-');

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date nascimento = sdf.parse(startDateStrNascimento);

            Profissional profissional = new Profissional(cpf, nome, email, senha, bio, area, especialidade, nascimento);
            daoProfissional.update(profissional);

            List<Profissional> listaProfissionais = daoProfissional.getAll();
            request.setAttribute("listaProfissionaiss", listaProfissionais);

            // NAO É NO LISTA QUE É PRA REDIRECIONAAAAAAAAAAAAAAAAAAAR
            response.sendRedirect("listaProfissionais");

        } catch (RuntimeException | ParseException | IOException e) {
            Erro erro = new Erro();
            erro.add(
                    "Operação não sucedida, verifique se seu dados estão corretos!\nOperation failed, please check if your data is correct!");
            request.setAttribute("mensagens", erro);
            RequestDispatcher rd = request.getRequestDispatcher("/admins/apresentaEdicaoProfissional");
            rd.forward(request, response);
            // throw new ServletException(e);
        }
    }

    // função para remoção de cliente
    private void removerCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cpfCliente = request.getParameter("cpf");

        daoUser.remobeByCpf(cpfCliente);
        List<Cliente> listaClientes = daoCliente.getAll();

        request.setAttribute("listaClientes", listaClientes);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/listaClientes.jsp");
        dispatcher.forward(request, response);
    }

    // função para remoção de profissioanl
    private void removerProfissional(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cpfProfissional = request.getParameter("cpf");

        daoUser.remobeByCpf(cpfProfissional);
        List<Profissional> listaProfissionais = daoProfissional.getAll();

        request.setAttribute("listaProfissionais", listaProfissionais);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/listaProfissionais.jsp");
        dispatcher.forward(request, response);
    }

}
