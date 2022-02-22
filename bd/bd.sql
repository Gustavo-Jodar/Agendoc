--CRIE O DATABASE 
CREATE DATABASE Agendoc;

--cria tabela usuários
CREATE TABLE Users(cpf CHAR(16) NOT NULL UNIQUE,
				  email VARCHAR(60) NOT NULL UNIQUE,
				  senha VARCHAR(60) NOT NULL,
				  papel CHAR(15) CHECK (papel='ADMIN' or papel='CLIENTE' or papel='PROFISSIONAL'),
				  nome VARCHAR(100) NOT NULL,
				  nascimento DATE NOT NULL);
					  
				
--cria tabela clientes
CREATE TABLE Clientes(cpf CHAR(16) NOT NULL UNIQUE, 
					  telefone VARCHAR(15)NOT NULL UNIQUE,
					  sexo VARCHAR(15) NOT NULL,
					  CONSTRAINT FK_CPF FOREIGN KEY(cpf) REFERENCES Users(cpf) ON DELETE CASCADE,
					  CONSTRAINT PK_CLIENTE PRIMARY KEY(cpf)
					  );

--cria tabela profissionais

CREATE TABLE Profissionais(cpf CHAR(16) NOT NULL UNIQUE, 
						   area VARCHAR(50) NOT NULL,
					 	   especialidade VARCHAR(100)NOT NULL,
					 	   bio VARCHAR(500) NOT NULL,
					 	   CONSTRAINT FK_CPF FOREIGN KEY(cpf) REFERENCES Users(cpf) ON DELETE CASCADE,
					       CONSTRAINT PK_PROFISSIONAIS PRIMARY KEY(cpf)
						   );																	
                         

--inserindo dados
--INSERE admin
INSERT INTO Users(cpf, nome, email, senha, nascimento, papel) VALUES ('000.000.000-00',
										  'Administrador',
										  'admin@admin.com',
										  'admin',
										  '11-11-1111',
										  'ADMIN'
										 );
--insere cliente
INSERT INTO Users(cpf, nome, email, senha, nascimento, papel) VALUES ('511.996.138-00',
										  'Gustavo Vieira Jodar',
										  'gustavo.jodar@gmail.com',
										  'password',
										  '29-01-2001',
										  'CLIENTE'
										 );
INSERT INTO Clientes(cpf, telefone, sexo) VALUES ('511.996.138-00', '16988304537', 'Masculino');

--insere profissional
INSERT INTO Users(cpf, nome, email, senha, nascimento, papel) VALUES ('511.996.138-01',
										  'Sophia Schuster',
										  'sophia.schuster@gmail.com',
										  'password',
										  '29-01-2001',
										  'PROFISSIONAL'
										 );
INSERT INTO Profissionais(cpf, area, especialidade, bio) VALUES ('511.996.138-01','MEDICINA', 'CARDIOLOGIA', 'Sou um bom doutor.');
