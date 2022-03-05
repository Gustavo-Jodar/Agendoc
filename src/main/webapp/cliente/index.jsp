<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="pt_br"> 
<head>
    <meta charset="UTF-8"> 
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Agendoc</title> 

    <%
        String contextPath = request.getContextPath().replace("/", "");
    %>

    <link rel="stylesheet" href="/<%= contextPath%>/styles/perfil.css"> 
    <link rel="stylesheet" href="/<%= contextPath%>/styles/main.css"> 
    <link rel="stylesheet" href="/<%= contextPath%>/styles/header.css">
    <link rel="stylesheet" href="/<%= contextPath%>/styles/page-prof.css"> 
    <!-- <link rel="stylesheet" href="/<%= contextPath%>/styles/forms.css"> -->

</head>
<body id= "page-start" >
        <fmt:bundle basename="messages">
    <div id= "container">
        <header class="page-header">

            <div class="header-content">
                <strong><fmt:message key="welcome"/> ${sessionScope.usuarioLogado.nome}</strong>
                <p><fmt:message key="you_can_make_appointment"/></p>
            </div>
        </header>

        <main>
            <div class="buttons-container">
                <a class ="button" href="/<%= contextPath%>/users/showProfissionais"> 
                    <fmt:message key="make_appointment"/>
                </a>
                <a class ="button" href="${pageContext.request.contextPath}/users/logout">
                    <fmt:message key="logout"/>
                </a>
            </div>
            <header class="title">
                <strong><fmt:message key="appointments"/></strong> 
            </header>
            <!-- Aqui ficaria o looping pra mostrar todas consultas, da data mais proxima da atual até a mais antiga -->
                <c:forEach var="consulta" items="${requestScope.consultas}">
                <article class="prof-item">
                    <header>
                        <div>
                            <strong>${consulta.nome_profissional}</strong>
                        </div>
                    </header>
                
                    <p><fmt:message key="appointment_schedule_for"/> ${consulta.data_consulta}</p>
                    <p><fmt:message key="time"/> ${consulta.horario}:00 de Brasilia</p>
                    <p><fmt:message key="modality"/> Online</p>
                
                    <footer>
                        </p>
                        <a class ="button" href="/<%= contextPath%>/clientes/apresentaConsulta?data_consulta=<c:out value='${consulta.data_consulta}'/>&cpf_profissional=<c:out value='${consulta.cpf_profissional}' />&hora=<c:out value='${consulta.horario}'/>"> 
                            <fmt:message key="appointment_info"/>
                        </a>
                    </footer>
                </article>
                </c:forEach>
        </main>
    </div>
        </fmt:bundle>
</body>
</html>

