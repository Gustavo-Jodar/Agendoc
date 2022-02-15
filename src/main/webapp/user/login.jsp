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
        <link rel="stylesheet" href="../styles/login.css">

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
                    <strong>Entre na plataforma</strong> 
                    <p>Assim voce podera disponibilizar ou agendar suas consultas!</p>
                </div>
            </header>
<main>       
        <form id="register-prof" method="post" action="${pageContext.request.contextPath}/users/login">
            <div class="input-block">
                <label for="email">E-mail: </label>
                <input type="text" name="email" value="" required/>
            </div>
            <div class="input-block">
                <label for="senha">Senha: </label>
                <input type="password" name="senha" required/>
            </div>
            <input class="button" type="submit" name="loginData" value="Entrar"/>
        </form>
        
    <footer>
        <div align="center">
            <a href="../user/userType.jsp">Ainda não possui um cadastro? Cadastre-se já!</a>
        </div>
    </footer>
</main>
</div>
</body>
</html>