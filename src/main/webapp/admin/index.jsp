<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agendoc</title>
    </head> 
    <body>
        <fmt:bundle basename="messages">
        <h1><fmt:message key="admin_page"/></h1>
        <p><fmt:message key="welcome"/> Admin</p>
        <div align="center">
            <ul>
                <li>
                    <a href="${pageContext.request.contextPath}/admins/listaClientes"><fmt:message key="client_crud"/></a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/admins/listaProfissionais"><fmt:message key="professional_crud"/></a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/users/logout"><fmt:message key="logout"/></a>
                </li>
            </ul>
        </div>
        </fmt:bundle>
    </body>
</html>