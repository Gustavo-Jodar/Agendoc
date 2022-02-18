package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.ProfissionalDAO;
import br.ufscar.dc.dsw.domain.Profissional;

import br.ufscar.dc.dsw.util.Formata;

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
                // rota para mostrar forms de cadastro de profissional
                case "/showCadastroProfissional":
                    apresentaFormCadastro(request, response);
                    break;
                // passível de remoção
                default:
                    apresentaPaginaProfissional(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException | ParseException e) {
            throw new ServletException(e);
        }
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

        Formata f = new Formata();
        especialidade = f.formataString(especialidade);

        String area = request.getParameter("area");
        if (area.substring(0, 1).equals("1"))
            area = "medicina";
        if (area.substring(0, 1).equals("2"))
            area = "advocacia";
        if (area.substring(0, 1).equals("3"))
            area = "psicologia";
        if (area.substring(0, 1).equals("4"))
            area = "educacao";
        if (area.substring(0, 1).equals("5"))
            area = "nutricao";
        if (area.substring(0, 1).equals("6"))
            area = "terapia";

        String startDateStrNascimento = request.getParameter("nascimento");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");

        try {
            Date nascimento = sdf.parse(startDateStrNascimento);

            Profissional profissional = new Profissional(cpf, nome, email, senha, bio, area, especialidade, nascimento);
            dao.insert(profissional);

            // response.sendRedirect("/users/showLogin");

            RequestDispatcher dispatcher = request.getRequestDispatcher("/users/showLogin");
            dispatcher.forward(request, response);

        } catch (RuntimeException | ParseException | IOException e) {
            throw new ServletException(e);
        }

    }

}
