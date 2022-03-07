<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html> 
<head>
    <meta charset="UTF-8"> 
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="theme-color" content="#8257E5">
    <%
        String contextPath = request.getContextPath().replace("/", "");
    %>
    
    <title>Agendoc</title> 
    <link rel="stylesheet" href="/<%= contextPath%>/styles/main.css"> 
    <link rel="stylesheet" href="/<%= contextPath%>/styles/page-landing.css"> 
</head>
<body id= "page-landing" >
    <fmt:bundle basename="messages">
        <div id= "container">
            <div class="logo-container">
                <h1>Agendoc<h1>
                <h2><fmt:message key="logo"/></h2>
            </div>
            <img class= "hero-image" src="/<%= contextPath%>/images/landing.png" alt="Plataforma de Consultas">

        <div class="buttons-container">
            <a class ="button" href="/<%= contextPath%>/users/verificaUsuarioLogado"> 
                <fmt:message key="start"/>
            </a>
        </div>

        </div>
    </fmt:bundle>
</body>
</html>

