package com.besteleben.irregularpenguin.data;

/**
 * Dao Interface, welches dafuer dient ggf. mehrere Datenquellen zu implementieren.
 */
public interface VocabularyDao {
    /**
     * zum suchen eines zufaelligen datensatzes aus der datenquelle
     * @return gibt ein VocabularyDTO zurueck
     */
    Vocabulary getRandomVocabulary();
}
