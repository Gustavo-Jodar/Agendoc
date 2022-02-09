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
        <div align="center" id="container">
            <a href="clientes/listar">Clientes Cadastrados</a>
            <br/>
            <a href="profissionais/listar">Profissionais Cadastrados</a>
            <br/>
            <a href="/<%= contextPath%>/clientes/showLogin">Marcar Consulta</a>
            <br>
            <a class ="profissionais" href="profissionais/showCadastroProfissional">Disponibilize Suas Consultas</a>
            <br/>
            <br/>
            <a href="/<%= contextPath%>/users/showLogin">Login</a>
            <p class="total-connections">
                Total de 1.000.000 de cadastros de profissionais já realizados!
            </p>
        </div>
    </body>
</html>
