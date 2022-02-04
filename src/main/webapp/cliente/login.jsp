<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta content="text/html;charset=utf-8" http-equiv="Content-Type">
<meta content="utf-8" http-equiv="encoding">
<title>Agendoc</title>
</head>
<body>
	<%
		String contextPath = request.getContextPath().replace("/", "");
	%>
	<div align="center">
		<h1>Login</h1>
		<h2>
			<a href="/<%=contextPath%>">Menu Principal</a> &nbsp;&nbsp;&nbsp; <a
		</h2>
	</div>

	<div align="center">
		<fmt:bundle basename="messages">
            <form style="font-family:Comic Sans Ms; text-align: center;" action="index.jsp" method="POST">
                <fieldset >
                    <legend>Email: </legend>
                    <input type="text" name="E-mail" /><br/>
                </fieldset>
                <fieldset >
                    <legend>Senha: </legend>
                    <input type="password" name="Senha" /><br/>
                    <input type="submit" value="login"/>
                </fieldset>
            </form>
        </fmt:bundle>
	</div>
    <div align="center">
        <a href="/<%= contextPath%>/clientes/cadastro">Ainda não possui um cadastro? Cadastre-se já!</a>
        
    </div>
</body>
</html>