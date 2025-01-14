--
-- Bolcament de dades per a la taula `users`
--

INSERT INTO `users` (`role`, `username`, `password`, `full_name`, `phone`) VALUES
('ADMIN', 'alex', '$2a$10$w2USjLND2dheaXiXFSRgo.pIOiYUBecdmFlFnxHN8HRxwFYuYp3g6', NULL, NULL),
('CLIENT', 'genis', '$2a$10$fN.nOfWlRY/LLotIWiseoeh/foQ1vFCY9bnpXmK3k8.VwW7F1xoPi', 'Genís Esteve i Prats', 633151523),
('ADMIN', 'laia', '$2a$10$EwsBI6trHD56ncjlsxAmwuic5R/qAzx6AyekBpCafndN.CiFuwJjK', NULL, NULL),
('CLIENT', 'lola', '$2a$10$/oyUYwpxe1m40Qe070kO5u44g50XrQe0htB08nwOXcU7/PMO1TBKS', 'Lola Valls i Vilalta', 633251101),
('CLIENT', 'maria', '$2a$10$EogCF6kJDxTPsfQFciZjROaSBd/8Ok3orVe49KdEebVdyVTYrCKs2', 'Maria Lopez i Castells', 633352133),
('ADMIN', 'marta', '$2a$10$0TIRK3JqDKs.4HXqN//yweu9EFdOCn64p3X1zGIkeGhKtD8CQ73ou', NULL, NULL),
('CLIENT', 'raul', '$2a$10$Zt92wjlBEPx2zXwdTfA4ZeM2cFAAX4MXY4y9y1BKMEZmYbNh.8dz6', 'Raul Casanova i Ferrer', 633154041),
('CLIENT', 'robert', '$2a$10$H1S18hqeIbIoPgVU7ECURu6nsitQ0/sGSEJ9Z0Dw6rBV/bloAmCTS', 'Robert Planes i Pujol', 633356101),
('CLIENT', 'toni', '$2a$10$T1lgKgp5XiQAuvTiq4alQeWkgCVHpsHVgmgk/X7wIkJwHypR6TMP2', 'Antoni Bosc i Cases', 633250108);

-- --------------------------------------------------------

--
-- Bolcament de dades per a la taula `trip_types`
--

INSERT INTO `trip_types` (`id`, `category`, `departures`, `description`, `duration`, `max_places`, `price`, `title`) VALUES
(1, 'GROUP', '9:30;13:30;17:30', '1h Sailing Experience', 1, 9, 30, '1 Hour Sailing Tour'),
(2, 'GROUP', '11:30;15:30', 'Relaxing 2h Sailing Tour', 2, 9, 45, '2 Hours Sailing Tour'),
(3, 'GROUP', '19:30', 'Sensational Sunset Sail', 2, 9, 50, 'Sunset Sail'),
(4, 'GROUP', '12:30', 'Watch America’s Cup Barcelona 2024 Live (shared)', 6, 11, 350, 'Watch Live: America’s Cup'),
(5, 'PRIVATE', NULL, 'Private Sailing Tour (max. 9)', 1, 9, 200, 'Private Sailing Tour (max. 9)'),
(6, 'PRIVATE', NULL, 'Private Sailing Tour (max.11)', 6, 11, 300, 'Private Sailing Tour (max.11)'),
(7, 'PRIVATE', NULL, 'Private Luxury Sailing Tour (max.12)', 2, 12, 350, 'Private Luxury Sailing Tour (max.12)');

-- --------------------------------------------------------

--
-- Bolcament de dades per a la taula `departures`
--

INSERT INTO `departures` (`id`, `date`, `departure`, `trip_type_id`) VALUES
(1, '2024-10-30', '09:30:00.000000', 1),
(2, '2024-10-29', '15:30:00.000000', 5),
(8, '2024-11-13', '07:00:00.000000', 7),
(11, '2024-11-18', '11:00:00.000000', 7);

-- --------------------------------------------------------

--
-- Bolcament de dades per a la taula `trips`
--

INSERT INTO `trips` (`id`, `places`, `client_username`, `departure_id`) VALUES
(1, 3, 'lola', 1),
(2, 2, 'robert', 1),
(3, 5, 'maria', 2),
(4, 2, 'lola', 8),
(6, 3, 'lola', 11);

-- --------------------------------------------------------

--
-- Bolcament de dades per a la taula `actions`
--

INSERT INTO `actions` (`type`, `id`, `comments`, `date`, `old_date`, `old_departure`, `performer_username`, `trip_id`, `reason`, `new_date`, `new_departure`) VALUES
('BOOKING', 1, NULL, '2024-10-19 14:46:04', NULL, NULL, 'lola', 1, NULL, NULL, NULL),
('BOOKING', 2, NULL, '2024-10-19 14:46:04', NULL, NULL, 'robert', 2, NULL, NULL, NULL),
('BOOKING', 3, NULL, '2024-10-19 14:46:22', NULL, NULL, 'maria', 3, NULL, NULL, NULL),
('CANCELLATION', 4, NULL, '2024-10-19 14:56:04', NULL, NULL, 'lola', 1, NULL, NULL, NULL),
('DONE', 5, 'Finalitzat tot OK', '2024-10-20 14:56:04', NULL, NULL, 'alex', 3, NULL, NULL, NULL),
('BOOKING', 6, NULL, '2024-11-04 18:38:03', NULL, NULL, 'lola', 4, NULL, NULL, NULL),
('BOOKING', 7, NULL, '2024-11-07 11:04:11', NULL, NULL, 'lola', 6, NULL, NULL, NULL);



