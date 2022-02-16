<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="pt_br"> 
<head>
    <meta charset="UTF-8"> 
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Agendoc | Plataforma para agendamentos de consultas online</title> 

    <link rel="stylesheet" href="styles/main.css"> 
    <link rel="stylesheet" href="styles/header.css">
    <link rel="stylesheet" href="styles/page-prof.css"> 
    <link rel="stylesheet" href="styles/forms.css">

</head>
<body id= "page-start" >
    <%
        String contextPath = request.getContextPath().replace("/", "");
    %>
    <div id= "container">
        <header class="page-header">
            <div class="top-bar-container">
                <a href="index.jsp">
                <img src="images/back.svg" alt="Voltar">
                </a>
            </div>

            <div class="header-content">
                <strong>Esses são os profissionais disponíveis</strong> 
                <form id="search-prof">
                    <div class="select-block">
                        <label for="area">Area</label>
                        <select name="area" id="area">
                            <option value="">Selecione uma opção</option>
                            <option value="1">Medicina</option>
                            <option value="2">Advocacia</option>
                            <option value="3">Psicologia</option>
                            <option value="4">Educaçao</option>
                            <option value="5">Nutriçao</option>
                            <option value="4">Terapia</option>
                        </select>
                    </div>
                    
                    <div class="select-block">
                        <label for="weekday">Dia da semana</label>
                        <select name="weekday[]" id="weekday">
                            <option value="">Selecione uma opção</option>
                            <option value="0">Domingo</option>
                            <option value="1">Segunda-feira</option>
                            <option value="2">Terça-feira</option>
                            <option value="3">Quarta-feira</option>
                            <option value="4">Quinta-feira</option>
                            <option value="5">Sexta-feira</option>
                            <option value="6">Sábado</option>
                        </select>
                    </div>
                    <div class="input-block">
                        <label for="time">Hora</label>
                        <input name="time" id="time" type="time" value="{{filters.time}}"> 
                    </div>
                    <button type="submit">Filtrar</button>
                </form>
            </div>
        </header>

        <main>
            <!-- {%for prof in profs%}  -->
                <article class="prof-item">
                    <header>
                        <div>
                            <!-- <strong>{{prof.name}}</strong>
                            <span>{{prof.area}}</span> -->
                            <strong>Nome aqui</strong>
                            <span>Area aqui</span>
                        </div>
                    </header>
                
                    <!-- <p>{{prof.bio}}</p> -->
                    <p>Outras informacoes aqui</p>
                
                    <footer>
                        <!-- <p>Especialidade<strong>{{prof.espec}}</strong>
                        </p> -->
                        <p>Especialidade<strong>Especialidade aqui</strong>
                        </p>
                        <a class ="button" href="/<%= contextPath%>/users/showLogin"> 
                            Marcar hora
                        </a>
                        <!-- encaminha para pagina de login se nao estiver logado -->
                    </footer>
                </article>
            <!-- {%endfor%} -->
            <!-- ja deixei o for comentadinho, para como seria para mostrar diversos profissionais do banco -->

        </main>
    </div>
</body>
</html>

