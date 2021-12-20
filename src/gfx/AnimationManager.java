package gfx;

import core.Direction;
import game.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AnimationManager {
    private SpriteSheet spriteSheet;
    private String currentAnimationName;
    private BufferedImage currentAnimationSheet;
    private int updatesPerFrame;
    private int currentFrameTime;
    private int frameIndex;
    private int directionIndex;
    private boolean looping;

    public AnimationManager(SpriteSheet spriteSheet, int updatesPerFrame) {
        this(spriteSheet, true, updatesPerFrame);
    }

    public AnimationManager(SpriteSheet spriteSheet, boolean looping, int updatesPerFrame) {
        this.spriteSheet = spriteSheet;
        this.updatesPerFrame = updatesPerFrame;
        this.frameIndex = 0;
        this.currentFrameTime = 0;
        this.directionIndex = 0;
        this.looping = looping;
        currentAnimationName = "";
        playAnimation("stand");
    }

    public Image getSprite() {
        return currentAnimationSheet.getSubimage(
                frameIndex * Game.SPRITE_SIZE,
                directionIndex * Game.SPRITE_SIZE,
                Game.SPRITE_SIZE,
                Game.SPRITE_SIZE
        );
    }

    public void update(Direction direction) {
        currentFrameTime++;
        directionIndex = direction.getAnimationRow();

        if(currentFrameTime >= updatesPerFrame) {
            currentFrameTime = 0;
            frameIndex++;

            int animationSize = currentAnimationSheet.getWidth() / Game.SPRITE_SIZE;
            if(frameIndex >= animationSize) {
                frameIndex = looping ? 0 : animationSize - 1;
            }
        }
    }

    public void playAnimation(String name) {
        if(!name.equals(currentAnimationName)) {
            this.currentAnimationSheet = (BufferedImage) spriteSheet.getOrGetDefault(name);
            currentAnimationName = name;
            frameIndex = 0;
        }
    }
}
