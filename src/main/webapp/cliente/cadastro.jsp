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
		<h1>Cadastro de Novo Cliente </h1>
		<h2>
			<a href="/<%=contextPath%>">Menu Principal</a> &nbsp;&nbsp;&nbsp;
		</h2>
	</div>

	<div align="center">
		<fmt:bundle basename="messages">
            <form style="font-family:Comic Sans Ms; text-align: center;" action="cadastrar" method="POST">
                <fieldset >
                    <legend>CPF: </legend>
                    <input type="text" name="cpf" /><br/>
                </fieldset>
				<fieldset >
                    <legend>Nome: </legend>
                    <input type="text" name="nome" /><br/>
                </fieldset>
				<fieldset >
                    <legend>Email: </legend>
                    <input type="text" name="email" /><br/>
                </fieldset>
                <fieldset >
                    <legend>Senha: </legend>
                    <input type="password" name="senha" /><br/>
                </fieldset>
				<fieldset >
                    <legend>Telefone: </legend>
                    <input type="text" name="telefone" /><br/>
                </fieldset>
                <fieldset >
                    <legend>Sexo: </legend>
                    <input type="text" name="sexo" /><br/>
                </fieldset>
				<fieldset >
                    <legend>Data de nascimento: </legend>
                    <input type="text" name="nascimento" /><br/>
                </fieldset>
				<input type="submit" value="Cadastrar"/>

			</form>
        </fmt:bundle>
	</div>
</body>
</html>