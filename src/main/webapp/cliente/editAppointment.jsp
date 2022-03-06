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
    <link rel="stylesheet" href="/<%= contextPath%>/styles/forms.css">
    <link rel="stylesheet" href="/<%= contextPath%>/styles/appointment.css">
    <link rel="stylesheet" href="/<%= contextPath%>/styles/erro.css">

</head>
<body id= "page-start" >
    <fmt:bundle basename="messages">
        <div id= "container">
            <header class="page-header">
                <div class="top-bar-container">
                    <a href="/<%= contextPath%>/clientes/showPaginaCliente">
                    <img src="/<%= contextPath%>/images/back.svg" alt="Voltar">
                    </a>
                </div>
                <div class="header-content">
                    <strong><fmt:message key="any_problem"/></strong>
                    <span><fmt:message key="any_problem_sub"/></span>
                </div>
            </header>
            <div class="schedule">
                <div class="link-time">
                    <strong><fmt:message key="professional"/>:</strong>
                    <legend> ${profissionalEscolhido.nome} ${profissionalEscolhido.area} - ${profissionalEscolhido.especialidade}</legend>
                </div>
                <div class="link-time">
                    <strong><fmt:message key="date_time"/>:</strong> 
                    <legend> ${consulta.data_consulta} - ${consulta.horario}h00min</legend>
                </div>
                <div class="link-time">
                    <strong><fmt:message key="enter_link"/>:</strong>
                    <form action="/<%= contextPath%>/clientes/mudaLinkConsulta?data_consulta=<c:out value='${consulta.data_consulta}'/>&cpf_profissional=<c:out value='${consulta.cpf_profissional}' />&hora=<c:out value='${consulta.horario}'/>" method="POST" id="register-prof" >
                    <input name="link_meet" class="link" type="url" value="<c:out value='${consulta.link_meet}'/>">
                    </form>
                    <button class ="saveLink" form='register-prof' type="submit"> 
                        Alterar
                    </button>
                        
                </div>
                <div class="cancel">
                    <a class="save" type="cancel" href="/<%= contextPath%>/clientes/cancelaConsulta?data_consulta=<c:out value='${consulta.data_consulta}'/>&cpf_profissional=<c:out value='${consulta.cpf_profissional}' />&hora=<c:out value='${consulta.horario}'/>" 
                    onclick="return confirm('Tem certeza que deseja cancela essa consulta? | Are you sure you want to cancel this appointment?');"
                    ><fmt:message key="cancel_appointment"/></a>
                </div>
            </div>
        </div>
    </fmt:bundle>
</body>