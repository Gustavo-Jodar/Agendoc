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
            <a href="/<%= contextPath%>/users/showLogin">Login</a>
            <p class="total-connections">
                Total de 1.000.000 de cadastros de profissionais já realizados!
            </p>
        </div>
    </body>
</html>
