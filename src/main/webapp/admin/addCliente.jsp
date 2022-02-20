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
                    <a href="/<%=contextPath%>/admins/listaClientes">Voltar</a>    
            </div>
            
            <main align="center">
                <form action="/<%=contextPath%>/users/saveCliente" method="POST" id="register-prof">
                        <legend>Dados do Cliente</legend>
                        <br/>
                        <div>
                            <label for="cpf">CPF: </label>
                            <input type="text" name="cpf" required/>
                        </div>
                        <div>
                            <label for="name">Nome Completo</label>
                            <input name="name" id="name" required>
                        </div>
                        <div>
                            <label for="e-mail">E-mail</label>
                            <input name="email" id="e-mail" required>
                        </div>
                        <div>
                            <label for="pass">Senha</label>
                            <input name="pass" id="pass" required>
                        </div>
                        <div >
                            <label for="birth">Nascimento</label>
                                <input type="date" name="birth-date" required >
                        </div>
                        <div >
                            <label>Telefone: </label>
                            <input name="telefone" type="text" required/><br/>
                        </div>
                        <div>
                            <label>Sexo: </label>
                            <input name="sexo" type="text" required /><br/>
                        </div>
                </form>
                    <button type="submit" form="register-prof" value="Salvar">Salvar</button>
            </main>
        </div>
    </body>
</html>