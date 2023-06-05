package com.besteleben.irregularpenguin.entities.character.questioner;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;
import com.besteleben.irregularpenguin.entities.character.IngameCharacter;

/** representing the questioner in the game who displays the asked verb */
public class QuestionerGhost extends IngameCharacter implements Questioner {
    /** keeps the .atlas file for the different textures */
    private final TextureAtlas textureAtlas = new TextureAtlas("questioner.atlas");
    /** animation for the disappearing animation */
    private Animation<TextureRegion> blinkAnimation;
    /** animation for the appearing animation */
    private Animation<TextureRegion> appearingAnimation;
    /** float number for the duration for every single frame */
    private final float frameDuration;
    /** counter for the frameduration when the questioner has to appear/disappear */
    private float elapsedTimeDisappearing;
    /** questionAnswered so other actors can react to it */
    private boolean questionAnswered; // wenn true dann soll der questioner disappearen.
    /** random texture from enum getting randomized in service */
    private QuestionerGhostColor actualTexture;
    /** speechbubble to display the question */
    private final Label questionLabel;


    /** constructor with base settings for the questioner ghost */
    public QuestionerGhost(float frameDuration, float defaultX, float defaultY, Skin skin) {
        this.defaultY = defaultY;
        //150px to the right to get the ghost off center and infront of the penguin
        this.defaultX = defaultX + 150;
        this.frameDuration = frameDuration;
        this.questionLabel = new Label("", skin);
        initializeLabel();
        currentRegion = new TextureRegion();
        setX(this.defaultX);
    }
    /** initialize the base settings of the speechbubble/label so its on the right coordinates */
    private void initializeLabel() {
        questionLabel.setPosition(defaultX - 250, defaultY + 50);
        questionLabel.setAlignment(Align.top);
        questionLabel.setSize(200, 110);

        questionLabel.setScale(0.75f);
        questionLabel.setVisible(false);
        questionLabel.setWrap(true);
    }

    /**
     * method to declare a random Questioner Texture and initialising the wanted answer form
     */
    public void initializeQuestionerTexture() {
        Array<TextureAtlas.AtlasRegion> idleFrames = textureAtlas.findRegions(actualTexture.getIdleRegionName());
        Array<TextureAtlas.AtlasRegion> blinkFrames = textureAtlas.findRegions(actualTexture.getBlinkRegionName());
        idleAnimation = new Animation<>(frameDuration + 0.25f, idleFrames, Animation.PlayMode.LOOP);
        blinkAnimation = new Animation<>(frameDuration, blinkFrames, Animation.PlayMode.NORMAL);
        appearingAnimation = new Animation<>(frameDuration, blinkFrames, Animation.PlayMode.REVERSED);
        setSize(blinkFrames.first().getRegionWidth(), blinkFrames.first().getRegionHeight());
        setX(defaultX - 100);
        setY(defaultY);
        setScale(3);
    }

    /**
     * Updates the actor based on time. Typically this is called each frame by {@link Stage#act(float)}.
     * <p>
     * The default implementation calls {@link Action#act(float)} on each action and removes actions that are complete.
     *
     * shows the actual frame by frame so the ghost can appear idle or disappear
     *
     * @param delta Time in seconds since the last frame.
     */
    @Override
    public void act(float delta) {
        super.act(delta);
        defaultX = getStage().getWidth() / 2;
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
            if (questionLabel.isVisible()) {
                questionLabel.draw(batch, parentAlpha);
            }
        }
    }

    /**
     * for displaying the Question and setting up the Data for the label etc.
     */
    @Override
    public void settingUpQuestion(String verb) {
        if (actualTexture == QuestionerGhostColor.RED) {
            String labelText = String.format("\nWhat is the infinitive of\n %s", verb);
            questionLabel.setText(labelText);
        } else {
            String labelText = String.format("\nWhat is the meaning of\n \"%s\"", verb);
            questionLabel.setText(labelText);
        }
    }

    /**
     * Processing the answer is right
     */
    @Override
    public void processRightAnswer() {
        questionLabel.setText("\nNice, well done!");
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                questionAnswered = true;
                questionLabel.setVisible(false);
            }
        }, 0.3f);

    }

    /** gets called by stage when the game is over cause the lifebar reached 0 */
    public void gameEnd() {
        String labelText = "\nGame Over\nbetter luck next time";
        questionLabel.setText(labelText);
    }
    /**resets the questioner to his default position */
    public void reset() {
        questionLabel.setVisible(false);
        elapsedTimeDisappearing = 0;
        elapsedTime = 0;
        questionAnswered = false;
        setX(defaultX+50); //todo why tho ohne 50 verschieben is der ghost offset
    }
    /**
     * Sets actualTexture.
     *
     * @param actualTexture value of actualTexture
     */
    public void setActualTexture(QuestionerGhostColor actualTexture) {
        this.actualTexture = actualTexture;
    }

    /**
     * Gets questionLabel.
     *
     * @return value of questionLabel
     */
    public Label getQuestionLabel() {
        return questionLabel;
    }
}
