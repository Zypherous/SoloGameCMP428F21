package gfx;

import game.Game;

import java.awt.image.BufferedImage;

import core.Direction;

import java.awt.*;


public class AnimationManager {
	private SpriteSheet spriteSheet;
	private String currentAnimationName;
	private BufferedImage currentAnimationSheet;
	private int currentFrameTime;
	private int updatesPerFrame;
	private int frameIndex;
	private int directionIndex;
	
	public AnimationManager(SpriteSheet spriteSheet) {
		this.spriteSheet = spriteSheet;
		this.updatesPerFrame = 10;
		this.frameIndex = 0;
		this.currentFrameTime = 0;
		this.directionIndex = 0;
		this.currentAnimationName = "";
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
			
			if(frameIndex >= currentAnimationSheet.getWidth()/ Game.SPRITE_SIZE) {
				frameIndex = 0;
			}
		}
	}
	
	public void playAnimation (String name) {
		if(!name.equals(currentAnimationName)) {
			this.currentAnimationSheet = (BufferedImage) spriteSheet.get(name);
			currentAnimationName = name;
			frameIndex = 0;
			
		}
	}
}
