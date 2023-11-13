SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

CREATE TABLE `wrong_answers` (
  `id` int(11) NOT NULL,
  `userId` int(11) DEFAULT NULL,
  `vocabularyId` int(11) DEFAULT NULL,
  `lastIncorrectAnswerTime` timestamp NULL DEFAULT NULL,
  `correctAnswers` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

ALTER TABLE `wrong_answers`
  ADD PRIMARY KEY (`id`),
  ADD KEY `UserID` (`userId`),
  ADD KEY `VocabularyID` (`vocabularyId`);

ALTER TABLE `wrong_answers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;
COMMIT;