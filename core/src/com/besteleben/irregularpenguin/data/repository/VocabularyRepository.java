package com.besteleben.irregularpenguin.data.repository;

import com.besteleben.irregularpenguin.data.objects.Vocabulary;

/**
 * Dao Interface, welches dafuer dient ggf. mehrere Datenquellen zu implementieren.
 */
public interface VocabularyRepository {
    /**
     * zum Suchen eines zufaelligen datensatzes aus der datenquelle
     * @return gibt ein VocabularyDTO zurueck
     */
    Vocabulary getRandomVocabulary();
}
