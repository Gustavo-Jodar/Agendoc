package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.ProfissionalDAO;
import br.ufscar.dc.dsw.dao.ConsultaDAO;
import br.ufscar.dc.dsw.dao.ClienteDAO;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Consulta;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/profissionais/*")

public class ProfissionalController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ConsultaDAO consultaDAO;
    private ClienteDAO clienteDAO;
    private ProfissionalDAO profissionalDAO;

    @Override
    public void init() {
        profissionalDAO = new ProfissionalDAO();
        consultaDAO = new ConsultaDAO();
        clienteDAO = new ClienteDAO();
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
                // rota para acesso de página do profissional
                case "/showPaginaProfissional":
                    apresentaPaginaProfissional(request, response);
                    break;
                // passível de remoção
                default:
                    apresentaPaginaProfissional(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
    }

    private void apresentaPaginaProfissional(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Profissional profissional = (Profissional) request.getSession().getAttribute("usuarioLogado");

        List<Consulta> listaConsultas = consultaDAO.getConsultasProfissional(profissional.getCpf());
        request.setAttribute("consultas", listaConsultas);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/profissional/index.jsp");
        dispatcher.forward(request, response);
    }

}
