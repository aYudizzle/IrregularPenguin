package com.besteleben.irregularpenguin.entities.character;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class IngameCharacter extends Actor {
    protected float defaultX;
    protected float defaultY;
    protected TextureRegion currentRegion;
    protected Animation<TextureRegion> idleAnimation;
    protected float elapsedTime;
}
