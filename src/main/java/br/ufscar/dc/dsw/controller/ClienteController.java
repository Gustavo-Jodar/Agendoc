package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.ProfissionalDAO;
import br.ufscar.dc.dsw.dao.ClienteDAO;
import br.ufscar.dc.dsw.dao.ConsultaDAO;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Consulta;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.util.Erro;

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

import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
                    break;
                // função para cadastrar uma consulta
                case "/cadastraConsulta":
                    cardastraConsulta(request, response);
                    break;
                case "/apresentaConsulta":
                    apresentaConsulta(request, response);
                    break;
                case "/cancelaConsulta":
                    cancelaConsulta(request, response);
                    break;
                case "/mudaLinkConsulta":
                    mudaLinkConsulta(request, response);
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
            Date hoje = new Date();
            if (hoje.equals(data_consulta) || hoje.after(data_consulta)) {
                Erro erro = new Erro();
                erro.add("Data inválida! | Invalid date!");
                request.setAttribute("mensagens", erro);
                RequestDispatcher rd = request.getRequestDispatcher("/cliente/appointment.jsp");
                rd.forward(request, response);
            }
            Consulta consulta = new Consulta(cpf_profissional, usuarioLogado.getCpf(), data_consulta, 0, "link_meet",
                    profissional_escolhido.getnome(), usuarioLogado.getnome());

            List<Integer> horariosLivres = consultaDAO.getHorariosLivresDeProfissionalEClienteNoDia(consulta);

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

        // link do meet novo
        String link_meet = request.getParameter("link_meet");

        try {
            Date data_consulta = sdf.parse(startDateStrConsulta);

            Consulta consulta = new Consulta(profissional_escolhido.getCpf(), usuarioLogado.getCpf(), data_consulta,
                    horario_consulta, link_meet,
                    profissional_escolhido.getnome(), usuarioLogado.getnome());

            consultaDAO.insert(consulta);

        } catch (ParseException e) {
            throw new ServletException(e);
        }
        Properties props = new Properties();
        /** Parâmetros de conexão com servidor Gmail */
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
        "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
        new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication()
            {
                    return new PasswordAuthentication("contaETCTeste@gmail.com",
                    "Teste@123");
            }
        });

        /** Ativa Debug para sessão */
        session.setDebug(true);

        try {

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("contaETCTeste@gmail.com"));
        //Remetente

        Address[] toUser = InternetAddress //Destinatário(s)
                    .parse("contaETCTeste@gmail.com");
        //para colocar mais de um email como desinatario (profissional e cliente) fazer assim:
        // Address[] toUser = InternetAddress //Destinatário(s)
        //          .parse("seuamigo@gmail.com, seucolega@hotmail.com,
        //          seuparente@yahoo.com.br");

        message.setRecipients(Message.RecipientType.TO, toUser);
        message.setSubject("Uma consulta foi marcada - Agendoc");//Assunto
        message.setText("Seguem as informacoes a respeito de sua consulta:");

        Transport.send(message);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/clientes/showPaginaCliente");
        dispatcher.forward(request, response);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    private void apresentaConsulta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {

        Cliente usuarioLogado = (Cliente) request.getSession().getAttribute("usuarioLogado");

        // hora da consulta
        Integer hora = Integer.parseInt(request.getParameter("hora"));

        // dados do profissional
        String cpf_profissional = request.getParameter("cpf_profissional");
        Profissional profissional_escolhido = profissionalDAO.getByCpf(cpf_profissional);
        request.setAttribute("profissionalEscolhido", profissional_escolhido);

        // data da consulta
        String startDateStrConsulta = request.getParameter("data_consulta");
        startDateStrConsulta = startDateStrConsulta.replace('/', '-');
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date data_consulta = sdf.parse(startDateStrConsulta);
            Consulta consulta = new Consulta(cpf_profissional, usuarioLogado.getCpf(), data_consulta, hora, "link_meet",
                    profissional_escolhido.getnome(), usuarioLogado.getnome());

            consulta = consultaDAO.getConsulta(consulta);

            request.setAttribute("consulta", consulta);
            request.setAttribute("data_nao_formatada", startDateStrConsulta);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/cliente/editAppointment.jsp");
            dispatcher.forward(request, response);
        } catch (ParseException e) {
            throw new ServletException(e);
        }
    }

    private void cancelaConsulta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {

        Cliente usuarioLogado = (Cliente) request.getSession().getAttribute("usuarioLogado");

        // hora da consulta
        Integer hora = Integer.parseInt(request.getParameter("hora"));

        // dados do profissional
        String cpf_profissional = request.getParameter("cpf_profissional");
        Profissional profissional_escolhido = profissionalDAO.getByCpf(cpf_profissional);
        request.setAttribute("profissionalEscolhido", profissional_escolhido);

        // data da consulta
        String startDateStrConsulta = request.getParameter("data_consulta");
        startDateStrConsulta = startDateStrConsulta.replace('/', '-');
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date data_consulta = sdf.parse(startDateStrConsulta);
            Consulta consulta = new Consulta(cpf_profissional, usuarioLogado.getCpf(), data_consulta, hora, "link_meet",
                    profissional_escolhido.getnome(), usuarioLogado.getnome());

            consultaDAO.deleteConsulta(consulta);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/clientes/apresentaPaginaCliente");
            dispatcher.forward(request, response);
        } catch (ParseException e) {
            throw new ServletException(e);
        }
    }

    private void mudaLinkConsulta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {

        Cliente usuarioLogado = (Cliente) request.getSession().getAttribute("usuarioLogado");

        // hora da consulta
        Integer hora = Integer.parseInt(request.getParameter("hora"));

        // dados do profissional
        String cpf_profissional = request.getParameter("cpf_profissional");

        // link do meet novo
        String link_meet = request.getParameter("link_meet");

        // data da consulta
        String startDateStrConsulta = request.getParameter("data_consulta");
        startDateStrConsulta = startDateStrConsulta.replace('/', '-');
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date data_consulta = (Date) sdf.parse(startDateStrConsulta);
            Consulta consulta = new Consulta(cpf_profissional, usuarioLogado.getCpf(), data_consulta, hora, link_meet,
                    "ciclano", "fulano");

            consultaDAO.changeLink(consulta);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/clientes/apresentaConsulta");
            dispatcher.forward(request, response);
        } catch (ParseException e) {
            throw new ServletException(e);
        }
    }

}
