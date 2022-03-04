<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
<title>Agendoc</title>
</head>
<body>
    <fmt:bundle basename="messages">
	<%
		String contextPath = request.getContextPath().replace("/", "");
	%>
	<div align="center">
		<h1><fmt:message key="client_crud"/></h1>
		<h2>
			<a href="/<%=contextPath%>/admins/showPaginaAdmin">Voltar</a> &nbsp;&nbsp;&nbsp; 
		</h2>
	</div>
	<div align="center">
		<table border="1">
			<caption><fmt:message key="client_list"/></caption>
			<tr>
                <th><fmt:message key="id_cpf"/></th>
				<th><fmt:message key="name"/></th>
				<th>Email</th>
				<th><fmt:message key="tel"/></th>
				<th><fmt:message key="sex"/></th>
				<th><fmt:message key="birth"/></th>
				<th><fmt:message key="edit"/></th>
				<th><fmt:message key="delete"/></th>
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
		<br/>
		<a href="/<%=contextPath%>/admins/apresentaAdicionarCliente"><fmt:message key="add_client"/></a>
	</div>
    </fmt:bundle>
</body>
</html>