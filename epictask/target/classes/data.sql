CREATE TABLE task (
	id bigint PRIMARY KEY auto_increment,
	title varchar(200),
	description TEXT,
	points int
);


INSERT INTO task (title, description, points) VALUES 
	('Criar banco de dados', 'Criar um banco de dados na nuvem com Oracle', 200);
	
INSERT INTO task (title, description, points) VALUES 
	('Modelagem de telas', 'Criação de protótipo de alta fidelidade', 100);
	
INSERT INTO task (title, description, points) VALUES 
	('API REST', 'Publicação de API com endpoints da aplicação', 150);

	
CREATE TABLE signup (
	id bigint PRIMARY KEY auto_increment,
	username varchar(200),
	email varchar(200),
	password varchar(200)
);

INSERT INTO signup (username, email, password) VALUES 
	('André Lorenção', 'andre.lorencao@gmail.com', 'andre84758');
	
INSERT INTO signup (username, email, password) VALUES 
	('Luca labio', 'luca.labio@gmail.com', 'lucas84841');
	
INSERT INTO signup (username, email, password) VALUES 
	('Stephane Cristini', 'stephane.cristini@gmail.com', 'steph84573');