-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 20-11-2024 a las 17:04:06
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

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `excursion`
--

CREATE TABLE `excursion` (
  `codigo` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `descripcion` text,
  `num_dias` int NOT NULL,
  `precio_inscripcion` decimal(38,2) NOT NULL,
  `fecha` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `excursion`
--

INSERT INTO `excursion` (`codigo`, `descripcion`, `num_dias`, `precio_inscripcion`, `fecha`) VALUES
('ABC', 'Una excursion de prueba', 3, 40.00, '2025-01-01'),
('GESP', 'Excursion del gobierno de España', 2, 5.00, '2026-02-06'),
('SYM1', 'Excursion de S.Y.M', 1, 5.00, '2022-04-03'),
('SYM2', 'Excursion larga de S.Y.M', 20, 100.00, '2025-03-03'),
('UOC1', 'Excursion de estudiantes 1', 5, 10.00, '2024-02-05'),
('UOC2', 'Excursion de estudiantes 2', 3, 20.00, '2025-10-10');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `federacion`
--

CREATE TABLE `federacion` (
  `codigo` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `nombre` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `federacion`
--

INSERT INTO `federacion` (`codigo`, `nombre`) VALUES
('EUOC', 'Excursiones UOC'),
('FAS', 'Federacion de asociados'),
('FED', 'FedSYM'),
('FederacionPrueba', 'Federacion');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inscripcion`
--

CREATE TABLE `inscripcion` (
  `numero_inscripcion` int NOT NULL,
  `id_socio` int DEFAULT NULL,
  `codigo_excursion` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `inscripcion`
--

INSERT INTO `inscripcion` (`numero_inscripcion`, `id_socio`, `codigo_excursion`) VALUES
(1, 1, 'UOC1'),
(2, 1, 'UOC2'),
(4, 2, 'SYM1'),
(5, 2, 'UOC1'),
(6, 3, 'GESP'),
(7, 1, 'SYM2'),
(8, 6, 'SYM1'),
(9, 6, 'SYM2'),
(10, 7, 'GESP'),
(11, 9, 'ABC');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `seguro`
--

CREATE TABLE `seguro` (
  `nombre` enum('BASICO','COMPLETO') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `precio` double NOT NULL
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

CREATE TABLE `socio` (
  `id_socio` int NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `tiposocio` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `socio`
--

INSERT INTO `socio` (`id_socio`, `nombre`, `tiposocio`) VALUES
(1, 'Jimena Blanco', 'SocioEstandar'),
(2, 'Liliana Salinas', 'SocioEstandar'),
(3, 'Abraham Barroso', 'SocioEstandar'),
(4, 'Maria Francisca Llorente', 'SocioFederado'),
(5, 'Meritxell Carrera', 'SocioFederado'),
(6, 'Sergio Aroca', 'SocioFederado'),
(7, 'Micaela Balaguer', 'SocioInfantil'),
(8, 'Jesus Angel Calero', 'SocioInfantil'),
(9, 'Kevin', 'SocioEstandar'),
(11, 'Tomas', 'SocioInfantil'),
(12, 'Carl', 'SocioEstandar'),
(13, 'Juan', 'SocioEstandar'),
(14, 'Maria', 'SocioEstandar'),
(15, 'Jose', 'SocioInfantil'),
(17, 'Juan', 'SocioEstandar'),
(21, 'Juan', 'SocioEstandar'),
(33, 'Juan', 'SocioEstandar'),
(44, 'Juan', 'SocioEstandar'),
(55, 'Juan', 'SocioEstandar'),
(66, 'Juan', 'SocioEstandar'),
(77, 'Juan', 'SocioEstandar'),
(88, 'Juan', 'SocioEstandar'),
(99, 'Juan', 'SocioEstandar'),
(110, 'Juan', 'SocioEstandar'),
(121, 'Juan', 'SocioEstandar'),
(132, 'Juan', 'SocioEstandar'),
(143, 'Juan', 'SocioEstandar'),
(157, 'Margarita', 'SocioEstandar'),
(159, 'Margarita', 'SocioEstandar'),
(160, 'Margarita', 'SocioEstandar'),
(161, 'Margarita', 'SocioEstandar'),
(162, 'Margarita', 'SocioEstandar'),
(168, 'Carlos', 'SocioEstandar'),
(169, 'Rosa', 'SocioFederado'),
(172, 'Carlos', 'SocioEstandar'),
(173, 'Rosa', 'SocioFederado'),
(206, 'Carlos', 'SocioEstandar');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `socioadulto`
--

CREATE TABLE `socioadulto` (
  `id_socio` int NOT NULL,
  `nif` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `socioadulto`
--

INSERT INTO `socioadulto` (`id_socio`, `nif`) VALUES
(1, '85183461V'),
(2, '17013755B'),
(3, '75370076L'),
(4, '80398297A'),
(5, '08387969F'),
(6, '37705934X'),
(9, 'abcdefgh'),
(12, 'qweqweqwe'),
(13, '11223366H'),
(14, 'aaabbbccc'),
(17, '11223366H'),
(21, '11223366H'),
(33, '11223366H'),
(44, '11223366H'),
(55, '11223366H'),
(66, '11223366H'),
(77, '11223366H'),
(88, '11223366H'),
(99, '11223366H'),
(110, '11223366H'),
(121, '11223366H'),
(206, 'AABBCCDDE');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `socioestandar`
--

CREATE TABLE `socioestandar` (
  `id_socio` int NOT NULL,
  `seguro_nombre` enum('BASICO','COMPLETO') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `socioestandar`
--

INSERT INTO `socioestandar` (`id_socio`, `seguro_nombre`) VALUES
(1, 'BASICO'),
(2, 'BASICO'),
(3, 'BASICO'),
(9, 'BASICO'),
(12, 'BASICO'),
(13, 'BASICO'),
(17, 'BASICO'),
(21, 'BASICO'),
(33, 'BASICO'),
(44, 'BASICO'),
(55, 'BASICO'),
(66, 'BASICO'),
(77, 'BASICO'),
(88, 'BASICO'),
(99, 'BASICO'),
(110, 'BASICO'),
(121, 'BASICO'),
(206, 'BASICO'),
(14, 'COMPLETO');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sociofederado`
--

CREATE TABLE `sociofederado` (
  `id_socio` int NOT NULL,
  `federacion_codigo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `sociofederado`
--

INSERT INTO `sociofederado` (`id_socio`, `federacion_codigo`) VALUES
(4, 'FED'),
(5, 'FAS'),
(6, 'EUOC');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `socioinfantil`
--

CREATE TABLE `socioinfantil` (
  `id_socio` int NOT NULL,
  `tutor` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `socioinfantil`
--

INSERT INTO `socioinfantil` (`id_socio`, `tutor`) VALUES
(7, 1),
(8, 5),
(11, 6),
(15, 13);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tiposocio`
--

CREATE TABLE `tiposocio` (
  `nombre` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `descuento_mensual` double NOT NULL,
  `descuento_excursion` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `tiposocio`
--

INSERT INTO `tiposocio` (`nombre`, `descuento_mensual`, `descuento_excursion`) VALUES
('SocioEstandar', 0, 0),
('SocioFederado', 0.05, 0.1),
('SocioInfantil', 0.5, 0);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `excursion`
--
ALTER TABLE `excursion`
  ADD PRIMARY KEY (`codigo`);

--
-- Indices de la tabla `federacion`
--
ALTER TABLE `federacion`
  ADD PRIMARY KEY (`codigo`);

--
-- Indices de la tabla `inscripcion`
--
ALTER TABLE `inscripcion`
  ADD PRIMARY KEY (`numero_inscripcion`),
  ADD KEY `id_socio` (`id_socio`),
  ADD KEY `codigo_excursion` (`codigo_excursion`);

--
-- Indices de la tabla `seguro`
--
ALTER TABLE `seguro`
  ADD PRIMARY KEY (`nombre`);

--
-- Indices de la tabla `socio`
--
ALTER TABLE `socio`
  ADD PRIMARY KEY (`id_socio`),
  ADD KEY `tiposocio_fk` (`tiposocio`);

--
-- Indices de la tabla `socioadulto`
--
ALTER TABLE `socioadulto`
  ADD PRIMARY KEY (`id_socio`);

--
-- Indices de la tabla `socioestandar`
--
ALTER TABLE `socioestandar`
  ADD PRIMARY KEY (`id_socio`),
  ADD KEY `seguro_fk` (`seguro_nombre`);

--
-- Indices de la tabla `sociofederado`
--
ALTER TABLE `sociofederado`
  ADD KEY `id_socio_ref` (`id_socio`),
  ADD KEY `federacion_ref` (`federacion_codigo`);

--
-- Indices de la tabla `socioinfantil`
--
ALTER TABLE `socioinfantil`
  ADD PRIMARY KEY (`id_socio`),
  ADD KEY `tutor` (`tutor`);

--
-- Indices de la tabla `tiposocio`
--
ALTER TABLE `tiposocio`
  ADD PRIMARY KEY (`nombre`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `inscripcion`
--
ALTER TABLE `inscripcion`
  MODIFY `numero_inscripcion` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=112;

--
-- AUTO_INCREMENT de la tabla `socio`
--
ALTER TABLE `socio`
  MODIFY `id_socio` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=285;

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
  ADD CONSTRAINT `sociofk` FOREIGN KEY (`id_socio`) REFERENCES `socio` (`id_socio`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `socioestandar`
--
ALTER TABLE `socioestandar`
  ADD CONSTRAINT `seguro_fk` FOREIGN KEY (`seguro_nombre`) REFERENCES `seguro` (`nombre`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `socioadultofk` FOREIGN KEY (`id_socio`) REFERENCES `socioadulto` (`id_socio`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `sociofederado`
--
ALTER TABLE `sociofederado`
  ADD CONSTRAINT `federacion_ref` FOREIGN KEY (`federacion_codigo`) REFERENCES `federacion` (`codigo`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `id_socio_ref` FOREIGN KEY (`id_socio`) REFERENCES `socioadulto` (`id_socio`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `socioinfantil`
--
ALTER TABLE `socioinfantil`
  ADD CONSTRAINT `socioinfantil_ibfk_1` FOREIGN KEY (`id_socio`) REFERENCES `socio` (`id_socio`) ON DELETE CASCADE ON UPDATE RESTRICT,
  ADD CONSTRAINT `socioinfantil_ibfk_2` FOREIGN KEY (`tutor`) REFERENCES `socioadulto` (`id_socio`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
