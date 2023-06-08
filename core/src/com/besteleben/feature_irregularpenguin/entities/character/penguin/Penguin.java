package com.besteleben.feature_irregularpenguin.entities.character.penguin;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.besteleben.feature_irregularpenguin.entities.character.IngameCharacter;

/**
 * Penguin represents the player chracter which extends the IngameCharacter class
 */
public class Penguin extends IngameCharacter {
    /**
     * TextureRegion for the running animation
     */
    private Animation<TextureRegion> runAnimation;
    /**
     * Texture Region for the hurtAnimation which get Displayed when the game is over
     */
    private Animation<TextureRegion> hurtAnimation;
    /**
     * if the question got answered
     */
    private boolean questionAnswered = false;
    /**
     * if the player is defeated
     */
    private boolean playerDefeated = false;

    /**
     * Penguin constructor with the duration for everysingle frame to cycle through his animations
     * @param frameDuration how long should a frame be displayed
     * @param defaultX  base X-Coordinate
     * @param defaultY base Y-Coordinate
     */
    public Penguin(float frameDuration, float defaultX, float defaultY){
        this.defaultX = defaultX;
        this.defaultY = defaultY;
        currentTextureRegion = new TextureRegion();
        setY(this.defaultY);
        setX(this.defaultX);
        setScale(2);

        buildAnimationTextures(frameDuration);
    }

    /**
     * build the animation for idlAnimation, runAnimation and hurtAnimation
     * just created a helper method to keep the constructor cleaner.
     * @param frameDuration duration for every single frame
     */
    private void buildAnimationTextures(float frameDuration) {
        TextureAtlas textureAtlas = new TextureAtlas("penguin.atlas");
        String runRegionName = "walk"; // tag for run animation in penguin.atlas
        String idleRegionName = "Idle"; // tag for idle animation in penguin.atlas
        Array<TextureAtlas.AtlasRegion> runFrames = textureAtlas.findRegions(runRegionName);
        Array<TextureAtlas.AtlasRegion> idleFrames = textureAtlas.findRegions(idleRegionName);
        Array<TextureAtlas.AtlasRegion> hurtFrames = textureAtlas.findRegions("hurt");

        runAnimation = new Animation<>(frameDuration,runFrames, Animation.PlayMode.LOOP);
        idleAnimation = new Animation<>(frameDuration,idleFrames, Animation.PlayMode.LOOP);
        hurtAnimation = new Animation<>(frameDuration,hurtFrames,Animation.PlayMode.LOOP);

        setSize(runFrames.first().getRegionWidth(),runFrames.first().getRegionHeight());
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
        elapsedTime += delta;
        updateAppearance();
    }

    /**
     * draws the penguin and his texture to the batch
     * @param batch the batch to draw the texture
     * @param parentAlpha The parent alpha, to be multiplied with this actor's alpha, allowing the parent's alpha to affect all
     *           children.
     */
    public void draw(Batch batch, float parentAlpha) {
        if (currentTextureRegion != null) {
            batch.draw(currentTextureRegion, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
        }
    }

    /**
     * Updates the appearance of the penguin on certain states
     */
    public void updateAppearance() {
        if (!questionAnswered) {
            if (!hasReachedCenter()) {
                useRunningAnimation();
            } else {
                if (playerDefeated) {
                    useHurtAnimation();
                } else {
                    useIdleAnimation();
                }
            }
        } else {
            useRunningAnimation();
            }
        }

    /**
     * change the currentTexture to the hurtAnimation texture
     */
    private void useHurtAnimation() {
        TextureRegion currentFrame;
        currentFrame = hurtAnimation.getKeyFrame(elapsedTime);
        setSize(currentFrame.getRegionWidth(),currentFrame.getRegionHeight());
        currentTextureRegion.setRegion(currentFrame);
    }

    /**
     * change the currentTexture to the idle animation
     */
    public void useIdleAnimation(){
        TextureRegion currentFrame;
        currentFrame = idleAnimation.getKeyFrame(elapsedTime);
        setSize(currentFrame.getRegionWidth(),currentFrame.getRegionHeight());
        currentTextureRegion.setRegion(currentFrame);
    }

    /**
     * change the currentTexture to the running Animation.
     */
    public void useRunningAnimation(){
        TextureRegion currentFrame;
        currentFrame = runAnimation.getKeyFrame(elapsedTime);
        setSize(currentFrame.getRegionWidth(),currentFrame.getRegionHeight());
        currentTextureRegion.setRegion(currentFrame);
        moveBy(4,0);
    }

    /**
     * checks if the penguin has reached the center of the screen with a little offset
     * @return true or false depending on the position of the character
     */
    public boolean hasReachedCenter() {
        float centerX = getX() + getWidth() / 2;
        float stageCenterX = getStage().getWidth() / 2 - (4.9f* currentTextureRegion.getRegionWidth()); // für eine gewisse verschiebung nach links
        return centerX == stageCenterX;
    }
    /**
     * resets the penguin to the default parameters
     */
    public void reset() {
        setX(defaultX);
        questionAnswered = false;
    }
    /**
     * Gets answered.
     *
     * @return value of answered
     */
    public boolean isQuestionAnswered() {
        return questionAnswered;
    }

    /**
     * Sets answered.
     *
     * @param questionAnswered value of answered
     */
    public void setQuestionAnswered(boolean questionAnswered) {
        this.questionAnswered = questionAnswered;
    }

    /**
     * Gets defeated.
     *
     * @return value of defeated
     */
    public boolean isPlayerDefeated() {
        return playerDefeated;
    }

    /**
     * Sets defeated.
     *
     * @param playerDefeated value of defeated
     */
    public void setPlayerDefeated(boolean playerDefeated) {
        this.playerDefeated = playerDefeated;
    }

}

