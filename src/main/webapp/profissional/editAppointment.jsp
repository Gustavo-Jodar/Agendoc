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
                    <a href="/<%= contextPath%>/profissionais/showPaginaProfissional">
                    <img src="/<%= contextPath%>/images/back.svg" alt="Voltar">
                    </a>
                </div>
                <div class="header-content">
                    <strong> Veja sobre sua consulta</strong>
                    <span> Sempre evite desmarcar consultas de ultima hora. </span>
                    <span> Nao se atrase. </span>
                    <span> Nao se esqueca de disponibilizar o link da consulta. </span>
                </div>
            </header>
            <div class="schedule">
                <div class="link-time">
                    <strong>Cliente:</strong> 
                    <legend> Sophia Schuster</legend>
                </div>
                <div class="link-time">
                    <strong>Data e horário da consulta:</strong> 
                    <legend> 03/02/2022 às 17:00 horas</legend>
                </div>
                <div class="link-time">
                    <strong>Cole aqui o link do meet para consulta:</strong>
                    <input name="link" class="link" type="url">
                    <button type="save" class="saveLink" value=""> Salvar </button>
                </div>
                <div class="cancel">
                    <button type="cancel" class="save" value=""> Cancelar consulta </button>
                </div>
            </div>
        </div>
    </fmt:bundle>
</body>