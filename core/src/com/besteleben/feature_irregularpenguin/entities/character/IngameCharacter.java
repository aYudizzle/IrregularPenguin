package com.besteleben.feature_irregularpenguin.entities.character;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * A Template for every character ingame
 * with a base set on important attributes.
 */
public abstract class IngameCharacter extends Actor {
    /**
     * the characters default X value when he is created
     */
    protected float defaultX;
    /**
     * the characters default Y value when he is created
     */
    protected float defaultY;
    /**
     * the characters currentTextureRegion
     */
    protected TextureRegion currentTextureRegion;
    /**
     * all textures for the idleanimation to cycle through
     */
    protected Animation<TextureRegion> idleAnimation;
    /**
     * elapsed time since the creation of the character, so it is possible to calculate the
     * currentTextureRegion.
     */
    protected float elapsedTime;
}
