-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 06-05-2022 a las 06:13:15
-- Versión del servidor: 10.4.21-MariaDB
-- Versión de PHP: 8.0.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `puntodeventa`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `areas`
--

CREATE TABLE `areas` (
  `codigo` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `areas`
--

INSERT INTO `areas` (`codigo`, `nombre`) VALUES
(1, 'Jugos');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clasificaciones`
--

CREATE TABLE `clasificaciones` (
  `id` int(11) NOT NULL,
  `codigo` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `codigoArea` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `clasificaciones`
--

INSERT INTO `clasificaciones` (`id`, `codigo`, `nombre`, `codigoArea`) VALUES
(3, 1, 'Naturales', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `matricula` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `saldo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`matricula`, `nombre`, `saldo`) VALUES
(15001567, 'Suemi', 9940),
(15003270, 'Jairo Josue Cristobal Franco', 35),
(15004035, 'Rodrigo Hernández Góngora', 1000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `codigo` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `precioCompra` float NOT NULL,
  `precioVenta` float NOT NULL,
  `existencia` int(11) NOT NULL,
  `codigoArea` int(11) NOT NULL,
  `codigoClasificacion` int(11) NOT NULL,
  `descripcion` longtext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`codigo`, `nombre`, `precioCompra`, `precioVenta`, `existencia`, `codigoArea`, `codigoClasificacion`, `descripcion`) VALUES
(111, 'Naranja', 10, 15, 49997, 1, 1, 'Jugo de naranja de 500ml'),
(112, 'Manzana', 10, 15, 50000, 1, 1, 'Jugo de manzana de 500ml'),
(113, 'Jamaica', 10, 15, 50000, 1, 1, 'Jamaica de 500ml'),
(114, 'Limonada', 10, 15, 49998, 1, 1, 'Limonada de 500ml'),
(115, 'Tamarindo', 10, 15, 54321, 1, 1, 'Jugo de tamarindo de 500ml');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reportecliente`
--

CREATE TABLE `reportecliente` (
  `idreporteCliente` int(11) NOT NULL,
  `matricula` int(11) DEFAULT NULL,
  `fecha` varchar(45) DEFAULT NULL,
  `accion` varchar(45) DEFAULT NULL,
  `actualizacion` double DEFAULT NULL,
  `saldo` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `reportecliente`
--

INSERT INTO `reportecliente` (`idreporteCliente`, `matricula`, `fecha`, `accion`, `actualizacion`, `saldo`) VALUES
(1, 15003270, '05-05-2022', 'Retiro', -5, 10),
(2, 15003270, '05-05-2022', 'Retiro', -10, 0),
(3, 15003270, '05-05-2022', 'Depósito', 50, 50),
(4, 15003270, '05-05-2022', 'Compra', -15, 35);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reportes`
--

CREATE TABLE `reportes` (
  `id` int(11) NOT NULL,
  `codigo` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `subtotal` float NOT NULL,
  `fecha` varchar(45) NOT NULL,
  `matricula` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `reportes`
--

INSERT INTO `reportes` (`id`, `codigo`, `nombre`, `cantidad`, `subtotal`, `fecha`, `matricula`) VALUES
(2, 111, 'Naranja', 1, 15, '2-2-2002', 15001567),
(3, 111, '111: Naranja', 1, 15, '03-05-2022', 15001567),
(4, 114, '114: Limonada', 2, 30, '03-05-2022', 15001567),
(5, 111, 'Naranja', 1, 15, '03-05-2022', 15001567),
(6, 111, 'Naranja', 1, 15, '05-05-2022', 15003270);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `areas`
--
ALTER TABLE `areas`
  ADD PRIMARY KEY (`codigo`);

--
-- Indices de la tabla `clasificaciones`
--
ALTER TABLE `clasificaciones`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`matricula`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`codigo`);

--
-- Indices de la tabla `reportecliente`
--
ALTER TABLE `reportecliente`
  ADD PRIMARY KEY (`idreporteCliente`);

--
-- Indices de la tabla `reportes`
--
ALTER TABLE `reportes`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `areas`
--
ALTER TABLE `areas`
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `clasificaciones`
--
ALTER TABLE `clasificaciones`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `reportecliente`
--
ALTER TABLE `reportecliente`
  MODIFY `idreporteCliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `reportes`
--
ALTER TABLE `reportes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
