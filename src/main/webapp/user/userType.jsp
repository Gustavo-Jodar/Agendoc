<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agendoc | Plataforma de consultas online</title>
    </head>
    <body>
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
            <a class ="profissionais" href="../clientes/showCadastroCliente">Sou um cliente</a>
            <br>
            <a class ="profissionais" href="../profissionais/showCadastroProfissional">Sou um profissional</a>
            <br/>
            <br/>
        </div>
    </body>
</html>
