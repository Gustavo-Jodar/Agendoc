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

    <link rel="stylesheet" href="/<%= contextPath%>/styles/perfil.css"> 
    <link rel="stylesheet" href="/<%= contextPath%>/styles/main.css"> 
    <link rel="stylesheet" href="/<%= contextPath%>/styles/header.css">
    <link rel="stylesheet" href="/<%= contextPath%>/styles/page-prof.css"> 
    <!-- <link rel="stylesheet" href="/<%= contextPath%>/styles/forms.css"> -->

</head>
<body id= "page-start" >
   
    <div id= "container">
        <header class="page-header">

            <div class="header-content">
                <strong>Bem vindo (a) ${sessionScope.usuarioLogado.nome}</strong>
                <p>Você já pode marcar suas consultas agora!</p>
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
                <article class="prof-item">
                    <header>
                        <div>
                            <strong>Carlos Alberto</strong>
                            <span>Medicina</span>
                        </div>
                    </header>
                
                    <p>Consulta agendada para: 27/03/2022</p>
                    <p>Horário: 14:00 de Brasilia</p>
                    <p>Modalidade: Presencial</p>
                
                    <footer>
                        <p>Especialidade<strong>Cardiologista</strong>
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
        </main>
    </div>

</body>
</html>

