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
		<h1><fmt:message key="professional_crud"/></h1>
		<h2>
			<a href="/<%=contextPath%>//admins/showPaginaAdmin"><fmt:message key="back"/></a> &nbsp;&nbsp;&nbsp; 
		</h2>
	</div>
	<div align="center">
		<table border="1">
			<caption><fmt:message key="professional_list"/></caption>
			<tr>
                <th><fmt:message key="id_cpf"/></th>
				<th><fmt:message key="name"/></th>
				<th>Email</th>
				<th>Area</th>
				<th><fmt:message key="speciality"/></th>
				<th><fmt:message key="bio"/></th>
				<th><fmt:message key="birth"/></th>
				<th><fmt:message key="edit"/></th>
				<th><fmt:message key="delete"/></th>
			</tr>
			<c:forEach var="profissional" items="${requestScope.listaProfissionais}">
				<tr>
					<td>${profissional.cpf}</td>
					<td>${profissional.nome}</td>
					<td>${profissional.email}</td>
					<td>${profissional.area}</td>
					<td>${profissional.especialidade}</td>
					<td>${profissional.bio}</td>
					<td>${profissional.nascimento}</td>
					<td align="center" ><a href="/<%=contextPath%>/admins/apresentaEdicaoProfissional?email=<c:out value='${profissional.email}'/>">🖍️</a></td>
					<td align="center" >
					<a href="/<%=contextPath%>/admins/removerProfissional?cpf=${profissional.cpf}"
						onclick="return confirm('Tem certeza que deseja excluir este usuário? | Are you sure you want to delete this user?');"
					>🗑️</a></td>
				</tr>
			</c:forEach>
		</table>
		<br/>
		<a href="/<%=contextPath%>/admins/apresentaAdicionarProfissional" ><fmt:message key="add_professional"/></a>
	</div>
	    </fmt:bundle>
</body>
</html>