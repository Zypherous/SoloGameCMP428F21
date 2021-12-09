package entity;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import controller.Controller;
import core.CollisionBox;
import core.Direction;
import core.Movement;
import core.Position;
import core.Size;
import display.Camera;
import entity.action.Action;
import entity.effect.Effect;
import game.state.State;
import gfx.AnimationManager;
import gfx.SpriteLibrary;

public abstract class MovingEntity extends GameObject {
	
	protected Controller controller;
	protected Movement movement;
	protected AnimationManager animationManager;
	protected Direction direction;
	protected List<Effect> effects;
	protected Optional<Action> action;
	
	protected Size collisionBoxSize;
	protected CollisionBox collisionBox;
	
	
	public MovingEntity(Controller controller, SpriteLibrary spriteLibrary, Camera camera) {
		super(camera);
		this.controller = controller;
		this.movement = new Movement(4);
		this.direction = Direction.S;
		animationManager = new AnimationManager(spriteLibrary.getUnit("dave"));
		effects = new ArrayList<>();
		action = Optional.empty();
		this.collisionBoxSize = new Size(32,32);
		this.collisionBox = getCollider();
	}
	
	@Override
	public void update(State state) {
		handleAction(state);

		handleMovement();
		
		animationManager.update(direction);
		this.collisionBox = getCollider();
		effects.forEach(effect -> effect.update(state, this));
		this.collisionBox = getCurrentCollider();
		
		handleCollisions(state);
		manageDirection();
		animation();
		
		position.apply(movement);
		this.getRect().setX((int)this.getPosition().getX());
    	this.getRect().setY((int)this.getPosition().getY());
    	
    	cleanup();
	}
	
	private void handleCollisions(State state) {
		state.getCollidingGameObjects(this).forEach(this::handleCollision);
	}
	protected abstract void handleCollision(GameObject other);
	
	private void handleMovement() {
		if(!action.isPresent()) {
			movement.update(controller);
		}
		else {
			// To make it stop on both axis
			movement.stop(true,true);
		}
	}

	private void handleAction(State state) {
		if(action.isPresent()) {
			action.get().update(state, this);
		}
	}

	private void cleanup() {
        List.copyOf(effects).stream()
        		// Effect::shouldDelete - References the shouldDelete method in the EFFECTs class since we know the List
        		// Only contains effects. Then it goes through the original list to remove the effects. 
                .filter(Effect::shouldDelete)
                .forEach(effects::remove);
        if(action.isPresent() && action.get().isDone()) {
        	action = Optional.empty();
//        	System.out.println("MovingEntity.java Line 86 ==> ACTION DONE");
        }
    }

	private void animation() {
		if(action.isPresent()){
			animationManager.playAnimation(action.get().getAnimationName());
		}else if(movement.isMoving()) {
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
	public boolean collidesWith(GameObject other) {
		return getCollider().collidesWith(other.getCollider());
	}
	@Override
	public CollisionBox getCollider() {
		Position positionWithMovement = position.copyOf(position);
		positionWithMovement.apply(movement);
		return new CollisionBox(
				new Rect(
					(int)positionWithMovement.getX() - ((int)this.size.getWidth()/4) +4,
					(int)positionWithMovement.getY() - ((int)this.size.getHeight()/2) +4,
					collisionBoxSize.getWidth()-8,
					collisionBoxSize.getHeight() +16
						)
				);
	}
	public CollisionBox getCurrentCollider() {
		return this.collisionBox;
	}
	public CollisionBox getCollisionBox(CollisionBox collisionBox) {
		return collisionBox;
	}
	
	public void setCollisionBox(CollisionBox collisionBox) {
		this.collisionBox = collisionBox;
	}
	
	@Override
	public Image getSprite() {
		return animationManager.getSprite();
	}
	
	public Controller getController() {
		return this.controller;
	}
	
	public void multiplySpeed(double multiplier) {
        movement.multiply(multiplier);
    }
	public void perform(Action action) {
		this.action = Optional.of(action);
	}
	public void addEffect(Effect effect) {
		effects.add(effect);
	}
	public void clearEffect() {
		effects.clear();
	}
	public void setSize(Size size) {
		this.size.setSize(size.getWidth(), size.getHeight());
	}
	
	public boolean willCollideX(GameObject other) {
		CollisionBox otherBox = other.getCurrentCollider();
		Position positionWithXApplied = Position.copyOf(position);
		positionWithXApplied.applyX(movement);
		return CollisionBox.of(positionWithXApplied, collisionBoxSize).collidesWith(other.getCollider());
	}
	public boolean willCollideY(GameObject other) {
		CollisionBox otherBox = other.getCurrentCollider();
		Position positionWithYApplied = Position.copyOf(position);
		positionWithYApplied.applyY(movement);
		return CollisionBox.of(positionWithYApplied, collisionBoxSize).collidesWith(other.getCollider());
	}
}
