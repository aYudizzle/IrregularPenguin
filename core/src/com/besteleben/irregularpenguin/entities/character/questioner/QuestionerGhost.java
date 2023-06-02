package com.besteleben.irregularpenguin.entities.character.questioner;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.besteleben.irregularpenguin.data.Vocabulary;
import com.besteleben.irregularpenguin.entities.character.IngameCharacter;

public class QuestionerGhost extends IngameCharacter implements Questioner {
    private final TextureAtlas textureAtlas = new TextureAtlas("questioner.atlas");
    private Animation<TextureRegion> blinkAnimation;
    private Animation<TextureRegion> appearingAnimation;
    private final float frameDuration;
    private float elapsedTimeDisappearing;
    private boolean questionAnswered; // wenn true dann soll der questioner disappearen.
    private QuestionerGhostColor actualTexture;
    private final Label questionLabel;
    private Vocabulary vocabulary;


    public QuestionerGhost(float frameDuration, float defaultX, float defaultY, Skin skin) {
        this.defaultY = defaultY;
        //150px to the right to get the ghost off center and infront of the penguin
        this.defaultX = defaultX+150;
        this.frameDuration = frameDuration;
        this.questionLabel = new Label("", skin);

        initializeLabel();
        initializeQuestionerTexture();
        currentRegion = new TextureRegion();
        // Default Values for Testing purposes gets removed later on
        vocabulary = new Vocabulary(1, "sein", "be","was", "been");
    }

    private void initializeLabel() {
        questionLabel.setPosition(defaultX-250,defaultY+50);
        questionLabel.setAlignment(Align.top);
        questionLabel.setSize(200, 110);

//        questionLabel.setAlignment(Align.center);
        questionLabel.setScale(0.75f);
        questionLabel.setVisible(false);
        questionLabel.setWrap(true);
    }

    /**
     * method to declare a random Questioner Texture and initialising the wanted answer form
     */
    private void initializeQuestionerTexture() {
        QuestionerGhostColor[] questionerTextures = QuestionerGhostColor.values();
        int randomIndex = MathUtils.random(questionerTextures.length - 1);
        actualTexture = questionerTextures[randomIndex];
        Array<TextureAtlas.AtlasRegion> idleFrames = textureAtlas.findRegions(actualTexture.getIdleRegionName());
        Array<TextureAtlas.AtlasRegion> blinkFrames = textureAtlas.findRegions(actualTexture.getBlinkRegionName());
        idleAnimation = new Animation<>(frameDuration + 0.25f, idleFrames, Animation.PlayMode.LOOP);
        blinkAnimation = new Animation<>(frameDuration, blinkFrames, Animation.PlayMode.NORMAL);
        appearingAnimation = new Animation<>(frameDuration, blinkFrames, Animation.PlayMode.REVERSED);
        setSize(blinkFrames.first().getRegionWidth(), blinkFrames.first().getRegionHeight());
        setX(defaultX-100);
        setY(defaultY);
        setScale(3);
    }
    /**
     * Updates the actor based on time. Typically this is called each frame by {@link Stage#act(float)}.
     * <p>
     * The default implementation calls {@link Action#act(float)} on each action and removes actions that are complete.
     *
     * @param delta Time in seconds since the last frame.
     */
    @Override
    public void act(float delta) {
        super.act(delta);
        defaultX = getStage().getWidth() / 2 + 100;
        elapsedTime += delta;
        TextureRegion currentFrame;
        if (appearingAnimation.isAnimationFinished(elapsedTime)) {
            if (!questionAnswered) {
                currentFrame = idleAnimation.getKeyFrame(elapsedTime);
                currentRegion.setRegion(currentFrame);
            } else {
                elapsedTimeDisappearing += delta;
                currentFrame = blinkAnimation.getKeyFrame(elapsedTimeDisappearing);
                currentRegion.setRegion(currentFrame);
            }
        } else {
            currentFrame = appearingAnimation.getKeyFrame(elapsedTime);
            currentRegion.setRegion(currentFrame);
        }
    }

    /**
     * Draws the actor. The batch is configured to draw in the parent's coordinate system.
     * {@link Batch#draw(TextureRegion, float, float, float, float, float, float, float, float, float)
     * This draw method} is convenient to draw a rotated and scaled TextureRegion. {@link Batch#begin()} has already been called on
     * the batch. If {@link Batch#end()} is called to draw without the batch then {@link Batch#begin()} must be called before the
     * method returns.
     * <p>
     * The default implementation does nothing.
     *
     * @param batch
     * @param parentAlpha The parent alpha, to be multiplied with this actor's alpha, allowing the parent's alpha to affect all
     *                    children.
     */
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (currentRegion != null) {
            batch.draw(currentRegion, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
            if(questionLabel.isVisible()){
                questionLabel.draw(batch, parentAlpha);
            }
        }
    }

    /**
     * for displaying the Question
     */
    @Override
    public void showQuestion() {
        if(actualTexture == QuestionerGhostColor.RED){
            String labelText = "\n";
            String searchTerm = actualTexture.getQuestionTerm().replace("_", " ");
            labelText += String.format(" What is the \n%s word of \n%s", searchTerm, vocabulary.getInfinitive());
            questionLabel.setText(labelText);
        } else {
            String labelText = "\n";
            String searchTerm = actualTexture.getQuestionTerm().replace("_", " ");
            labelText += String.format(" What is the \n%s of \n%s", searchTerm, vocabulary.getGerman());
            questionLabel.setText(labelText);
        }
//        if(actualTexture == QuestionerTexture.GREEN){
//            String labelText = "\n";
//            String searchTerm = actualTexture.getQuestionTerm().replace("_", " ");
//            labelText += String.format(" What is the \n%s of \n%s", searchTerm, vocabulary.getGerman());
//            questionLabel.setText(labelText);
//        }
//        if(actualTexture == QuestionerTexture.BLUE){
//            String labelText = "\n";
//            String searchTerm = actualTexture.getQuestionTerm().replace("_", " ");
//            labelText += String.format(" What is the \n%s of \n%s", searchTerm,vocabulary.getGerman());
//            questionLabel.setText(labelText);
//        }
//        if(actualTexture == QuestionerTexture.ORANGE){
//            String labelText = "\n";
//            String searchTerm = actualTexture.getQuestionTerm().replace("_", " ");
//            labelText += String.format(" What is the \n%s of \n %s", searchTerm,vocabulary.getGerman());
//            questionLabel.setText(labelText);
//        }
        }


        public void brainstorm(){
            switch (actualTexture){
                case BLUE:
                    System.out.println(vocabulary.getGerman());
                    break;
                case GREEN:
                    System.out.println(vocabulary.getGerman());
                    break;
                case ORANGE:
                    System.out.println(vocabulary.getGerman());
                    break;
                case RED:
                    System.out.println(vocabulary.getInfinitive());
                    break;
            }
        }

    /**
     * Processing the answer if right or wrong
     *
     * @param answer given answer from an user input
     */
    @Override
    public void processAnswer(String answer) {
        //todo process the right answer
    }

    /**
     * Sets vocabulary.
     *
     * @param vocabulary value of vocabulary
     */
    public void setVocabulary(Vocabulary vocabulary) {
        this.vocabulary = vocabulary;
    }
    /**
     * Gets vocabulary.
     *
     * @return value of vocabulary
     */
    public Vocabulary getVocabulary() {
        return vocabulary;
    }

    /**
     * Gets questionLabel.
     *
     * @return value of questionLabel
     */
    public Label getQuestionLabel() {
        return questionLabel;
    }

    /**
     * Gets actualTexture.
     *
     * @return value of actualTexture
     */
    public QuestionerGhostColor getActualTexture() {
        return actualTexture;
    }
}
