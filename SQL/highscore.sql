SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

CREATE TABLE `highscore` (
  `id` int(11) NOT NULL,
  `playername` varchar(50) NOT NULL,
  `playerscore` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

INSERT INTO `highscore` (`id`, `playername`, `playerscore`) VALUES
(1, 'unknown', 0),
(2, 'unknown', 0),
(3, 'unknown', 0),
(4, 'unknown', 0),
(5, 'unknown', 0),
(6, 'unknown', 0),
(7, 'unknown', 0),
(8, 'unknown', 0),
(9, 'unknown', 0),
(10, 'unknown', 0);

ALTER TABLE `highscore`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `highscore`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;
