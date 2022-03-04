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
                <form action="/<%=contextPath%>/users/saveProfissional" method="POST" id="register-prof">
                        <legend><fmt:message key="professional_data"/></legend>
                        <br/>
                        <div>
                            <label for="cpf"><fmt:message key="id_cpf"/> </label>
                            <input type="text" name="cpf" required/>
                        </div>
                        <div>
                            <label for="name"><fmt:message key="name"/></label>
                            <input name="nome" id="name" required>
                        </div>
                        <div>
                            <label for="e-mail">E-mail</label>
                            <input name="email" id="e-mail" required>
                        </div>
                        <div>
                            <label for="pass"><fmt:message key="password"/></label>
                            <input name="senha" id="pass" required>
                        </div>
                        <div >
                            <label for="birth"><fmt:message key="birth"/></label>
                                <input type="date" name="nascimento" required>
                        </div>
                        <div class="textarea-block">
                                <label for="bio"><fmt:message key="bio"/></label>
                                <textarea name="bio" id="bio"></textarea>
                        </div>
                        <div>
                            <label for="area"><fmt:message key="professional_data"/><fmt:message key="area_of_knowlegde"/></label>
                            <select name="area" id="area required" required>
                                    <option value=""><fmt:message key="select_option"/></option>
                                    <option value="1"><fmt:message key="Medicina"/></option>
                                    <option value="2"><fmt:message key="Advocacia"/></option>
                                    <option value="3"><fmt:message key="Psicologia"/></option>
                                    <option value="4"><fmt:message key="Educacao"/></option>
                                    <option value="5"><fmt:message key="Nutricao"/></option>
                                    <option value="4"><fmt:message key="Terapia"/></option>
                            </select>
                        </div>
                        <div class="input-block">
                                <label for="especialidade"><fmt:message key="speciality"/></label>
                                <input type="especialidade" id="especialidade" name="especialidade" required>
                        </div>
                </form>
                    <button type="submit" form="register-prof" value="Salvar"><fmt:message key="save_register"/></button>
            </main>
        </div>
                </fmt:bundle>
    </body>
</html>