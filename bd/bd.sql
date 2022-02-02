--CRIE O DATABASE 
CREATE DATABASE Agendoc;

--cria tabela clientes

CREATE TABLE Cliente(cpf CHAR(16) NOT NULL UNIQUE, 
					 email VARCHAR(50) NOT NULL UNIQUE,
					 senha VARCHAR(50) NOT NULL,
					 nome VARCHAR(100) NOT NULL,
					 telefone VARCHAR(15)NOT NULL UNIQUE,
					 sexo VARCHAR(15) NOT NULL,
					 nascimento DATE NOT NULL,
					CONSTRAINT PK_CLIENTE PRIMARY KEY(cpf)
					)

--inserindo dados
INSERT INTO Cliente(cpf, nome, email, senha, telefone, sexo, nascimento) VALUES ('511.996.138-00',
																				  'Gustavo Vieira Jodar',
																				  'gustavo.jodar@gmail.com',
																				  'password',
																				  '16988304537',
																				  'Masculino',
																				  '29-01-2001'
																				 );
                                                                                 