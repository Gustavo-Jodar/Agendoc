<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <meta content="text/html;charset=utf-8" http-equiv="Content-Type">
        <meta content="utf-8" http-equiv="encoding">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agendoc | Plataforma para agendamentos de consultas online</title> 
    </head>
    <body id= "page-prof-register">

        <div align="center" id="container">
            <%
                String contextPath = request.getContextPath().replace("/", "");
            %>
            <br/>
            <div align="center" >
                    <a href="/<%=contextPath%>/admins/listaProfissionais">Voltar</a>    
            </div>
            
            <main align="center">
                <form action="editaProfissional" method="POST" id="register-prof">
                        <legend>Dados do Profissional</legend>
                        <br/>
                        <div>
                            <label for="cpf">CPF: </label>
                            <input type="text" name="cpf" value="<c:out value='${profissionalEdit.cpf}'/>" />
                        </div>
                        <div>
                            <label for="name">Nome Completo</label>
                            <input name="nome" id="name" value="<c:out value='${profissionalEdit.nome}'/>" required>
                        </div>
                        <div>
                            <label for="e-mail">E-mail</label>
                            <input name="email" id="e-mail" value="<c:out value='${profissionalEdit.email}'/>" required>
                        </div>
                        <div>
                            <label for="pass">Senha</label>
                            <input name="senha" id="pass" value="<c:out value='${profissionalEdit.senha}'/>" required>
                        </div>
                        <div >
                            <label for="birth">Nascimento</label>
                                <input type="date" name="nascimento" value="<c:out value='${profissionalEdit.nascimento}'/>" >
                        </div>
                        <div class="textarea-block">
                                <label for="bio">Biografia</label>
                                <textarea name="bio" id="bio"><c:out value='${profissionalEdit.bio}'/></textarea>
                        </div>
                        <div>
                            <label for="area">Area de conhecimento</label>
                            <input name="area" id="area" value="<c:out value='${profissionalEdit.area}'/>" required>
                        </div>
                        <div class="input-block">
                                <label for="especialidade">Especialidade</label>
                                <input type="especialidade" id="especialidade" name="especialidade" value="<c:out value='${profissionalEdit.especialidade}'/>" required>
                        </div>
                </form>
                    <button type="submit" form="register-prof" value="Salvar">Salvar edição</button>
            </main>
        </div>
    </body>
</html>