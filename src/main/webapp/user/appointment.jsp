<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="pt_br"> 
<head>
    <meta charset="UTF-8"> 
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Agendoc | Plataforma para agendamentos de consultas online</title> 

    <%
        String contextPath = request.getContextPath().replace("/", "");
    %>

    <link rel="stylesheet" href="/<%= contextPath%>/styles/main.css"> 
    <link rel="stylesheet" href="/<%= contextPath%>/styles/header.css">
    <link rel="stylesheet" href="/<%= contextPath%>/styles/page-prof.css"> 
    <link rel="stylesheet" href="/<%= contextPath%>/styles/forms.css">
    <!-- <link rel="stylesheet" href="/<%= contextPath%>/styles/modal.css">  -->

</head>
<body id= "page-start" >
    <div id= "container">
        <header class="page-header">
            <div class="top-bar-container">
                <a href="/<%= contextPath%>/seeProf.jsp">
                <img src="/<%= contextPath%>/images/back.svg" alt="Voltar">
                </a>
            </div>
            <div class="header-content">
                <strong>Marque sua consulta com o doutor XXX</strong> 
                <!-- aqui teria que de alguma forma ter os dados do profissional com quem estou marcando -->
            </div>
        </header>
        <div class="schedule">
            <header class="title">
                <strong>Agende sua consulta online</strong> 
            </header>
            <fieldset id="schedule-items" class="schedule-box">
                <legend>Horários disponíveis para atendimento</legend>
                <div class="schedule-item">

                    <div class="input-block">
                        <label for="weekday">Data</label>
                        <input type="date" name="nascimento">
                    </div>
                    
                    <div class="input-block">
                        <label for="time_from">Das</label>
                        <input type="time" name="time_from[]">
                    </div>

                    <div class="input-block">
                        <label for="time_to">Até</label>
                        <input type="time" name="time_to[]">
                    </div>

                </div>
            </fieldset>
            <a class="button" href="/<%= contextPath%>/users/verificaUsuarioLogado">Marcar</a>
        </div>
    </div>
</body>