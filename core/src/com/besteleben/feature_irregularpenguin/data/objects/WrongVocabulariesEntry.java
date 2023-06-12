package com.besteleben.feature_irregularpenguin.data.objects;

import java.time.LocalDate;

/**
 * class to transport the data from all wrong answered vocabularies
 */
public class WrongVocabulariesEntry {
    /** primary key of the entry */
    private int id;
    /** id of the user */
    private int userId;
    /** id of the vocabulary */
    private int vocabularyId;
    /** date of the entry when the vocabulary got insert into persistence level */
    private LocalDate dateOfWrongAnswer;
    /** count of right answers */
    private int countOfRightAnswers;

    /**
     * creating a transfer Object for given wrong answers
     * @param id id of the entry
     * @param userId user id of the wrong given answer
     * @param vocabularyId id of the vocabulary
     * @param dateOfWrongAnswer date of the wrong given answer
     * @param countOfRightAnswers how often did the user give the right answer after the last time of the wrong answer.
     */
    public WrongVocabulariesEntry(int id, int userId, int vocabularyId, LocalDate dateOfWrongAnswer, int countOfRightAnswers) {
        this.id = id;
        this.userId = userId;
        this.vocabularyId = vocabularyId;
        this.dateOfWrongAnswer = dateOfWrongAnswer;
        this.countOfRightAnswers = countOfRightAnswers;
    }

    /**
     * Gets userId.
     *
     * @return value of userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Gets vocabularyId.
     *
     * @return value of vocabularyId
     */
    public int getVocabularyId() {
        return vocabularyId;
    }

    /**
     * Gets dateOfWrongAnswer.
     *
     * @return value of dateOfWrongAnswer
     */
    public LocalDate getDateOfWrongAnswer() {
        return dateOfWrongAnswer;
    }

    /**
     * Gets countOfRightAnswers.
     *
     * @return value of countOfRightAnswers
     */
    public int getCountOfRightAnswers() {
        return countOfRightAnswers;
    }

    /**
     * Gets id.
     *
     * @return value of id
     */
    public int getId() {
        return id;
    }
}
