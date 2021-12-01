package gfx;

import game.Game;

import java.awt.image.BufferedImage;
import java.awt.*;


public class AnimationManager {
	private SpriteSheet spriteSheet;
	private BufferedImage currentAnimationSheet;
	private int currentFrameTime;
	private int updatesPerFrame;
	private int frameIndex;
	
	public AnimationManager(SpriteSheet spriteSheet) {
		this.spriteSheet = spriteSheet;
		this.updatesPerFrame = 20;
		this.frameIndex = 0;
		this.currentFrameTime = 0;
		
		playAnimation("stand");
	}
	
	public Image getSprite() {
		return currentAnimationSheet.getSubimage(
					frameIndex * Game.SPRITE_SIZE,
					0,
					Game.SPRITE_SIZE,
					Game.SPRITE_SIZE
				);
			
		
	}
	
	public void update() {
		currentFrameTime++;
		if(currentFrameTime >= updatesPerFrame) {
			currentFrameTime = 0;
			frameIndex++;
			
			if(frameIndex >= currentAnimationSheet.getWidth()/ Game.SPRITE_SIZE) {
				frameIndex = 0;
			}
		}
	}
	
	public void playAnimation (String name) {
		this.currentAnimationSheet = (BufferedImage) spriteSheet.get(name);
	}
}
