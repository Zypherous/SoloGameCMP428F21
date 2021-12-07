package entity;

import java.awt.Image;

import controller.Controller;
import core.Direction;
import core.Movement;
import display.Camera;
import game.state.State;
import gfx.AnimationManager;
import gfx.SpriteLibrary;

public abstract class MovingEntity extends GameObject {
	
	protected Controller controller;
	protected Movement movement;
	protected AnimationManager animationManager;
	protected Direction direction;
	
	public MovingEntity(Controller controller, SpriteLibrary spriteLibrary, Camera camera) {
		super(camera);
		this.controller = controller;
		this.movement = new Movement(4);
		this.direction = Direction.S;
		animationManager = new AnimationManager(spriteLibrary.getUnit("dave"));
	}
	
	@Override
	public void update(State state) {
		movement.update(controller);
		position.apply(movement);
		manageDirection();
		animation();
		animationManager.update(direction);
		this.getRect().setX((int)this.getPosition().getX());
    	this.getRect().setY((int)this.getPosition().getY());
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
	
	public Controller getController() {
		return this.controller;
	}
}
