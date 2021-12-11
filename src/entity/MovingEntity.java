package entity;

import java.awt.Image;
import java.util.List;
import java.util.Optional;

import controller.EntityController;
import core.CollisionBox;
import core.Direction;
import core.Movement;
import core.Position;
import core.Size;
import core.Vector2D;
import entity.humanoid.action.Action;
import entity.humanoid.effect.Effect;
import gfx.AnimationManager;
import gfx.SpriteLibrary;
import state.State;

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
        this.animationManager = new AnimationManager(spriteLibrary.getSpriteSheet("matt"));
        this.collisionBoxSize = new Size(size.getWidth(), size.getHeight());
    }

    @Override
    public void update(State state) {
        movement.update(entityController);
        handleMovement();
        animationManager.update(direction);

        handleCollisions(state);
        manageDirection();
        animationManager.playAnimation(decideAnimation());
        position.apply(movement);

    }

    private void handleCollisions(State state) {
        state.getCollidingGameObjects(this).forEach(this::handleCollision);
    }

    protected abstract void handleCollision(GameObject other);

    protected abstract void handleMovement();

    protected abstract String decideAnimation();

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
    
    public boolean isFacing(Position other) {
    	Vector2D direction = Vector2D.directionBetweenPositions(other, getPosition());
    	double dotProduct = Vector2D.dotProduct(direction, directionVector);
    	
    	return dotProduct >0;
    }
}
