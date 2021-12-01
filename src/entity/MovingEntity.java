package entity;

import java.awt.Image;

import controller.Controller;
import core.Movement;
import gfx.AnimationManager;
import gfx.SpriteLibrary;

public abstract class MovingEntity extends GameObject {
	
	private Controller controller;
	private Movement movement;
	private AnimationManager animationManager;
	
	public MovingEntity(Controller controller, SpriteLibrary spriteLibrary) {
		super();
		this.controller = controller;
		this.movement = new Movement(2);
		
		animationManager = new AnimationManager(spriteLibrary.getUnit("dave"));
	}
	
	@Override
	public void update() {
		movement.update(controller);
		position.apply(movement);
		animationManager.update();
	}
	
	@Override
	public Image getSprite() {
		return animationManager.getSprite();
	}
}
