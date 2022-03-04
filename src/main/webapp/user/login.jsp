<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agendoc</title> 

        <link rel="stylesheet" href="../styles/main.css"> 
        <link rel="stylesheet" href="../styles/header.css">
        <link rel="stylesheet" href="../styles/page-profissional-login.css"> 
        <link rel="stylesheet" href="../styles/forms.css">
        <link rel="stylesheet" href="../styles/login.css">
        <link rel="stylesheet" href="../styles/erro.css">

        <script src="../scripts/addField.js" defer></script>
<body  id= "page-prof-register">
    <fmt:bundle basename="messages">
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
                    <strong><fmt:message key="enter_platform"/></strong> 
                    <p><fmt:message key="enter_platform_sub"/></p>
                </div>
            </header>
<main>       
        <form id="register-prof" method="post" action="${pageContext.request.contextPath}/users/login">
            <div class="input-block">
                <label for="email">E-mail: </label>
                <input type="text" name="email" value="" required/>
            </div>
            <div class="input-block">
                <label for="senha"><fmt:message key="password"/></label>
                <input type="password" name="senha" required/>
            </div>
            <input class="button" type="submit" name="loginData" value=<fmt:message key="enter"/>>
        </form>
        <c:if test="${mensagens.existeErros}">
            <div id="erro">
                <div>
                    <c:forEach var="erro" items="${mensagens.erros}">
                        <div class="alerta" > ${erro} </div>
                        </c:forEach>
                </div>
            </div>
        </c:if> 
    <footer>
        <div align="center">
            <a href="../user/userType.jsp"><fmt:message key="no_account"/></a>
        </div>
    </footer>
</main>
</div>
</fmt:bundle>
</body>
</html>