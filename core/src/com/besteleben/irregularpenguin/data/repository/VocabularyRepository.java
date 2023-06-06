package com.besteleben.irregularpenguin.data.repository;

import com.besteleben.irregularpenguin.data.objects.Vocabulary;

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
