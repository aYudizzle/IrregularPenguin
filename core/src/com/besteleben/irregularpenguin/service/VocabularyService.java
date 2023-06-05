package com.besteleben.irregularpenguin.service;

import com.badlogic.gdx.math.MathUtils;
import com.besteleben.irregularpenguin.data.objects.QuestionerData;
import com.besteleben.irregularpenguin.data.objects.Vocabulary;
import com.besteleben.irregularpenguin.data.repository.VocabularyRepository;
import com.besteleben.irregularpenguin.entities.character.questioner.QuestionerGhostColor;

/**
 * Service/Middletier for business logic
 */
public class VocabularyService {
    /**
     * data source of choice
     */
    private VocabularyRepository dataSource;
    /**
     * asked verb
     */
    private String verb;
    /**
     * expected Answer
     */
    private String expectedAnswer;

    /**
     * concstructor to get a service with the certain data source
     */
    public VocabularyService(VocabularyRepository dataSource) {
        this.dataSource = dataSource;
    }
    /** to get a random vocabulary */
    public Vocabulary getVocabulary() {
        return dataSource.getRandomVocabulary();
    }
    /** generate Data for the question randomly */
    public QuestionerData generateNextQuestion() {
        QuestionerGhostColor[] questionerTextures = QuestionerGhostColor.values();
        int randomIndex = MathUtils.random(questionerTextures.length - 1);
        QuestionerGhostColor color = questionerTextures[randomIndex];
        Vocabulary vocabulary = dataSource.getRandomVocabulary();
        switch (color) {
            case RED:
                verb = vocabulary.getGerman();
                expectedAnswer = vocabulary.getInfinitive();
                break;
            case BLUE:
                verb = vocabulary.getInfinitive();
                expectedAnswer = vocabulary.getGerman();
                break;
            case GREEN:
                verb = vocabulary.getSimple_past();
                expectedAnswer = vocabulary.getGerman();
                break;
            case ORANGE:
                verb = vocabulary.getPast_participle();
                expectedAnswer = vocabulary.getGerman();
                break;
            default:
                verb = vocabulary.getGerman();
                expectedAnswer = vocabulary.getInfinitive();
                break;

        }
        return new QuestionerData(color, verb);
    }

    public boolean checkAnswer(String userAnswer){
        String[] possibleAnswers = expectedAnswer.split(",");
        for(String possibleAnswer : possibleAnswers){
            if(userAnswer.equals(possibleAnswer.trim())){
                return true;
            }
        }
        return false;
    }
}
