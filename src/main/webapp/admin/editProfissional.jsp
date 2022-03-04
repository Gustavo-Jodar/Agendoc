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
            <br/>
            <div align="center" >
                    <a href="/<%=contextPath%>/admins/listaProfissionais"><fmt:message key="back"/></a>    
            </div>
            
            <main align="center">
                <form action="editaProfissional" method="POST" id="register-prof">
                        <legend><fmt:message key="professional_data"/></legend>
                        <br/>
                        <div>
                            <label for="cpf"><fmt:message key="id_cpf"/></label>
                            <input type="text" name="cpf" value="<c:out value='${profissionalEdit.cpf}'/>" />
                        </div>
                        <div>
                            <label for="name"><fmt:message key="name"/></label>
                            <input name="nome" id="name" value="<c:out value='${profissionalEdit.nome}'/>" required>
                        </div>
                        <div>
                            <label for="e-mail">E-mail</label>
                            <input name="email" id="e-mail" value="<c:out value='${profissionalEdit.email}'/>" required>
                        </div>
                        <div>
                            <label for="pass"><fmt:message key="password"/></label>
                            <input name="senha" id="pass" value="<c:out value='${profissionalEdit.senha}'/>" required>
                        </div>
                        <div >
                            <label for="birth"><fmt:message key="birth"/></label>
                                <input type="date" name="nascimento" value="<c:out value='${profissionalEdit.nascimento}'/>" >
                        </div>
                        <div class="textarea-block">
                                <label for="bio"><fmt:message key="bio"/></label>
                                <textarea name="bio" id="bio"><c:out value='${profissionalEdit.bio}'/></textarea>
                        </div>
                        <div>
                            <label for="area"><fmt:message key="knowledge_area"/></label>
                            <input name="area" id="area" value="<c:out value='${profissionalEdit.area}'/>" required>
                        </div>
                        <div class="input-block">
                                <label for="especialidade"><fmt:message key="speciality"/></label>
                                <input type="especialidade" id="especialidade" name="especialidade" value="<c:out value='${profissionalEdit.especialidade}'/>" required>
                        </div>
                </form>
                    <button type="submit" form="register-prof" value="Salvar"><fmt:message key="sabe_area"/></button>
            </main>
        </div>
        </fmt:bundle>
    </body>
</html>