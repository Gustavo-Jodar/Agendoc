<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agendoc</title>
    </head> 
    <body>
        <h1>Página do Profissional</h1>
        <p>Olá ${sessionScope.usuarioLogado.nome}</p>
        <ul>
            <li>
                <!-- <a href="${pageContext.request.contextPath}/logout.jsp">Sair</a> -->
            </li>
        </ul>
    </body>
</html>