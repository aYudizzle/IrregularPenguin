package com.besteleben.feature_irregularpenguin.service;

import com.badlogic.gdx.math.MathUtils;
import com.besteleben.feature_irregularpenguin.data.objects.QuestionerData;
import com.besteleben.feature_irregularpenguin.data.objects.Vocabulary;
import com.besteleben.feature_irregularpenguin.data.objects.WrongVocabulariesEntry;
import com.besteleben.feature_irregularpenguin.data.repository.VocabularyRepository;
import com.besteleben.feature_irregularpenguin.entities.character.questioner.QuestionerGhostColor;
import com.besteleben.feature_login.objects.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Service/Middletier for business logic
 */
public class GameVocabularyService {
    /**
     * number of right answers before delete
     */
    private static final int MAX_COUNT_OF_RIGHT_ANSWERS = 5;
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
     * saves the actual Vocabulary
     */
    private Vocabulary actualVocabulary;

    /**
     * concstructor to get a service with the certain data source
     *
     * @param dataSource reference to the source of data
     */
    public GameVocabularyService(VocabularyRepository dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * generate Data for the question and checks if an answer given needs to remove from the wrong answer list
     *
     * @param answerCorrect was the answer correct or wrong
     * @return a QuestionerData Object containing the QuestionerGhostColor and the verb to ask for
     */
    public QuestionerData generateNextQuestion(boolean answerCorrect) {
        if (answerCorrect) {
            checkWrongAnsweredVocabulary();
        }
        QuestionerGhostColor[] questionerTextures = QuestionerGhostColor.values();
        int randomIndex = MathUtils.random(questionerTextures.length - 1);
        QuestionerGhostColor color = questionerTextures[randomIndex];
        Vocabulary vocabulary = selectVocabulary();
        actualVocabulary = vocabulary;
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
     * checks if the answered vocabulary is one of the wrong answered vocabulary
     * and then looks up if the count of correct answers matches the MAX_COUNT_OF_RIGHT_ANSWERS
     * if it does, then delete it from  wrong answers
     */
    private void checkWrongAnsweredVocabulary() {
        int vocabularyId = actualVocabulary.getId();
        int userId = User.getInstance().getId();
        WrongVocabulariesEntry entry = dataSource.getWrongAnsweredVocabularyByIds(userId, vocabularyId);
        if (entry != null) {
            if (entry.getCountOfRightAnswers() == MAX_COUNT_OF_RIGHT_ANSWERS) {
                dataSource.deleteWrongAnsweredVocabulary(entry.getId());
            } else {
                int countOfRightAnswers = entry.getCountOfRightAnswers() + 1;
                dataSource.updateWrongAnsweredVocabulary(entry.getUserId(), entry.getVocabularyId(), countOfRightAnswers);
            }
        }
    }

    /**
     * to get a vocabulary from the repository
     * looks up if there are some vocabularies which got answered wrong, and it's older than the actual date
     * then pick this up otherwise pick a random vocabulary
     *
     * @return the selected Vocabulary
     */
    private Vocabulary selectVocabulary() {
        int userId = User.getInstance().getId();
        List<WrongVocabulariesEntry> wrongAnsweredVocabularies;
        List<WrongVocabulariesEntry> filteredVocabularies = new ArrayList<>();
        wrongAnsweredVocabularies = dataSource.getWrongAnsweredVocabularies(userId);
        wrongAnsweredVocabularies.stream()
                .filter(entry -> entry.getDateOfWrongAnswer().isBefore(LocalDate.now()))
                .forEach(filteredVocabularies::add);
        if (filteredVocabularies.isEmpty()) {
            return dataSource.getRandomVocabulary();
        } else {
            Collections.shuffle(filteredVocabularies);
            int idToLookUp = filteredVocabularies.get(0).getVocabularyId();
            return dataSource.getVocabularyById(idToLookUp);
        }
    }

    /**
     * checks if the given answer is right or wrong
     *
     * @param userAnswer answer of the user
     * @return true or false depending on if the answer was right or wrong
     */
    public boolean checkAnswer(String userAnswer) {
        String[] possibleAnswers = expectedAnswer.split(",");
        for (String possibleAnswer : possibleAnswers) {
            if (userAnswer.equals(possibleAnswer.trim())) {
                return true;
            }
        }
        return false;
    }

    /**
     * saves a wrong answered vocabulary.
     */
    public void saveWrongAnsweredVocabulary() {
        User user = User.getInstance();
        int userId = user.getId();
        int vocabularyId = actualVocabulary.getId();
        dataSource.saveWrongAnsweredVocabulary(userId, vocabularyId);
    }
}
