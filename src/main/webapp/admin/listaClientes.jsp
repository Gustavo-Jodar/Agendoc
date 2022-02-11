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
		<h1>Gerenciamento de Clientes</h1>
		<h2>
			<a href="/<%=contextPath%>/admins/showPaginaAdmin">Voltar</a> &nbsp;&nbsp;&nbsp; 
		</h2>
	</div>
	<div align="center">
		<table border="1">
			<caption>Lista de Clientes</caption>
			<tr>
                <th>CPF</th>
				<th>Nome</th>
				<th>Email</th>
				<th>Telefone</th>
				<th>Sexo</th>
				<th>Nascimento</th>
				<th>Editar</th>
				<th>Apagar</th>
			</tr>
			<c:forEach var="cliente" items="${requestScope.listaClientes}">
				<tr>
					<td>${cliente.cpf}</td>
					<td>${cliente.nome}</td>
					<td>${cliente.email}</td>
					<td>${cliente.telefone}</td>
					<td>${cliente.sexo}</td>
					<td>${cliente.nascimento}</td>
					<td align="center" ><a href="/<%=contextPath%>/admins/apresentaEdicaoCliente?email=<c:out value='${cliente.email}' />">🖍️</a></td>
					<td align="center" >
					<a href="/<%=contextPath%>/admins/removerCliente?cpf=<c:out value='${cliente.cpf}' />"
						onclick="return confirm('Tem certeza que deseja excluir este usuário?');"
					>🗑️</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>