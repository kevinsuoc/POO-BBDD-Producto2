
-- Creación de la base de datos
CREATE DATABASE IF NOT EXISTS SenderosYMontanas;
USE SenderosYMontanas;

-- Tabla TipoSeguro (para el tipo de seguro)
CREATE TABLE TipoSeguro (
    id_tipo_seguro INT AUTO_INCREMENT PRIMARY KEY,
    nombre ENUM('BASICO', 'COMPLETO') NOT NULL
);

-- Tabla TipoSocio (para el tipo de socio)
CREATE TABLE TipoSocio (
    id_tipo_socio INT AUTO_INCREMENT PRIMARY KEY,
    nombre ENUM('ESTANDAR', 'FEDERADO', 'INFANTIL') NOT NULL
);

-- Tabla Seguro
CREATE TABLE Seguro (
    id_seguro INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    tipo_seguro INT,
    FOREIGN KEY (tipo_seguro) REFERENCES TipoSeguro(id_tipo_seguro)
);

-- Tabla Socio
CREATE TABLE Socio (
    id_socio INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    telefono VARCHAR(15),
    tipo_socio INT,
    FOREIGN KEY (tipo_socio) REFERENCES TipoSocio(id_tipo_socio)
);

-- Tabla SocioAdulto (extiende Socio)
CREATE TABLE SocioAdulto (
    numero_socio INT PRIMARY KEY,
    nif VARCHAR(20),
    FOREIGN KEY (numero_socio) REFERENCES Socio(id_socio)
);

-- Tabla SocioEstandar (extiende SocioAdulto y se relaciona con Seguro)
CREATE TABLE SocioEstandar (
    numero_socio INT PRIMARY KEY,
    id_seguro INT,
    FOREIGN KEY (numero_socio) REFERENCES SocioAdulto(numero_socio),
    FOREIGN KEY (id_seguro) REFERENCES Seguro(id_seguro)
);

-- Tabla SocioInfantil (extiende Socio y se relaciona con SocioAdulto como tutor)
CREATE TABLE SocioInfantil (
    numero_socio INT PRIMARY KEY,
    tutor INT,
    FOREIGN KEY (numero_socio) REFERENCES Socio(id_socio),
    FOREIGN KEY (tutor) REFERENCES SocioAdulto(numero_socio)
);

-- Tabla Excursion
CREATE TABLE Excursion (
    codigo VARCHAR(10) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    num_dias INT NOT NULL,
    precio_inscripcion DECIMAL(10, 2) NOT NULL,
    fecha DATE NOT NULL
);

-- Tabla Federacion
CREATE TABLE Federacion (
    codigo VARCHAR(10) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);

-- Tabla Inscripcion
CREATE TABLE Inscripcion (
    numero_inscripcion INT AUTO_INCREMENT PRIMARY KEY,
    id_socio INT,
    codigo_excursion VARCHAR(10),
    FOREIGN KEY (id_socio) REFERENCES Socio(id_socio),
    FOREIGN KEY (codigo_excursion) REFERENCES Excursion(codigo)
);


