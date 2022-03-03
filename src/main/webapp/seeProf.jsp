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

</head>
<body id= "page-start" >
   
    <div id= "container">
        <header class="page-header">
            <div class="top-bar-container">
                <a href="/<%= contextPath%>/users/retornaIndex">
                <img src="/<%= contextPath%>/images/back.svg" alt="Voltar">
                </a>
            </div>

            <div class="header-content">
                <strong>Esses são os profissionais disponíveis</strong> 
                <form id="search-prof" method="post" action="${pageContext.request.contextPath}/users/showProfissionais" >
                    <div class="select-block">
                        <label for="area">Area</label>
                        <select name="area" id="area">
                            <option value="">Selecione uma opção</option>
                            <option value="Medicina">Medicina</option>
                            <option value="Advocacia">Advocacia</option>
                            <option value="Psicologia">Psicologia</option>
                            <option value="Educacao">Educação</option>
                            <option value="Nutricao">Nutrição</option>
                            <option value="Terapia">Terapia</option>
                        </select>
                    </div>
                    
                    <div class="input-block">
                        <label for="weekday">Especialidade</label>
                        <input name="especialidade" id="especialidade"/>
                    </div>
                    <button type="submit">Filtrar</button>
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
                        <p>Especialidade<strong>${profissional.especialidade}</strong>
                        </p>
                        <a class="button" href="/<%= contextPath%>/users/verificaEstaLogado">Marcar Consulta</a>
                        <!-- encaminha para pagina de login se nao estiver logado -->
                    </footer>
                </article>
            </c:forEach>
            <!-- ja deixei o for comentadinho, para como seria para mostrar diversos profissionais do banco -->

        </main>
    </div>
</body>
</html>

