<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agendoc</title>
    </head>
    <body>
        <fmt:bundle basename="messages">
        <%
			String contextPath = request.getContextPath().replace("/", "");
		%>
        <div class="top-bar-container">
            <a href="login.jsp">
            <img src="../images/back.svg" alt="Voltar">
            </a>
        </div>
        <div align="center" id="container">
            <!-- <br/>
            <a href="/<%= contextPath%>/clientes/showLogin">Marcar Consulta</a>
            <br> -->
            <a class ="profissionais" href="../users/showCadastroCliente"><fmt:message key="i_am_client"/></a>
            <br>
            <a class ="profissionais" href="../users/showCadastroProfissional"><fmt:message key="i_am_professional"/></a>
            <br/>
            <br/>
        </div>
    </fmt:bundle>
    </body>
</html>
