package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.ProfissionalDAO;
import br.ufscar.dc.dsw.domain.Profissional;

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

@WebServlet(urlPatterns = "/profissionais/*")

public class ProfissionalController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ProfissionalDAO dao;

    @Override
    public void init() {
        dao = new ProfissionalDAO();
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
                // rota para salvar Profissional no BD
                case "/saveProfissional":
                    saveProfissional(request, response);
                    break;
                // rota para acesso de página do profissional
                case "/showPaginaProfissional":
                    apresentaPaginaProfissional(request, response);
                    break;
                // rota para mostra login profissional (passível de remoção)
                case "/showLogin":
                    apresentaFormLogin(request, response);
                    break;
                // rota para mostrar forms de cadastro de profissional
                case "/showCadastroProfissional":
                    apresentaFormCadastro(request, response);
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
        List<Profissional> listaProfissionais = dao.getAll();
        request.setAttribute("listaProfissionais", listaProfissionais);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/profissional/lista.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/profissional/login.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaPaginaProfissional(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/profissional/index.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/profissional/cadastro.jsp");
        dispatcher.forward(request, response);
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
        String area = request.getParameter("area");

        String startDateStrNascimento = request.getParameter("nascimento");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");

        try {
            Date nascimento = sdf.parse(startDateStrNascimento);

            Profissional profissional = new Profissional(cpf, nome, email, senha, bio, area, especialidade, nascimento);
            dao.insert(profissional);

            response.sendRedirect("lista");

        } catch (RuntimeException | ParseException | IOException e) {
            throw new ServletException(e);
        }

    }

}