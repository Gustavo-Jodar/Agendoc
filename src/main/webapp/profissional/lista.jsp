<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Livraria Virtual</title>
</head>
<body>
	<%
		String contextPath = request.getContextPath().replace("/", "");
	%>
	<div align="center">
		<h1>Gerenciamento de Profissionais</h1>
		<h2>
			<a href="/<%=contextPath%>">Menu Principal</a> &nbsp;&nbsp;&nbsp; 
		</h2>
	</div>
	<div align="center">
		<table border="1">
			<caption>Lista de Profissionais</caption>
			<tr>
                <th>CPF</th>
				<th>Nome</th>
				<th>Email</th>
				<th>Senha</th>
				<th>Biografia</th>
				<th>Especialidade</th>
				<th>Nascimento</th>
			</tr>
			<c:forEach var="profissional" items="${requestScope.listaProfissionais}">
				<tr>
					<td>${profissional.cpf}</td>
					<td>${profissional.nome}</td>
					<td>${profissional.email}</td>
					<td>${profissional.senha}</td>
					<td>${profissional.bio}</td>
					<td>${profissional.especialidade}</td>
					<td>${profissional.nascimento}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>