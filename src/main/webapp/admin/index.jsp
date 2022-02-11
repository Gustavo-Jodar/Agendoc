<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agendoc</title>
    </head> 
    <body>
        <h1>Página do Admin</h1>
        <p>Olá ${sessionScope.usuarioLogado.nome}</p>
        <div align="center">
            <ul>
                <li>
                    <a href="${pageContext.request.contextPath}/admins/listaClientes">CRUD Clientes</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/admins/listaProfissionais">CRUD Profissionais</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/users/logout">Sair</a>
                </li>
            </ul>
        </div>
    </body>
</html>