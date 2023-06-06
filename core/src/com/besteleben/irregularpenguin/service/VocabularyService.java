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
    private final VocabularyRepository dataSource;
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
     * @param dataSource reference to the source of data
     */
    public VocabularyService(VocabularyRepository dataSource) {
        this.dataSource = dataSource;
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
                verb = vocabulary.getGerman();
                expectedAnswer = vocabulary.getSimple_past();
                break;
            case GREEN:
                verb = vocabulary.getGerman();
                expectedAnswer = vocabulary.getPast_participle();
                break;
            case ORANGE:
                verb = vocabulary.getInfinitive();
                expectedAnswer = vocabulary.getGerman();
                break;
            case DARKBLUE:
                verb = vocabulary.getInfinitive();
                expectedAnswer = vocabulary.getSimple_past();
                break;
            case LIGHTBLUE:
                verb = vocabulary.getInfinitive();
                expectedAnswer = vocabulary.getPast_participle();
                break;
            case LIGHTPURPLE:
                verb = vocabulary.getSimple_past();
                expectedAnswer = vocabulary.getGerman();
                break;
            case PINKRED:
                verb = vocabulary.getSimple_past();
                expectedAnswer = vocabulary.getInfinitive();
                break;
            case PURPLE:
                verb = vocabulary.getSimple_past();
                expectedAnswer = vocabulary.getPast_participle();
                break;
            case TURQUOISE:
                verb = vocabulary.getPast_participle();
                expectedAnswer = vocabulary.getGerman();
                break;
            case YELLOW:
                verb = vocabulary.getPast_participle();
                expectedAnswer = vocabulary.getInfinitive();
                break;
            case PINK:
                verb = vocabulary.getPast_participle();
                expectedAnswer = vocabulary.getSimple_past();
                break;
            default:
                verb = vocabulary.getGerman();
                expectedAnswer = vocabulary.getInfinitive();
                break;
        }
        return new QuestionerData(color, verb);
    }

    /**
     * checks if the given answer is right or wrong
     * @param userAnswer answer of the user
     * @return true or false depending on if the answer was right or wrong
     */
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
