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

    <link rel="stylesheet" href="/<%= contextPath%>/styles/main.css"> 
    <link rel="stylesheet" href="/<%= contextPath%>/styles/header.css">
    <link rel="stylesheet" href="/<%= contextPath%>/styles/page-prof.css"> 
    <link rel="stylesheet" href="/<%= contextPath%>/styles/perfil.css"> 

</head>
<body id= "page-start" >
        <fmt:bundle basename="messages">
    <div id= "container">
        <header class="page-header">

            <div class="header-content">
                <strong><fmt:message key="welcome"/> ${sessionScope.usuarioLogado.nome}</strong>
                <p><fmt:message key="you_can_make_available_appointment"/></p>
            </div>
        </header>

        <main>
            <div class="buttons-container">
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
                        <strong>${consulta.nome_cliente}</strong>
                        <!-- <span>Consulta particular</span> -->
                    </div>
                </header>
            
                <p><fmt:message key="appointment_schedule_for"/> ${consulta.data_consulta}</p>
                <p><fmt:message key="time"/> ${consulta.horario}:00 de Brasilia</p>
                <p><fmt:message key="modality"/> Online</p>
            
                <footer>
                    <p>Consulta<strong>Online</strong>
                    </p>
                    <a class ="button" href="/<%= contextPath%>/profissional/editAppointment.jsp"> 
                        <fmt:message key="appointment_info"/>
                    </a>
                    <!-- Podemoriamos fazer essa opcao q aparece só se a consulta ja tiver passado: -->
                    <!-- <a class ="button"> 
                        Avaliar consulta
                    </a> -->
                </footer>
            </article>
            </c:forEach>
        </main>
    </div>
        </fmt:bundle>
</body>
</html>

