-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 10-11-2024 a las 17:33:23
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

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `getSocioEstandarById` (IN `id` INT)   BEGIN
				SELECT socio.id_socio as id, socio.nombre, tiposocio, nif, seguro, precio FROM socio
                JOIN socioadulto ON id_socio = socioadulto.numero_socio
                JOIN socioestandar ON id_socio = socioestandar.numero_socio
                JOIN seguro ON socioestandar.seguro = seguro.nombre
                WHERE socio.id_socio = id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getSocioFederadoById` (IN `id` INT)   BEGIN
				SELECT socio.id_socio as id, nif, socio.nombre, tiposocio, codigo_federacion, federacion.nombre AS nombre_federacion FROM socio
                JOIN socioadulto ON socio.id_socio = socioadulto.numero_socio
                JOIN sociofederado ON socio.id_socio = sociofederado.id_socio
                JOIN federacion ON sociofederado.codigo_federacion = federacion.codigo
                WHERE socio.id_socio = id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getSocioInfantilById` (`id` INT)   BEGIN
	SELECT nombre, tutor FROM socio 
    JOIN socioinfantil ON numero_socio = id_socio
    WHERE id_socio = id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getSociosEstandar` ()   BEGIN
SELECT socio.id_socio as id, socio.nombre, tiposocio, nif, seguro, precio FROM socio
                JOIN socioadulto ON id_socio = socioadulto.numero_socio
                JOIN socioestandar ON id_socio = socioestandar.numero_socio
                JOIN seguro ON socioestandar.seguro = seguro.nombre;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getSociosFederados` ()   BEGIN
				SELECT socio.id_socio as id, nif, socio.nombre, tiposocio, codigo_federacion, federacion.nombre AS nombre_federacion FROM socio
                JOIN socioadulto ON socio.id_socio = socioadulto.numero_socio
                JOIN sociofederado ON socio.id_socio = sociofederado.id_socio
                JOIN federacion ON sociofederado.codigo_federacion = federacion.codigo;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getSociosInfantiles` ()   BEGIN
    SELECT id_socio as id, nombre, tutor FROM socio 
    JOIN socioinfantil ON numero_socio = id_socio;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insertSocioEstandar` (IN `nombre` VARCHAR(100), IN `nif` VARCHAR(14), IN `seguro` ENUM('BASICO','COMPLETO'), OUT `id` INT)   BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION 
    BEGIN
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error agregando socio estandar';
    END;
    
    START TRANSACTION;
	INSERT INTO socio (nombre, tiposocio) VALUES (nombre, "ESTANDAR");
    SET id = LAST_INSERT_ID();
    INSERT into socioadulto (numero_socio, nif) VALUES (id, nif);
	INSERT INTO socioestandar (numero_socio, seguro) VALUES (id, seguro);
    COMMIT;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insertSocioFederado` (IN `nombre` VARCHAR(100), IN `nif` VARCHAR(14), IN `codigo_federacion` VARCHAR(100), OUT `id` INT)   BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION 
    BEGIN
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error agregando socio federado';
    END;
    
    START TRANSACTION;
	INSERT INTO socio (nombre, tiposocio) VALUES (nombre, "FEDERADO");
    SET id = LAST_INSERT_ID();
    INSERT INTO socioadulto (numero_socio, nif) VALUES (id, nif);
	INSERT INTO sociofederado (id_socio, codigo_federacion) VALUES (id, codigo_federacion);
    COMMIT;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insertSocioInfantil` (IN `nombre` VARCHAR(100), IN `tutor` INT, OUT `id` INT)   BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION 
    BEGIN
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error insertando socio infantil';
    END;
    
    START TRANSACTION;
	INSERT INTO socio (nombre, tiposocio) VALUES (nombre, "INFANTIL");
    SET id = LAST_INSERT_ID();
	INSERT INTO socioinfantil (numero_socio, tutor) VALUES (id, tutor);
    COMMIT;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateSocioEstandar` (IN `id` INT, IN `nif` VARCHAR(14), IN `nombre` VARCHAR(100), IN `seguro` ENUM('BASICO','COMPLETO'))   BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION 
    BEGIN
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error actualizando socio estandar';
    END;
    
    START TRANSACTION;
	UPDATE socio SET nombre = nombre WHERE id_socio = id;
    UPDATE socioadulto SET nif = nif  WHERE numero_socio = id;
	UPDATE socioestandar SET seguro = seguro WHERE numero_socio = id;
    COMMIT;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateSocioFederado` (IN `id` INT, IN `nombre` VARCHAR(100), IN `nif` VARCHAR(14), IN `codigo_federacion` VARCHAR(100))   BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION 
    BEGIN
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error actualizando socio federado';
    END;
    
    START TRANSACTION;
    UPDATE socio SET nombre = nombre WHERE id_socio = id;
    UPDATE socioadulto SET nif = nif WHERE numero_socio = id;
    UPDATE sociofederado SET codigo_federacion = codigo_federacion WHERE id_socio = id;
    COMMIT;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateSocioInfantil` (IN `id` INT, IN `nombre` VARCHAR(100), IN `tutor` INT)   BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION 
    BEGIN
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error actualizando socio infantil';
    END;
    
    START TRANSACTION;
    UPDATE socio SET nombre = nombre WHERE id_socio = id;
    UPDATE socioinfantil SET tutor = tutor WHERE numero_socio = id;
    COMMIT;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `excursion`
--

CREATE TABLE `excursion` (
  `codigo` varchar(10) NOT NULL,
  `descripcion` text,
  `num_dias` int NOT NULL,
  `precio_inscripcion` decimal(10,2) NOT NULL,
  `fecha` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `excursion`
--

INSERT INTO `excursion` (`codigo`, `descripcion`, `num_dias`, `precio_inscripcion`, `fecha`) VALUES
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
  `nombre` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `federacion`
--

INSERT INTO `federacion` (`codigo`, `nombre`) VALUES
('EUOC', 'Excursiones UOC'),
('FAS', 'Federacion de asociados'),
('FED', 'FedSYM');

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
(10, 7, 'GESP');

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
  `tiposocio` enum('ESTANDAR','FEDERADO','INFANTIL') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `socio`
--

INSERT INTO `socio` (`id_socio`, `nombre`, `tiposocio`) VALUES
(1, 'Jimena Blanco', 'ESTANDAR'),
(2, 'Liliana Salinas', 'ESTANDAR'),
(3, 'Abraham Barroso', 'ESTANDAR'),
(4, 'Maria Francisca Llorente', 'FEDERADO'),
(5, 'Meritxell Carrera', 'FEDERADO'),
(6, 'Sergio Aroca', 'FEDERADO'),
(7, 'Micaela Balaguer', 'INFANTIL'),
(8, 'Jesus Angel Calero', 'INFANTIL');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `socioadulto`
--

CREATE TABLE `socioadulto` (
  `numero_socio` int NOT NULL,
  `nif` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `socioadulto`
--

INSERT INTO `socioadulto` (`numero_socio`, `nif`) VALUES
(1, '85183461V'),
(2, '17013755B'),
(3, '75370076L'),
(4, '80398297A'),
(5, '08387969F'),
(6, '37705934X');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `socioestandar`
--

CREATE TABLE `socioestandar` (
  `numero_socio` int NOT NULL,
  `seguro` enum('BASICO','COMPLETO') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `socioestandar`
--

INSERT INTO `socioestandar` (`numero_socio`, `seguro`) VALUES
(1, 'BASICO'),
(3, 'BASICO'),
(2, 'COMPLETO');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sociofederado`
--

CREATE TABLE `sociofederado` (
  `id_socio` int NOT NULL,
  `codigo_federacion` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `sociofederado`
--

INSERT INTO `sociofederado` (`id_socio`, `codigo_federacion`) VALUES
(4, 'FED'),
(5, 'FAS'),
(6, 'EUOC');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `socioinfantil`
--

CREATE TABLE `socioinfantil` (
  `numero_socio` int NOT NULL,
  `tutor` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `socioinfantil`
--

INSERT INTO `socioinfantil` (`numero_socio`, `tutor`) VALUES
(7, 1),
(8, 5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tiposocio`
--

CREATE TABLE `tiposocio` (
  `nombre` enum('ESTANDAR','FEDERADO','INFANTIL') NOT NULL,
  `descuento_mensual` double NOT NULL,
  `descuento_excursion` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `tiposocio`
--

INSERT INTO `tiposocio` (`nombre`, `descuento_mensual`, `descuento_excursion`) VALUES
('ESTANDAR', 0, 0),
('FEDERADO', 0.05, 0.1),
('INFANTIL', 0.5, 0);

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
  ADD PRIMARY KEY (`numero_socio`);

--
-- Indices de la tabla `socioestandar`
--
ALTER TABLE `socioestandar`
  ADD PRIMARY KEY (`numero_socio`),
  ADD KEY `seguro_fk` (`seguro`);

--
-- Indices de la tabla `sociofederado`
--
ALTER TABLE `sociofederado`
  ADD KEY `id_socio_ref` (`id_socio`),
  ADD KEY `federacion_ref` (`codigo_federacion`);

--
-- Indices de la tabla `socioinfantil`
--
ALTER TABLE `socioinfantil`
  ADD PRIMARY KEY (`numero_socio`),
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
  MODIFY `numero_inscripcion` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `socio`
--
ALTER TABLE `socio`
  MODIFY `id_socio` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

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
  ADD CONSTRAINT `socioadulto_ibfk_1` FOREIGN KEY (`numero_socio`) REFERENCES `socio` (`id_socio`) ON DELETE CASCADE ON UPDATE RESTRICT;

--
-- Filtros para la tabla `socioestandar`
--
ALTER TABLE `socioestandar`
  ADD CONSTRAINT `seguro_fk` FOREIGN KEY (`seguro`) REFERENCES `seguro` (`nombre`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `socioadultofk` FOREIGN KEY (`numero_socio`) REFERENCES `socioadulto` (`numero_socio`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `sociofederado`
--
ALTER TABLE `sociofederado`
  ADD CONSTRAINT `federacion_ref` FOREIGN KEY (`codigo_federacion`) REFERENCES `federacion` (`codigo`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `id_socio_ref` FOREIGN KEY (`id_socio`) REFERENCES `socioadulto` (`numero_socio`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `socioinfantil`
--
ALTER TABLE `socioinfantil`
  ADD CONSTRAINT `socioinfantil_ibfk_1` FOREIGN KEY (`numero_socio`) REFERENCES `socio` (`id_socio`) ON DELETE CASCADE ON UPDATE RESTRICT,
  ADD CONSTRAINT `socioinfantil_ibfk_2` FOREIGN KEY (`tutor`) REFERENCES `socioadulto` (`numero_socio`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
