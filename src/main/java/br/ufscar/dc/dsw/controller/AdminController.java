package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.UserDAO;
import br.ufscar.dc.dsw.dao.ClienteDAO;
import br.ufscar.dc.dsw.dao.ProfissionalDAO;

import br.ufscar.dc.dsw.domain.User;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Profissional;

import br.ufscar.dc.dsw.util.Erro;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
                default:
                    apresentaPaginaAdmin(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
    }

    // função para acessar página do admin
    private void apresentaPaginaAdmin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/index.jsp");
        dispatcher.forward(request, response);
    }

    private void listaClientes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Cliente> listaClientes = daoCliente.getAll();
        request.setAttribute("listaClientes", listaClientes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/listaClientes.jsp");
        dispatcher.forward(request, response);
    }

    private void listaProfissionais(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Profissional> listaProfissionais = daoProfissional.getAll();
        request.setAttribute("listaProfissionais", listaProfissionais);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/listaProfissionais.jsp");
        dispatcher.forward(request, response);
    }

}
