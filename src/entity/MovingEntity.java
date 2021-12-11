package entity;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import controller.EntityController;
import core.CollisionBox;
import core.Direction;
import core.Movement;
import core.Position;
import core.Size;
import core.Vector2D;
import display.Camera;
import entity.action.Action;
import entity.effect.Effect;
import game.state.State;
import gfx.AnimationManager;
import gfx.SpriteLibrary;

public abstract class MovingEntity extends GameObject {

    protected EntityController entityController;
    protected Movement movement;
    protected AnimationManager animationManager;
    protected Direction direction;
    protected List<Effect> effects;
    protected Optional<Action> action;

    protected Vector2D directionVector;
    
    protected Size collisionBoxSize;

    public MovingEntity(EntityController entityController, SpriteLibrary spriteLibrary) {
        super();
        this.entityController = entityController;
        this.movement = new Movement(2);
        this.direction = Direction.S;
        this.directionVector = new Vector2D(0,0);
        this.animationManager = new AnimationManager(spriteLibrary.getUnit("matt"));
        effects = new ArrayList<>();
        action = Optional.empty();
        this.collisionBoxSize = new Size(16, 28);
        this.renderOffset = new Position(size.getWidth() / 2, size.getHeight() - 12);
        // half width to draw from middle of sprite
        this.collisionBoxOffset = new Position(collisionBoxSize.getWidth()/2, collisionBoxSize.getHeight());
    }

    @Override
    public void update(State state) {
        handleAction(state);
        handleMovement();
        animationManager.update(direction);
        effects.forEach(effect -> effect.update(state, this));

        handleCollisions(state);
        manageDirection();
        decideAnimation();

        position.apply(movement);

        cleanup();
    }

    private void handleCollisions(State state) {
        state.getCollidingGameObjects(this).forEach(this::handleCollision);
    }

    protected abstract void handleCollision(GameObject other);

    private void handleMovement() {
        if(!action.isPresent()) {
            movement.update(entityController);
        } else {
            movement.stop(true, true);
        }
    }

    private void handleAction(State state) {
        if(action.isPresent()) {
            action.get().update(state, this);
        }
    }

    private void cleanup() {
        List.copyOf(effects).stream()
                .filter(Effect::shouldDelete)
                .forEach(effects::remove);

        if(action.isPresent() && action.get().isDone()) {
            action = Optional.empty();
        }
    }

    private void decideAnimation() {
        if(action.isPresent()) {
            animationManager.playAnimation(action.get().getAnimationName());
        } else if(movement.isMoving()) {
            animationManager.playAnimation("walk");
        } else {
            animationManager.playAnimation("stand");
        }
    }

    private void manageDirection() {
        if(movement.isMoving()) {
            this.direction = Direction.fromMovement(movement);
            this.directionVector = movement.getDirection();
        }
    }

    @Override
    public CollisionBox getCollisionBox() {
        Position positionWithMovement = Position.copyOf(getPosition());
        positionWithMovement.apply(movement);
        positionWithMovement.subtract(collisionBoxOffset);
        return new CollisionBox(
            new Rect(
                (int)positionWithMovement.getX() ,
                (int)positionWithMovement.getY() ,
                collisionBoxSize.getWidth(),
                collisionBoxSize.getHeight()
            )
        );
    }

    @Override
    public Image getSprite() {
        return animationManager.getSprite();
    }

    public EntityController getEntityController() {
        return entityController;
    }

    public void perform(Action action) {
        this.action = Optional.of(action);
    }

    public void addEffect(Effect effect) {
        effects.add(effect);
    }

    protected void clearEffects() {
        effects.clear();
    }

    public boolean willCollideX(GameObject other) {
        CollisionBox otherBox = other.getCollisionBox();
        Position positionWithXApplied = Position.copyOf(position);
        positionWithXApplied.applyX(movement);
        positionWithXApplied.subtract(collisionBoxOffset);

        return CollisionBox.of(positionWithXApplied, collisionBoxSize).collidesWith(otherBox);
    }

    public boolean willCollideY(GameObject other) {
        CollisionBox otherBox = other.getCollisionBox();
        Position positionWithYApplied = Position.copyOf(position);
        positionWithYApplied.applyY(movement);
        positionWithYApplied.subtract(collisionBoxOffset);


        return CollisionBox.of(positionWithYApplied, collisionBoxSize).collidesWith(otherBox);
    }

    public boolean isAffectedBy(Class<?> clazz) {
        return effects.stream()
                .anyMatch(effect -> clazz.isInstance(effect));
    }
    
    public boolean isFacing(Position other) {
    	Vector2D direction = Vector2D.directionBetweenPositions(other, getPosition());
    	double dotProduct = Vector2D.dotProduct(direction, directionVector);
    	
    	return dotProduct >0;
    }
}
