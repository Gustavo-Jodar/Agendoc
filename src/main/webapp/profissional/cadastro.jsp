<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html> 
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agendoc | Plataforma para agendamentos de consultas online</title> 

        <link rel="stylesheet" href="../styles/main.css"> 
        <link rel="stylesheet" href="../styles/header.css">
        <link rel="stylesheet" href="../styles/page-profissional-login.css"> 
        <link rel="stylesheet" href="../styles/forms.css">

        <script src="../scripts/addField.js" defer></script>

    </head>
    <body id= "page-prof-register" >
        <div id= "container">
            <header class="page-header">
                <div class="top-bar-container">
                    <a href="../user/userType.jsp">
                    <img src="../images/back.svg" alt="Voltar">
                    </a>
                </div>

                <div class="header-content">
                    <strong>Você quer disponibilizar suas consultas online?</strong> 
                    <p>O primeiro passo, é preencher esse formulário de inscrição</p>
                </div>
            </header>

            <main>
                <fmt:bundle basename="messages">
                    <form action="saveProfissional" method="POST" id="register-prof">
                        <fieldset>
                            <legend>Seus dados</legend>

                            <div class="input-block">
                                <label for="nome">Nome Completo</label>
                                <input name="nome" id="nome" required>
                            </div>
                            <div class="input-block">
                                <label for="email">E-mail</label>
                                <input name="email" id="email" required>
                            </div>
                            <div class="input-block">
                                <label for="senha">Senha</label>
                                <input name="senha" id="senha" required>
                            </div>
                            <div class="input-block">
                                <label for="avatar">Link da sua foto <small>(comece com https://)</small></label>
                                <input name="avatar" id="avatar" type="url">
                            </div>
                            <div class="input-block">
                                <label for="cpf">CPF <small>(somente número)</small></label>
                                <input name="cpf" id="cpf" type="number" required>
                            </div>
                            <div class="input-block">
                                <label for="nascimento">Nascimento</label>
                                <input type="date" name="nascimento">
                            </div>

                            <div class="textarea-block">
                                <label for="bio">Biografia</label>
                                <textarea name="bio" id="bio"></textarea>
                            </div>
                        </fieldset>

                        <fieldset>
                            <legend>Área de conhecimento</legend>
                            <div class="select-block">
                                <label for="area">Área</label>
                                <select name="area" id="area required">
                                    <option value="">Selecione uma opção</option>
                                    <option value="1">Medicina</option>
                                    <option value="2">Advocacia</option>
                                    <option value="3">Psicologia</option>
                                    <option value="4">Educaçao</option>
                                    <option value="5">Nutriçao</option>
                                    <option value="4">Terapia</option>
                                </select>
                            </div>

                            <div class="input-block">
                                <label for="especialidade">Especialidade</label>
                                <input type="especialidade" id="especialidade" name="especialidade" required>
                            </div>
                        </fieldset>

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
                                    <label for="time_to">até</label>
                                    <input type="time" name="time_to[]">
                                </div>

                            </div>

                        </fieldset>
                    </form>
                </fmt:bundle>
                <footer>
                    <button type="submit" form="register-prof">Salvar cadastro</button>
                </footer>
            </main>
        </div>

    </body>
</html>

