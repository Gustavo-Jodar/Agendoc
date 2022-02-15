<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html> 
<head>
    <meta charset="UTF-8"> 
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="theme-color" content="#8257E5">
    
    <title>Agendoc | Plataforma de consultas online</title> 
    <link rel="stylesheet" href="styles/main.css"> 
    <link rel="stylesheet" href="styles/page-landing.css"> 
</head>
<body id= "page-landing" >
    <%
        String contextPath = request.getContextPath().replace("/", "");
    %>
    <div id= "container">
        <div class="logo-container">
            <h1>Agendoc<h1>
            <h2> Agendoc | Plataforma de consultas online </h2>
        </div>
        <img class= "hero-image" src="images/landing.png" alt="Plataforma de Consultas">

    <div class="buttons-container">
        <a class ="button" href="/<%= contextPath%>/users/showLogin"> 
        Login
        </a>
    </div>

    <p class="total-connections">
        Total de 1.000.000 de cadastros de profissionais já realizados!
    </p>

    </div>
</body>
</html>

