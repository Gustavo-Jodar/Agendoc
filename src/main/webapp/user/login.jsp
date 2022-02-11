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
                    <strong>Entre na plataforma</strong> 
                    <p>Basta informar seu e-mail e senha cadastrados!</p>
                </div>
            </header>
<main>       
	      <!--  <form action="showIndex" method="post" id="register-prof" >
                <fieldset>
                    <div class="input-block">
                        <label for="e-mail">E-mail</label>
                        <input name="email" id="e-mail" required>
                    </div>
                    <div class="input-block">
                        <label for="pass">Senha</label>
                        <input name="senha" id="pass" required>
                    </div>
                </fieldset>
            </form>
            <footer>
        <button type="submit" name="loginData" form="login-prof">Log-in</button>
        --> <form id="register-prof" method="post" action="${pageContext.request.contextPath}/users/login">
            <table>
                <tr>
                    <th>email: </th>
                    <td><input type="text" name="email"
                               value=""/></td>
                </tr>
                <tr>
                    <th>Senha: </th>
                    <td><input type="password" name="senha" /></td>
                </tr>
                <tr>
                    <td colspan="2"> 
                        <input type="submit" name="loginData" value="Entrar"/>
                    </td>
                </tr>
            </table>
        </form>
        
    
    <div align="center">
        <a href="../user/userType.jsp">Ainda não possui um cadastro? Cadastre-se já!</a>
        </footer>
</main>
</div>
    </div>
</body>
</html>