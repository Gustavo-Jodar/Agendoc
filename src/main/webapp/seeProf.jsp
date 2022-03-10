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

</head>
<body id= "page-start" >
    <fmt:bundle basename="messages">
    <div id= "container">
        <header class="page-header">
            <div class="top-bar-container">
                <a href="/<%= contextPath%>/users/retornaIndex">
                <img src="/<%= contextPath%>/images/back.svg" alt="Voltar">
                </a>
                <a class="button" href="/<%= contextPath%>/users/verificaLogin">Login / Profile</a>
            </div>

            <div class="header-content">
                <strong><fmt:message key="profissionais_filtro"/></strong> 
                <form id="search-prof" method="post" action="${pageContext.request.contextPath}/users/showProfissionais" >
                    <div class="select-block">
                        <label for="area">Area</label>
                        <select name="area" id="area">
                            <option value=""><fmt:message key="select_option"/></option>
                            <option value="Medicina"><fmt:message key="Medicina"/></option>
                            <option value="Advocacia"><fmt:message key="Advocacia"/></option>
                            <option value="Psicologia"><fmt:message key="Psicologia"/></option>
                            <option value="Educacao"><fmt:message key="Educacao"/></option>
                            <option value="Nutricao"><fmt:message key="Nutricao"/></option>
                            <option value="Terapia"><fmt:message key="Terapia"/></option>
                        </select>
                    </div>
                    
                    <div class="input-block">
                        <label for="weekday"><fmt:message key="speciality"/></label>
                        <input name="especialidade" id="especialidade"/>
                    </div>
                    <button type="submit"><fmt:message key="filter"/></button>
                </form>
            </div>
        </header>

        <main>
            <c:forEach var="profissional" items="${requestScope.listaProfissionais}">
                <article class="prof-item">
                    <header>
                        <div>
                            <!-- <strong>{{prof.name}}</strong>
                            <span>{{prof.area}}</span> -->
                            <strong>${profissional.nome}</strong>
                            <span>${profissional.area}</span>
                        </div>
                    </header>
                
                    <!-- <p>{{prof.bio}}</p> -->
                    <p>${profissional.bio}</p>
                
                    <footer>
                        <p><fmt:message key="speciality"/><strong>${profissional.especialidade}</strong>
                        </p>
                        <a class="button" href="/<%= contextPath%>/users/verificaEstaLogado?cpf=<c:out value='${profissional.cpf}'/>"><fmt:message key="schedule_appointment"/></a>
                        <!-- encaminha para pagina de login se nao estiver logado -->
                    </footer>
                </article>
            </c:forEach>
            <!-- ja deixei o for comentadinho, para como seria para mostrar diversos profissionais do banco -->

        </main>
    </div>
    </fmt:bundle>
</body>
</html>

