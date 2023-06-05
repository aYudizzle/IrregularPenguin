package tests;

import com.besteleben.irregularpenguin.data.repository.VocabularyRepositoryImpl;

public class DatabaseTest {
    public static void main(String[] args) {
        VocabularyRepositoryImpl mariaDatabase = new VocabularyRepositoryImpl();
        System.out.println(mariaDatabase.getRandomVocabulary());
    }
}
