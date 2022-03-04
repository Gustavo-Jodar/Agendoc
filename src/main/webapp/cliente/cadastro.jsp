<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
    <head>
        <meta content="text/html;charset=utf-8" http-equiv="Content-Type">
        <meta content="utf-8" http-equiv="encoding">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agendoc</title> 

        <link rel="stylesheet" href="../styles/main.css"> 
        <link rel="stylesheet" href="../styles/header.css">
        <link rel="stylesheet" href="../styles/page-profissional-login.css"> 
        <link rel="stylesheet" href="../styles/forms.css">

        <script src="../scripts/addField.js" defer></script>

    </head>
    <body id= "page-prof-register">
        <fmt:bundle basename="messages">
        <div id="container">
            
            <header class="page-header">
                <div class="top-bar-container">
                    <a href="../user/userType.jsp">
                    <img src="../images/back.svg" alt="Voltar">
                    </a>
                </div>

                <div class="header-content">
                    <strong><fmt:message key="acess_to_specialities"/></strong> 
                    <p><fmt:message key="acess_to_specialities_sub"/></p>
                </div>
                <br/><br/><br/>
            </header>

            <%
                String contextPath = request.getContextPath().replace("/", "");
            %>

            <main>
                <form action="saveCliente" method="POST" id="register-prof">
                    <fieldset >
                        <legend><fmt:message key="your_data"/></legend>
                        <div class="input-block">
                            <label for="cpf"><fmt:message key="id_cpf"/></label>
                            <input type="text" name="cpf" id="cpf" required/>
                        </div>

                         <div class="input-block">
                            <label for="name"><fmt:message key="name"/></label>
                            <input name="name" id="name" required>
                        </div>
                        <div class="input-block">
                            <label for="e-mail">E-mail</label>
                            <input name="email" id="e-mail" required>
                        </div>
                        <div class="input-block">
                            <label for="pass"><fmt:message key="password"/></label>
                            <input name="pass" id="pass" required>
                        </div>
                        <div class="input-block">
                            <label for="birth"><fmt:message key="birth"/></label>
                            <input type="date" name="birth-date">
                        </div>
                        <div class="input-block">
                            <label><fmt:message key="tel"/></label>
                            <input name="telefone" type="text"/><br/>
                        </div>
                        <div class="input-block">
                            <label><fmt:message key="sex"/></label>
                            <input name="sexo" type="text"/><br/>
                        </div>
                    </fieldset>
                </form>
                <footer>
                    <button type="submit" form="register-prof" value="Cadastrar"><fmt:message key="save_register"/></button>
                </footer>    
            
            </main>
        </div>
        </fmt:bundle>
    </body>
</html>