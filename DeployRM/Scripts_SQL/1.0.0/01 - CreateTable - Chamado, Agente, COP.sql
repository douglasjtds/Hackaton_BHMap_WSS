CREATE TABLE Chamado
(
	ID_Chamado INT identity(1,1) NOT NULL,
	Titulo VARCHAR(50) NOT NULL,
	Descricao VARCHAR(50),
	Latitude FLOAT NOT NULL,
	Longitude FLOAT NOT NULL,
	DataHoraOcorrido DateTime NOT NULL,
	PRIMARY KEY (ID_Chamado)
);

CREATE TABLE Agente
(
	ID_Agente INT identity(1,1),
	Nome VARCHAR(50) NOT NULL, 
	Latitude FLOAT NOT NULL,
	Longitude FLOAT NOT NULL,
	ID_Chamado int not null,
	PRIMARY KEY (ID_Agente),
	FOREIGN KEY (ID_Chamado) REFERENCES Chamado(ID_Chamado)

);

CREATE TABLE COP 
(
	ID_Cop INT identity(1,1) NOT NULL,
	Nome_Dep VARCHAR(MAX),
	PRIMARY KEY (ID_Cop),
	ID_Chamado int not null,
	ID_Agente int not null,
	FOREIGN KEY (ID_Chamado) REFERENCES Chamado(ID_Chamado),
	FOREIGN KEY (ID_Agente) REFERENCES Agente(ID_Agente)
);
