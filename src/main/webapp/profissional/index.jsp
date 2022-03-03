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
    <link rel="stylesheet" href="/<%= contextPath%>/styles/perfil.css"> 

</head>
<body id= "page-start" >
   
    <div id= "container">
        <header class="page-header">

            <div class="header-content">
                <strong>Bem vindo (a) ${sessionScope.usuarioLogado.nome}</strong>
                <p>Agora suas consultas já estão sendo disponibilizadas!</p>
            </div>
        </header>

        <main>
            <div class="buttons-container">
                <a class ="button" href="/<%= contextPath%>/users/showProfissionais"> 
                    Marque uma consulta!
                </a>
                <a class ="button" href="${pageContext.request.contextPath}/users/logout">
                    Sair
                </a>
            </div>
        <header class="title">
            <strong>Consultas em aberto</strong> 
        </header>
        <!-- Aqui ficaria o looping pra mostrar todas consultas, da data mais proxima da atual até a mais antiga -->
            <c:forEach var="consulta" items="${requestScope.consultas}">
            <article class="prof-item">
                <header>
                    <div>
                        <strong>${consulta.nome_cliente}</strong>
                        <!-- <span>Consulta particular</span> -->
                    </div>
                </header>
            
                <p>Consulta agendada para: ${consulta.data_consulta}</p>
                <p>Horário: ${consulta.horario}:00 de Brasilia</p>
                <p>Modalidade: Online</p>
            
                <footer>
                    <p>Consulta<strong>Particular</strong>
                    </p>
                    <a class ="button"> 
                        Alterar consulta
                    </a>
                    <!-- Podemoriamos fazer essa opcao q aparece só se a consulta ja tiver passado: -->
                    <!-- <a class ="button"> 
                        Avaliar consulta
                    </a> -->
                </footer>
            </article>
            </c:forEach>
        </main>
    </div>

</body>
</html>

