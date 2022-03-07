package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.ProfissionalDAO;
import br.ufscar.dc.dsw.dao.ConsultaDAO;
import br.ufscar.dc.dsw.dao.ClienteDAO;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Consulta;

import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
                case "/apresentaConsulta":
                    apresentaConsulta(request, response);
                    break;
                case "/cancelaConsulta":
                    cancelaConsulta(request, response);
                    break;
                default:
                    apresentaPaginaProfissional(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ParseException | ServletException e) {
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

    private void apresentaConsulta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {

        Profissional usuarioLogado = (Profissional) request.getSession().getAttribute("usuarioLogado");

        // hora da consulta
        Integer hora = Integer.parseInt(request.getParameter("hora"));

        // dados do profissional
        String cpf_cliente = request.getParameter("cpf_cliente");
        Profissional profissional_escolhido = profissionalDAO.getByCpf(usuarioLogado.getCpf());
        request.setAttribute("profissionalEscolhido", profissional_escolhido);

        // data da consulta
        String startDateStrConsulta = request.getParameter("data_consulta");
        startDateStrConsulta = startDateStrConsulta.replace('/', '-');
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date data_consulta = (Date) sdf.parse(startDateStrConsulta);
            Consulta consulta = new Consulta(usuarioLogado.getCpf(), cpf_cliente, data_consulta, hora, "link_meet",
                    profissional_escolhido.getnome(), "fulano");

            consulta = consultaDAO.getConsulta(consulta);

            request.setAttribute("consulta", consulta);
            request.setAttribute("data_nao_formatada", startDateStrConsulta);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/profissional/editAppointment.jsp");
            dispatcher.forward(request, response);
        } catch (ParseException e) {
            throw new ServletException(e);
        }
    }

    private void cancelaConsulta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {

        Profissional usuarioLogado = (Profissional) request.getSession().getAttribute("usuarioLogado");

        // hora da consulta
        Integer hora = Integer.parseInt(request.getParameter("hora"));

        // dados do profissional
        String cpf_cliente = request.getParameter("cpf_cliente");
        Profissional profissional_escolhido = profissionalDAO.getByCpf(usuarioLogado.getCpf());
        request.setAttribute("profissionalEscolhido", profissional_escolhido);

        // data da consulta
        String startDateStrConsulta = request.getParameter("data_consulta");
        startDateStrConsulta = startDateStrConsulta.replace('/', '-');
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date data_consulta = (Date) sdf.parse(startDateStrConsulta);
            Consulta consulta = new Consulta(usuarioLogado.getCpf(), cpf_cliente, data_consulta, hora, "link_meet",
                    profissional_escolhido.getnome(), "fulano");

            consultaDAO.deleteConsulta(consulta);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/profissionais/apresentaPaginaProfissional");
            dispatcher.forward(request, response);
        } catch (ParseException e) {
            throw new ServletException(e);
        }
    }

}
