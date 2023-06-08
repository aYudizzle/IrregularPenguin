package com.besteleben.feature_irregularpenguin.data.repository;

import com.besteleben.feature_irregularpenguin.data.objects.Vocabulary;

/**
 * Repository Interface for further data source implementations
 */
public interface VocabularyRepository {
    /**
     * zum Suchen eines zufaelligen datensatzes aus der datenquelle
     * @return gibt ein VocabularyDTO zurueck
     */
    Vocabulary getRandomVocabulary();
}
