package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.UserDAO;
import br.ufscar.dc.dsw.domain.User;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/admins/*")

public class AdminController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UserDAO dao;

    @Override
    public void init() {
        dao = new UserDAO();
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
                // rota para acessar página do admin
                case "/showPaginaAdmin":
                    apresentaPaginaAdmin(request, response);
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

}
