package com.besteleben.feature_irregularpenguin.entities.character.questioner;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;
import com.besteleben.feature_irregularpenguin.entities.character.IngameCharacter;

/**
 * representing the questioner in the game who displays the asked verb
 * Questioner extends IngameCharacter but needs few more options
 * implements the Questioner Interface to add other functionalities
 *
 */
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
    /**
     * a table for the lable to be able to adjust the layout of the speechbubble
     */
    private Table speechBubbleTable;

    /**
     * constructor with base settings for the questioner ghost
     *
     * @param defaultX default X-Coordinate
     * @param defaultY default Y-Coordinate
     * @param skin skin settings coming from the resourcemanager
     * @param frameDuration how long should every single frame last
     * */
    public QuestionerGhost(float frameDuration, float defaultX, float defaultY, Skin skin) {
        this.defaultY = defaultY;
        //150px to the right to get the ghost off center and infront of the penguin
        this.defaultX = defaultX + 150;
        this.frameDuration = frameDuration;
        this.questionLabel = new Label("", skin);


        initializeLabel();
        currentTextureRegion = new TextureRegion();
        setX(this.defaultX);
    }
    /** initialize the base settings of the speechbubble/label so it gets displayed on the right coordinates on the screen */
    private void initializeLabel() {
        questionLabel.setPosition(defaultX - 250, defaultY + 50);
        questionLabel.setAlignment(Align.top);
        Texture textureRegion = new Texture("stage/speechbubble.png");
        speechBubbleTable = new Table();

        // ninepatch for the texture of the bubble so it can get set as background of the table
        NinePatch speechBubblePatch = new NinePatch(textureRegion);
        speechBubbleTable.setBackground(new NinePatchDrawable(speechBubblePatch));
        speechBubbleTable.add(questionLabel).pad(10).grow().top().left();
        speechBubbleTable.setSize(200, 100);
        speechBubbleTable.setPosition(defaultX - 275, defaultY + 75);


        questionLabel.setScale(0.75f);
        questionLabel.setVisible(false);
        questionLabel.setWrap(true);
//        questionLabel.set
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
     * shows the actual frame by frame so the ghost can appear, idle or disappear.
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
                currentTextureRegion.setRegion(currentFrame);
            } else {
                elapsedTimeDisappearing += delta;
                currentFrame = blinkAnimation.getKeyFrame(elapsedTimeDisappearing);
                currentTextureRegion.setRegion(currentFrame);
            }
        } else {
            currentFrame = appearingAnimation.getKeyFrame(elapsedTime);
            currentTextureRegion.setRegion(currentFrame);
        }
    }

    /**
     * Draws the actor. The batch is configured to draw in the parent's coordinate system.
     * {@link Batch#draw(TextureRegion, float, float, float, float, float, float, float, float, float)
     * This draw method} is convenient to draw a rotated and scaled TextureRegion. {@link Batch#begin()} has already been called on
     * the batch. If {@link Batch#end()} is called to draw without the batch then {@link Batch#begin()} must be called before the
     * method returns.
     * <p>
     *
     * draws the ghost and if questionLabel.isVisible() = true the table with the speechbbubble is drawn too.
     *
     * @param batch Batch to draw the currentTextureRegion
     * @param parentAlpha The parent alpha, to be multiplied with this actor's alpha, allowing the parent's alpha to affect all
     *                    children. In this case there is no custom Alpha value sind everything should be fully visible
     */
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (currentTextureRegion != null) {
            batch.draw(currentTextureRegion, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
            if (questionLabel.isVisible()) {
                speechBubbleTable.draw(batch, parentAlpha);
            }
        }
    }
    /**
     * for displaying the Question and setting up the Data for the label etc.
     * @param verb the verb which should get displayed
     */
    @Override
    public void settingUpQuestion(String verb) {
        if (actualTexture.getRequestedForm().equals("german")) {
            String labelText = String.format("What is the infinitive of \"%s\" in %s", verb,actualTexture.getRequestedForm());
            questionLabel.setText(labelText);
        } else {
            String labelText = String.format("What is the %s of \"%s\"",actualTexture.getRequestedForm(), verb);
            questionLabel.setText(labelText);
        }
    }

    /**
     * Processing the answer is right
     */
    @Override
    public void processRightAnswer() {
        questionLabel.setText("Nice, well done!");
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                questionAnswered = true;
                questionLabel.setVisible(false);
            }
        }, 0.3f);
    }

    /**
     * gets called by stage when the game is over cause the lifebar reached 0
     * */
    public void gameEnd() {
        String labelText = "Game Over\nbetter luck next time";
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
