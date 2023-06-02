package tests;

import com.besteleben.irregularpenguin.data.VocabularyDaoImpl;

public class DatabaseTest {
    public static void main(String[] args) {
        VocabularyDaoImpl mariaDatabase = new VocabularyDaoImpl();
        System.out.println(mariaDatabase.getRandomVocabulary());
    }
}
