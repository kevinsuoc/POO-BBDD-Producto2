-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 07-11-2024 a las 19:53:07
-- Versión del servidor: 8.0.33
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `senderosymontanas`
--
CREATE DATABASE IF NOT EXISTS `senderosymontanas` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `senderosymontanas`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `excursion`
--

DROP TABLE IF EXISTS `excursion`;
CREATE TABLE IF NOT EXISTS `excursion` (
  `codigo` varchar(10) NOT NULL,
  `descripcion` text,
  `num_dias` int NOT NULL,
  `precio_inscripcion` decimal(10,2) NOT NULL,
  `fecha` date NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `federacion`
--

DROP TABLE IF EXISTS `federacion`;
CREATE TABLE IF NOT EXISTS `federacion` (
  `codigo` varchar(10) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inscripcion`
--

DROP TABLE IF EXISTS `inscripcion`;
CREATE TABLE IF NOT EXISTS `inscripcion` (
  `numero_inscripcion` int NOT NULL AUTO_INCREMENT,
  `id_socio` int DEFAULT NULL,
  `codigo_excursion` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`numero_inscripcion`),
  KEY `id_socio` (`id_socio`),
  KEY `codigo_excursion` (`codigo_excursion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `seguro`
--

DROP TABLE IF EXISTS `seguro`;
CREATE TABLE IF NOT EXISTS `seguro` (
  `nombre` enum('BASICO','COMPLETO') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `precio` double NOT NULL,
  PRIMARY KEY (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `seguro`
--

INSERT INTO `seguro` (`nombre`, `precio`) VALUES
('BASICO', 10),
('COMPLETO', 15);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `socio`
--

DROP TABLE IF EXISTS `socio`;
CREATE TABLE IF NOT EXISTS `socio` (
  `id_socio` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `apellido` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `tiposocio` enum('ESTANDAR','FEDERADO','INFANTIL') NOT NULL,
  PRIMARY KEY (`id_socio`),
  UNIQUE KEY `email` (`email`),
  KEY `tiposocio_fk` (`tiposocio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `socioadulto`
--

DROP TABLE IF EXISTS `socioadulto`;
CREATE TABLE IF NOT EXISTS `socioadulto` (
  `numero_socio` int NOT NULL,
  `nif` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`numero_socio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `socioestandar`
--

DROP TABLE IF EXISTS `socioestandar`;
CREATE TABLE IF NOT EXISTS `socioestandar` (
  `numero_socio` int NOT NULL,
  `seguro` enum('SEGURO','BASICO') NOT NULL,
  PRIMARY KEY (`numero_socio`),
  KEY `seguro_fk` (`seguro`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sociofederado`
--

DROP TABLE IF EXISTS `sociofederado`;
CREATE TABLE IF NOT EXISTS `sociofederado` (
  `id_socio` int NOT NULL,
  `codigo_federacion` varchar(10) NOT NULL,
  KEY `id_socio_ref` (`id_socio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `socioinfantil`
--

DROP TABLE IF EXISTS `socioinfantil`;
CREATE TABLE IF NOT EXISTS `socioinfantil` (
  `numero_socio` int NOT NULL,
  `tutor` int DEFAULT NULL,
  PRIMARY KEY (`numero_socio`),
  KEY `tutor` (`tutor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tiposocio`
--

DROP TABLE IF EXISTS `tiposocio`;
CREATE TABLE IF NOT EXISTS `tiposocio` (
  `nombre` enum('ESTANDAR','FEDERADO','INFANTIL') NOT NULL,
  `descuento_mensual` double NOT NULL,
  `descuento_excursion` double NOT NULL,
  PRIMARY KEY (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `tiposocio`
--

INSERT INTO `tiposocio` (`nombre`, `descuento_mensual`, `descuento_excursion`) VALUES
('ESTANDAR', 0, 0),
('FEDERADO', 0.05, 0.1),
('INFANTIL', 0.5, 0);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `inscripcion`
--
ALTER TABLE `inscripcion`
  ADD CONSTRAINT `inscripcion_ibfk_1` FOREIGN KEY (`id_socio`) REFERENCES `socio` (`id_socio`),
  ADD CONSTRAINT `inscripcion_ibfk_2` FOREIGN KEY (`codigo_excursion`) REFERENCES `excursion` (`codigo`);

--
-- Filtros para la tabla `socio`
--
ALTER TABLE `socio`
  ADD CONSTRAINT `tiposocio_fk` FOREIGN KEY (`tiposocio`) REFERENCES `tiposocio` (`nombre`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Filtros para la tabla `socioadulto`
--
ALTER TABLE `socioadulto`
  ADD CONSTRAINT `socioadulto_ibfk_1` FOREIGN KEY (`numero_socio`) REFERENCES `socio` (`id_socio`);

--
-- Filtros para la tabla `socioestandar`
--
ALTER TABLE `socioestandar`
  ADD CONSTRAINT `seguro_fk` FOREIGN KEY (`seguro`) REFERENCES `seguro` (`nombre`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `socioestandar_ibfk_1` FOREIGN KEY (`numero_socio`) REFERENCES `socioadulto` (`numero_socio`);

--
-- Filtros para la tabla `sociofederado`
--
ALTER TABLE `sociofederado`
  ADD CONSTRAINT `id_socio_ref` FOREIGN KEY (`id_socio`) REFERENCES `socioadulto` (`numero_socio`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Filtros para la tabla `socioinfantil`
--
ALTER TABLE `socioinfantil`
  ADD CONSTRAINT `socioinfantil_ibfk_1` FOREIGN KEY (`numero_socio`) REFERENCES `socio` (`id_socio`),
  ADD CONSTRAINT `socioinfantil_ibfk_2` FOREIGN KEY (`tutor`) REFERENCES `socioadulto` (`numero_socio`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
