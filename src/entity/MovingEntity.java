package entity;

import java.awt.Image;

import controller.Controller;
import core.Direction;
import core.Movement;
import gfx.AnimationManager;
import gfx.SpriteLibrary;

public abstract class MovingEntity extends GameObject {
	
	private Controller controller;
	private Movement movement;
	private AnimationManager animationManager;
	private Direction direction;
	
	public MovingEntity(Controller controller, SpriteLibrary spriteLibrary) {
		super();
		this.controller = controller;
		this.movement = new Movement(4);
		this.direction = Direction.S;
		animationManager = new AnimationManager(spriteLibrary.getUnit("dave"));
	}
	
	@Override
	public void update() {
		movement.update(controller);
		position.apply(movement);
		manageDirection();
		animation();
		animationManager.update(direction);
	}
	
private void animation() {
		if(movement.isMoving()) {
			animationManager.playAnimation("walk");
		}
		else {
			animationManager.playAnimation("stand");
		}
		
	}

	public void manageDirection() {
		if(movement.isMoving()) {
			this.direction = Direction.fromMotion(movement);
		}
	}
	@Override
	public Image getSprite() {
		return animationManager.getSprite();
	}
}
