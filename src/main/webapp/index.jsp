<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
    
    <title>Agendoc | Plataforma de consultas online</title> 
    <link rel="stylesheet" href="/<%= contextPath%>/styles/main.css"> 
    <link rel="stylesheet" href="/<%= contextPath%>/styles/page-landing.css"> 
</head>
<body id= "page-landing" >
    <div id= "container">
        <div class="logo-container">
            <h1>Agendoc<h1>
            <h2> Agendoc | Plataforma de consultas online </h2>
        </div>
        <img class= "hero-image" src="/<%= contextPath%>/images/landing.png" alt="Plataforma de Consultas">

    <div class="buttons-container">
        <a class ="button" href="/<%= contextPath%>/users/showProfissionais"> 
            Comece agora!
        </a>
    </div>

    <p class="total-connections">
        Total de ${requestScope.numProfissionais} cadastros de profissionais já realizados!
    </p>

    </div>
</body>
</html>

