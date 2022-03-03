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
                <strong>Marque sua consulta com ${profissionalEscolhido.nome}</strong>
                <span>Área: ${profissionalEscolhido.area}</span>
                <span>Especialidade: ${profissionalEscolhido.especialidade}</span>
                <p>Biografia: ${profissionalEscolhido.bio}</p>
                <!-- aqui teria que de alguma forma ter os dados do profissional com quem estou marcando -->
            </div>
        </header>
        <div class="schedule">
            <header class="title">
                <strong>Agende sua consulta online</strong> 
            </header>
            <fieldset id="schedule-items" class="schedule-box">
                <legend>Escolha a data para atendimento</legend>
                <c:choose>
                        <c:when test="${consulta_aux.data_consulta!=null}">     
                            <form action="/<%= contextPath%>/clientes/cadastraConsulta?cpf_profissional=<c:out value='${profissionalEscolhido.cpf}'/>" method="POST" id="register-prof">
                            <div>
                                <div>
                                    <label for="birth">Data</label>
                                    <input type="date" name="data_consulta" value="<c:out value='${data_nao_formatada}'/>" readonly>
                                </div>
                            </div>
                            <legend>Escolha um horário</legend>
                            <br/>
                            <p>As consultas duram 50 minutos, com uma janela de 10 minutos entre consulta!</p>      
                            <select class="form-control" name="horario" required>
                                    <option value="" required>Selecione o horário</option>
                                    <c:forEach var="line" items="${horariosLivres}">
                                        <option value="<c:out value='${line}'/>">${line}h00</option>
                                    </c:forEach>
                            </select>
                            <button type="submit" form="register-prof" value="Cadastrar">Agendar consulta!</button>
                            <a class="button" href="/<%= contextPath%>/users/verificaEstaLogado?cpf=<c:out value='${profissionalEscolhido.cpf}'/>">Verificar outros horários disponíveis</a>
                        </c:when>    
                        <c:otherwise>
                            <form action="/<%= contextPath%>/clientes/reapresentaMarcarConsulta?cpf=<c:out value='${profissionalEscolhido.cpf}'/>" method="POST" id="register-prof">
                            <div class="schedule-item">
                                <div class="input-block">
                                    <label for="weekday">Data</label>
                                    <input type="date" name="data_consulta" required>
                                </div>
                            </div>
                            </form>
                            <button type="submit" form="register-prof" value="">Verificar horários disponíveis</button>
                        </c:otherwise>
                    </c:choose>
            </fieldset>
        </div>
    </div>
</body>