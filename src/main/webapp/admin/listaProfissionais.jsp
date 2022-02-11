<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Agendoc</title>
</head>
<body>
	<%
		String contextPath = request.getContextPath().replace("/", "");
	%>
	<div align="center">
		<h1>Gerenciamento de Profissionais</h1>
		<h2>
			<a href="/<%=contextPath%>//admins/showPaginaAdmin">Voltar para Página do Administrador</a> &nbsp;&nbsp;&nbsp; 
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
				<th>Area</th>
				<th>Especialidade</th>
				<th>Biografia</th>
				<th>Nascimento</th>
			</tr>
			<c:forEach var="profissional" items="${requestScope.listaProfissionais}">
				<tr>
					<td>${profissional.cpf}</td>
					<td>${profissional.nome}</td>
					<td>${profissional.email}</td>
					<td>${profissional.senha}</td>
					<td>${profissional.area}</td>
					<td>${profissional.especialidade}</td>
					<td>${profissional.bio}</td>
					<td>${profissional.nascimento}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>