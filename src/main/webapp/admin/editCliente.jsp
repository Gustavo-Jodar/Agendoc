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
    </head>
    <body id= "page-prof-register">
                <fmt:bundle basename="messages">

        <div align="center" id="container">
            <%
                String contextPath = request.getContextPath().replace("/", "");
            %>
            <div align="center" >
                    <a href="/<%=contextPath%>/admins/listaClientes"><fmt:message key="back"/></a>    
            </div>
            
            <main align="center">
                <form action="editaCliente" method="POST" id="register-prof">
                        <legend><fmt:message key="client_data"/></legend>
                        <br/>
                        <div>
                            <label for="cpf"><fmt:message key="id_cpf"/></label>
                            <input type="text" name="cpf" value="<c:out value='${clienteEdit.cpf}'/>" />
                        </div>
                        <div>
                            <label for="name"><fmt:message key="name"/></label>
                            <input name="nome" id="name" value="<c:out value='${clienteEdit.nome}'/>" required>
                        </div>
                        <div>
                            <label for="e-mail">E-mail</label>
                            <input name="email" id="e-mail" value="<c:out value='${clienteEdit.email}'/>" required>
                        </div>
                        <div>
                            <label for="pass"><fmt:message key="password"/></label>
                            <input name="senha" id="pass" value="<c:out value='${clienteEdit.senha}'/>" required>
                        </div>
                        <div >
                            <label for="birth"><fmt:message key="birth"/></label>
                                <input type="date" name="nascimento" value="<c:out value='${clienteEdit.nascimento}'/>" >
                        </div>
                        <div >
                            <label><fmt:message key="tel"/></label>
                            <input name="telefone" type="text" value="<c:out value='${clienteEdit.telefone}'/>" /><br/>
                        </div>
                        <div>
                            <label><fmt:message key="sex"/></label>
                            <input name="sexo" type="text" value="<c:out value='${clienteEdit.sexo}'/>" /><br/>
                        </div>
                </form>
                <div id="erro">
                <div>
                    <c:forEach var="erro" items="${mensagens.erros}">
                        <div class="alerta" > ${erro} </div>
                        </c:forEach>
                </div>
            </div>
                    <button type="submit" form="register-prof" value="Salvar"><fmt:message key="save_register"/></button>
                
            </main>
        </div>
    </fmt:bundle>
    </body>
</html>