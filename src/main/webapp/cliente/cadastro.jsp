<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <meta content="text/html;charset=utf-8" http-equiv="Content-Type">
        <meta content="utf-8" http-equiv="encoding">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agendoc | Plataforma para agendamentos de consultas online</title> 

        <link rel="stylesheet" href="../styles/main.css"> 
        <link rel="stylesheet" href="../styles/header.css">
        <link rel="stylesheet" href="../styles/page-profissional-login.css"> 
        <link rel="stylesheet" href="../styles/forms.css">

        <script src="../scripts/addField.js" defer></script>

    </head>
    <body id= "page-prof-register">

        <div id="container">
            
            <header class="page-header">
                <div class="top-bar-container">
                    <a href="../index.jsp">
                    <img src="../images/back.svg" alt="Voltar">
                    </a>
                </div>

                <div class="header-content">
                    <strong>Quer ter acesso às nossas especialidades online?</strong> 
                    <p>Basta preencher esse formulário de cadastro</p>
                </div>
                <br/><br/><br/>
            </header>

            <%
                String contextPath = request.getContextPath().replace("/", "");
            %>

            <main>
                <form action="cadastrar" method="POST" id="register-prof">
                    <fieldset >
                        <legend>Seus dados</legend>
                        <div class="input-block">
                            <label for="cpf">CPF: </label>
                            <input type="text" name="cpf" id="cpf" required/>
                        </div>

                         <div class="input-block">
                            <label for="name">Nome Completo</label>
                            <input name="name" id="name" required>
                        </div>
                        <div class="input-block">
                            <label for="e-mail">E-mail</label>
                            <input name="e-mail" id="e-mail" required>
                        </div>
                        <div class="input-block">
                            <label for="pass">Senha</label>
                            <input name="pass" id="pass" required>
                        </div>
                        <div class="input-block">
                            <label for="birth">Nascimento</label>
                            <input type="date" name="birth-date">
                        </div>
                        <div class="input-block">
                            <label>Telefone: </label>
                            <input name="telefone" type="text"/><br/>
                        </div>
                        <div class="input-block">
                            <label>Sexo: </label>
                            <input name="Sexo" type="text"/><br/>
                        </div>
                    </fieldset>
                </form>
                <footer>
                    <button type="submit" form="register-prof" value="Cadastrar">Salvar cadastro</button>
                </footer>    
            
            </main>
        </div>
    </body>
</html>