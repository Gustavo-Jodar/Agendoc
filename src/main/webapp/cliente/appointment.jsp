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
                <a href="/<%= contextPath%>/seeProf.jsp">
                <img src="/<%= contextPath%>/images/back.svg" alt="Voltar">
                </a>
            </div>
            <div class="header-content">
                <strong><fmt:message key="make_appointment_with"/> ${profissionalEscolhido.nome}</strong>
                <span>Area: ${profissionalEscolhido.area}</span>
                <span><fmt:message key="speciality"/>: ${profissionalEscolhido.especialidade}</span>
                <p><fmt:message key="bio"/>: ${profissionalEscolhido.bio}</p>
                <!-- aqui teria que de alguma forma ter os dados do profissional com quem estou marcando -->
            </div>
        </header>
        <div class="schedule">
            <header class="title">
                <strong><fmt:message key="make_appointment"/></strong> 
            </header>
            <fieldset id="schedule-items" class="schedule-box">
                <legend><fmt:message key="choose_date"/></legend>
                <c:choose>
                        <c:when test="${consulta_aux.data_consulta!=null}">     
                            <form action="/<%= contextPath%>/clientes/cadastraConsulta?cpf_profissional=<c:out value='${profissionalEscolhido.cpf}'/>" method="POST" id="register-prof">
                            <div>
                                <div>
                                    <label for="birth"><fmt:message key="date"/></label>
                                    <input type="date" name="data_consulta" value="<c:out value='${data_nao_formatada}'/>" readonly>
                                </div>
                            </div>
                            <legend><fmt:message key="choose_time"/></legend>
                            <br/>
                            <p><fmt:message key="info_time"/></p>      
                            <select class="form-control" name="horario" required>
                                    <option value="" required><fmt:message key="select_option"/></option>
                                    <c:forEach var="line" items="${horariosLivres}">
                                        <option value="<c:out value='${line}'/>">${line}h00</option>
                                    </c:forEach>
                            </select>
                            <legend><fmt:message key="add_link"/></legend>
                            <input name="link_meet" class="link" type="url" value="" required> 
                            </form>
                            <button type="submit" class="save" form="register-prof" value="Cadastrar"><fmt:message key="schedule_appointment"/></button>
                            <a class="button" href="/<%= contextPath%>/users/verificaEstaLogado?cpf=<c:out value='${profissionalEscolhido.cpf}'/>"><fmt:message key="check_other_times"/></a>
                            <div id="erro">
                                <div>
                                    <c:forEach var="erro" items="${mensagens.erros}">
                                    <div class="alerta" > ${erro} </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </c:when>    
                        <c:otherwise>
                            <form action="/<%= contextPath%>/clientes/reapresentaMarcarConsulta?cpf=<c:out value='${profissionalEscolhido.cpf}'/>" method="POST" id="register-prof">
                            <div class="schedule-item">
                                <div class="input-block">
                                
                                    <label for="weekday"><fmt:message key="date"/></label>
                                    <input type="date" name="data_consulta" class="data-input" required>
                                </div>
                            </div>
                            </form>    
                            <button type="submit" form="register-prof" class="save" value=""><fmt:message key="choose_time"/></button>
                            <div id="erro">
                                <div>
                                    <c:forEach var="erro" items="${mensagens.erros}">
                                        <div class="alerta" > ${erro} </div>
                                        </c:forEach>
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>
            </fieldset>
        </div>
    </div>
    </fmt:bundle>
</body>