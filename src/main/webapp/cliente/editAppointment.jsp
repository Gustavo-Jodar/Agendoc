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
                    <strong> Algum problema com a sua consulta?</strong>
                    <span> Sempre evite desmarcar consultas de ultima hora. </span>
                    <span> Nao se atrase. </span>
                </div>
            </header>
            <div class="schedule">
                <div class="link-time">
                    <strong>Profissional:</strong> 
                    <legend> Sophia Schuster Médica Pedriatra</legend>
                </div>
                <div class="link-time">
                    <strong>Data e horário da consulta:</strong> 
                    <legend> 03/02/2022 às 17:00 horas</legend>
                </div>
                <div class="link-time">
                    <strong>Entre nesse link no horario da consulta:</strong>
                    <a href="googlemeet.com"> Link do meet </a> 
                </div>
                <div class="cancel">
                    <button type="cancel" class="save" value=""> Cancelar consulta </button>
                    <button type="cancel" class="save" value=""> Alterar consulta </button>
                </div>
            </div>
        </div>
    </fmt:bundle>
</body>