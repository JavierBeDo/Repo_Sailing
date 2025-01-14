-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Temps de generació: 18-11-2024 a les 12:07:50
-- Versió del servidor: 10.3.31-MariaDB-0+deb10u1
-- Versió de PHP: 7.4.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de dades: `sailingdb`
--

-- --------------------------------------------------------

--
-- Estructura de la taula `actions`
--

CREATE TABLE `actions` (
  `type` varchar(31) COLLATE utf8mb4_spanish_ci NOT NULL,
  `id` bigint(20) NOT NULL,
  `comments` varchar(255) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `date` timestamp NOT NULL DEFAULT current_timestamp(),
  `old_date` date DEFAULT NULL,
  `old_departure` time(6) DEFAULT NULL,
  `performer_username` varchar(25) COLLATE utf8mb4_spanish_ci NOT NULL,
  `trip_id` bigint(20) NOT NULL,
  `reason` varchar(255) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `new_date` date DEFAULT NULL,
  `new_departure` time(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de la taula `departures`
--

CREATE TABLE `departures` (
  `id` bigint(20) NOT NULL,
  `date` date NOT NULL,
  `departure` time(6) NOT NULL,
  `trip_type_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de la taula `trips`
--

CREATE TABLE `trips` (
  `id` bigint(20) NOT NULL,
  `places` int(11) NOT NULL,
  `client_username` varchar(25) COLLATE utf8mb4_spanish_ci NOT NULL,
  `departure_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;


-- --------------------------------------------------------

--
-- Estructura de la taula `trip_types`
--

CREATE TABLE `trip_types` (
  `id` bigint(20) NOT NULL,
  `category` enum('GROUP','PRIVATE') COLLATE utf8mb4_spanish_ci NOT NULL,
  `departures` varchar(255) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `description` varchar(255) COLLATE utf8mb4_spanish_ci NOT NULL,
  `duration` int(11) NOT NULL,
  `max_places` int(11) DEFAULT NULL,
  `price` double NOT NULL,
  `title` varchar(255) COLLATE utf8mb4_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Estructura de la taula `users`
--

CREATE TABLE `users` (
  `role` varchar(31) COLLATE utf8mb4_spanish_ci NOT NULL,
  `username` varchar(25) COLLATE utf8mb4_spanish_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_spanish_ci NOT NULL,
  `full_name` varchar(100) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `phone` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Índexs per a les taules bolcades
--

--
-- Índexs per a la taula `actions`
--
ALTER TABLE `actions`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `trip_x_date` (`trip_id`,`date`),
  ADD KEY `type` (`type`),
  ADD KEY `fk_trip_id` (`trip_id`),
  ADD KEY `fk_performer_username` (`performer_username`);

--
-- Índexs per a la taula `departures`
--
ALTER TABLE `departures`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `triptype_date_departure` (`trip_type_id`,`date`,`departure`);

--
-- Índexs per a la taula `trips`
--
ALTER TABLE `trips`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_client_username` (`client_username`),
  ADD KEY `fk_departure_id` (`departure_id`);

--
-- Índexs per a la taula `trip_types`
--
ALTER TABLE `trip_types`
  ADD PRIMARY KEY (`id`);

--
-- Índexs per a la taula `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`username`),
  ADD KEY `role` (`role`),
  ADD KEY `full_name` (`full_name`),
  ADD KEY `role_x_full_name` (`role`,`full_name`);

--
-- AUTO_INCREMENT per les taules bolcades
--

--
-- AUTO_INCREMENT per la taula `actions`
--
ALTER TABLE `actions`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT per la taula `departures`
--
ALTER TABLE `departures`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT per la taula `trips`
--
ALTER TABLE `trips`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT per la taula `trip_types`
--
ALTER TABLE `trip_types`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Restriccions per a les taules bolcades
--

--
-- Restriccions per a la taula `actions`
--
ALTER TABLE `actions`
  ADD CONSTRAINT `fk_trip_id` FOREIGN KEY (`trip_id`) REFERENCES `trips` (`id`),
  ADD CONSTRAINT `fk_performer_username` FOREIGN KEY (`performer_username`) REFERENCES `users` (`username`);

--
-- Restriccions per a la taula `departures`
--
ALTER TABLE `departures`
  ADD CONSTRAINT `fk_trip_type_id` FOREIGN KEY (`trip_type_id`) REFERENCES `trip_types` (`id`);

--
-- Restriccions per a la taula `trips`
--
ALTER TABLE `trips`
  ADD CONSTRAINT `fk_client_username` FOREIGN KEY (`client_username`) REFERENCES `users` (`username`),
  ADD CONSTRAINT `fk_departure_id` FOREIGN KEY (`departure_id`) REFERENCES `departures` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
