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
        <div id= "container">
            <!-- apenas para fins de testes -->
            <a href="clientes/listar">Clientes Cadastrados</a>
            <!-- apenas para fins de testes -->
            <div class="buttons-container">
                
                <a href="/<%= contextPath%>/clientes/loginPage">Marcar Consulta</a>
                <!-- Apenas enquanto nao faco o css -->
                <br>
                <!-- Apenas enquanto nao faco o css -->
                <a class ="profissionais" href="profissional/profissional-login.html">
                    Disponibilize Suas Consultas
                </a>
            </div>
        
            <p class="total-connections">
                Total de 200 cadastros de profissionais já realizados
            </p>
        </div>
    </body>
</html>
