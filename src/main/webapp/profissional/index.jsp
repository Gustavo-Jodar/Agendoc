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
    <link rel="stylesheet" href="/<%= contextPath%>/styles/perfil-prof.css">
    <link rel="stylesheet" href="/<%= contextPath%>/styles/perfil-client.css"> 

    <script src="../scripts/addField.js" defer></script>

</head>
<body id= "page-start" >
   
    <div id= "container">
        <header class="page-header">

            <div class="header-content">
                <strong>Bem vindo (a) ${sessionScope.usuarioLogado.nome}</strong>
                <p>Selecione seus horarios disponiveis e disponibilize consultas!</p>
            </div>
        </header>

        <main>
            <div class="container">
                <div class="appointments">
                    <header class="title">
                        <strong>Consultas em aberto</strong> 
                    </header>
                    <!-- Aqui ficaria o looping pra mostrar todas consultas, da data mais proxima da atual até a mais antiga -->
                        <article class="prof-item">
                            <header>
                                <div>
                                    <strong>Carlos Alberto</strong>
                                    <!-- <span>Consulta particular</span> -->
                                </div>
                            </header>
                        
                            <p>Consulta agendada para: 27/03/2022</p>
                            <p>Horário: 14:00 de Brasilia</p>
                            <p>Modalidade: Presencial</p>
                        
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
                </div>
                <div class="schedule">
                    <header class="title">
                        <strong>Altere seus horários disponiveis</strong> 
                    </header>
                    <fieldset id="schedule-items">
                        <legend>Horários disponíveis para atendimento
                            <button type="button" id="add-time">+ Novo Horario</button>
                        </legend>
                        <div class="schedule-item">
    
                            <div class="select-block">
                                <label for="weekday">Dia da semana</label>
                                <select name="weekday[]">
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
                                <label for="time_from">Das</label>
                                <input type="time" name="time_from[]">
                            </div>
    
                            <div class="input-block">
                                <label for="time_to">Até</label>
                                <input type="time" name="time_to[]">
                            </div>
    
                        </div>
    
                    </fieldset>
                </div>
            </div>
        </main>
    </div>

    <div class="page-footer">
        <div class="buttons-container">
            <a class ="button" href="/<%= contextPath%>/users/showProfissionais"> 
                Marque uma consulta!
            </a>
            <a class ="button" href="${pageContext.request.contextPath}/users/logout">
                Sair
            </a>
        </div>
    </div>

</body>
</html>

