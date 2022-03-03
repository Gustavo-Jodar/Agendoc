package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.ProfissionalDAO;
import br.ufscar.dc.dsw.dao.ClienteDAO;
import br.ufscar.dc.dsw.dao.ConsultaDAO;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Consulta;
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

@WebServlet(urlPatterns = "/clientes/*")

public class ClienteController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ClienteDAO dao;
    private ConsultaDAO consultaDAO;
    private ProfissionalDAO profissionalDAO;

    @Override
    public void init() {
        dao = new ClienteDAO();
        consultaDAO = new ConsultaDAO();
        profissionalDAO = new ProfissionalDAO();
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
                // rota para mostra página de cliente
                case "/showPaginaCliente":
                    apresentaPaginaCliente(request, response);
                    break;
                // função que reapresenta a pagina de marcar consulta com os horários
                // disponíveis
                case "/reapresentaMarcarConsulta":
                    reapresentaMarcarConsulta(request, response);
                    // função para cadastrar uma consulta
                case "/cadastraConsulta":
                    cardastraConsulta(request, response);
                default:
                    apresentaPaginaCliente(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException | ParseException e) {
            throw new ServletException(e);
        }
    }

    private void apresentaPaginaCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cliente cliente = (Cliente) request.getSession().getAttribute("usuarioLogado");

        List<Consulta> listaConsultas = consultaDAO.getConsultasCliente(cliente.getCpf());
        request.setAttribute("consultas", listaConsultas);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/cliente/index.jsp");
        dispatcher.forward(request, response);
    }

    private void reapresentaMarcarConsulta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        request.setCharacterEncoding("UTF-8");

        // recolhendo informações do profissional novamente
        String cpf_profissional = request.getParameter("cpf");
        Profissional profissional_escolhido = profissionalDAO.getByCpf(cpf_profissional);
        request.setAttribute("profissionalEscolhido", profissional_escolhido);

        // recolhendo a data escolhida
        String startDateStrConsulta = request.getParameter("data_consulta");
        startDateStrConsulta = startDateStrConsulta.replace('/', '-');
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // recolhendo o cpf do usuario logado
        Cliente usuarioLogado = (Cliente) request.getSession().getAttribute("usuarioLogado");

        try {
            Date data_consulta = sdf.parse(startDateStrConsulta);

            Consulta consulta = new Consulta(cpf_profissional, usuarioLogado.getCpf(), data_consulta, 0, "link_meet",
                    profissional_escolhido.getnome(), usuarioLogado.getnome());

            List<Integer> horariosLivres = consultaDAO.getHorariosLivresDeProfissionalEClienteNoDia(consulta);
            System.out.println(horariosLivres);

            request.setAttribute("horariosLivres", horariosLivres);
            request.setAttribute("consulta_aux", consulta);
            request.setAttribute("data_nao_formatada", startDateStrConsulta);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/cliente/appointment.jsp");
            dispatcher.forward(request, response);
        } catch (ParseException e) {
            throw new ServletException(e);
        }

    }

    private void cardastraConsulta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        request.setCharacterEncoding("UTF-8");

        // recolhendo informações do profissional novamente
        String cpf_profissional = request.getParameter("cpf_profissional");
        Profissional profissional_escolhido = profissionalDAO.getByCpf(cpf_profissional);
        request.setAttribute("profissionalEscolhido", profissional_escolhido);

        // recolhendo a data escolhida
        String startDateStrConsulta = request.getParameter("data_consulta");
        startDateStrConsulta = startDateStrConsulta.replace('/', '-');
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // recolhendo o cpf do usuario logado
        Cliente usuarioLogado = (Cliente) request.getSession().getAttribute("usuarioLogado");

        // recolhendo horário escolhido
        Integer horario_consulta = Integer.parseInt(request.getParameter("horario"));

        try {
            Date data_consulta = sdf.parse(startDateStrConsulta);

            Consulta consulta = new Consulta(profissional_escolhido.getCpf(), usuarioLogado.getCpf(), data_consulta,
                    horario_consulta, "ainda sem link",
                    profissional_escolhido.getnome(), usuarioLogado.getnome());

            consultaDAO.insert(consulta);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/clientes/showPaginaCliente");
            dispatcher.forward(request, response);
        } catch (ParseException e) {
            throw new ServletException(e);
        }

    }

}
