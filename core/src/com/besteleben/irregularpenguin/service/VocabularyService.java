package com.besteleben.irregularpenguin.service;

import com.besteleben.irregularpenguin.data.Vocabulary;
import com.besteleben.irregularpenguin.data.VocabularyDao;

/**
 * Service/Middletier for business logic
 */
public class VocabularyService {
    /** data source of choice */
    private VocabularyDao dataSource;
    /** asked vocabulary */
    private Vocabulary vocabulary;

    /** concstructor to get a service with the certain data source */
    public VocabularyService(VocabularyDao dataSource) {
        this.dataSource = dataSource;
    }

    public Vocabulary getVocabulary(){
        return dataSource.getRandomVocabulary();
    }

}
