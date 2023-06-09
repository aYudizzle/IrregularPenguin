package com.besteleben.feature_irregularpenguin.data.repository;

import com.besteleben.feature_irregularpenguin.data.objects.Vocabulary;
import com.besteleben.feature_irregularpenguin.data.objects.WrongVocabulariesEntry;

import java.util.List;

/**
 * Repository Interface for further data source implementations
 */
public interface VocabularyRepository {
    /**
     * zum Suchen eines zufaelligen datensatzes aus der datenquelle
     * @return gibt ein VocabularyDTO zurueck
     */
    Vocabulary getRandomVocabulary();

    /**
     * saves the wrong answered vocabulary
     * @param userId id from the user who answered the vocabulary wrong
     * @param vocabularyId id from vocabulary which got answered wrong
     */
    void saveWrongAnsweredVocabulary(int userId, int vocabularyId);

    /**
     * Update the wrong vocabulary
     * @param userId the user's id
     * @param vocabularyId the vocabularies id
     * @param rightAnswers counter of given correct answers
     */
    void updateWrongAnsweredVocabulary(int userId, int vocabularyId, int rightAnswers);
    /**
     * deletes the wrong answered vocabulary
     * @param answerId the answers id
     */
    void deleteWrongAnsweredVocabulary(int answerId);

    /**
     * gets a wrong answered vocabulary by user id and vocabularyId;
     */
    WrongVocabulariesEntry getWrongAnsweredVocabularyById(int userId, int VocabularyId);
    /**
     * list of all wrong answered vocabularies by the user
     * @return list of all wrong answered vocabularies by the user
     */
    List<WrongVocabulariesEntry> getWrongAnsweredVocabularies(int userId);

    /**
     * look up for an vocabulary by Id
     * @param idToLookUp the id to lookup
     * @return the vocabulary
     */
    Vocabulary getVocabularyById(int idToLookUp);
}
