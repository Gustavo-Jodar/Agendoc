<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agendoc | Plataforma para agendamentos de consultas online</title> 

        <link rel="stylesheet" href="../styles/main.css"> 
        <link rel="stylesheet" href="../styles/header.css">
        <link rel="stylesheet" href="../styles/page-profissional-login.css"> 
        <link rel="stylesheet" href="../styles/forms.css">

        <script src="../scripts/addField.js" defer></script>
<body  id= "page-prof-register">
	<%
		String contextPath = request.getContextPath().replace("/", "");
	%>      
    <div id= "container">
            <header class="page-header">
                <div class="top-bar-container">
                    <a href="../index.jsp">
                    <img src="../images/back.svg" alt="Voltar">
                    </a>
                </div>

                <div class="header-content">
                    <strong>Entre em nossa plataforma</strong> 
                    <p>Basta informar seu e-mail e senha cadastrados!</p>
                </div>
            </header>
<main>
	        <form id="register-prof" action="" method="POST">
                <fieldset>
                    <div class="input-block">
                        <label for="e-mail">E-mail</label>
                        <input name="e-mail" id="e-mail" required>
                    </div>
                    <div class="input-block">
                        <label for="pass">Senha</label>
                        <input name="pass" id="pass" required>
                    </div>
                </fieldset>
            </form>
            <footer>
        <button type="submit" form="login-prof">Log-in</button>

    <div align="center">
        <a href="/<%= contextPath%>/clientes/showCadastroCliente">Ainda não possui um cadastro? Cadastre-se já!</a>
        </footer>
</main>
</div>
    </div>
</body>
</html>