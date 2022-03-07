<h1>Sistema de Agendamento de Profissionais Liberais!</h1>

Grupo WEB 1:
Sophia S. Schuster 760936
Gustavo Vieira Jodar 769678

* Para iniciá-lo, rode o script postgresql que encontra-se em bd/bd.sql em um banco de dados postgresql.
* O Banco de Dados deve ser usuário postgres com senha: password

Então é só rodar:

$ mvn tomcat7:deploy

Para realizar cadastros é preciso criar um banco de dados, utilizamos PostgresSQL, é possivel ver os scripts necessarios para criacao do db em .../Agendoc/bd/bd.sql

Para que seja possivel se cadastrar como um profissional tambem é preciso ir no UserController.java localizado em: .../Agendoc/src/main/java/br/ufscar/dc/dsw/controller/UserController.java e modificar na linha 351 o inicio do caminho onde o PDF é salvo para um que exista em seu computador. 
