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
    

    public MovingEntity(EntityController entityController, SpriteLibrary spriteLibrary) {
        super();
        this.entityController = entityController;
        this.movement = new Movement(2);
        this.direction = Direction.S;
        this.directionVector = new Vector2D(0,0);
        this.animationManager = new AnimationManager(spriteLibrary.getSpriteSheet("matt"), 20);
    }

    @Override
    public void update(State state) {
        movement.update(entityController);
        handleMovement();
        animationManager.update(direction);

        handleCollisions(state);
        handleTileCollisions(state);
        handleSceneryCollisions(state);
        animationManager.playAnimation(decideAnimation());

        apply(movement);
    }

    protected void handleTileCollisions(State state) {
    	state.getGameMap().getCollidingUnwalkableTileBoxes(getCollisionBox())
        .forEach(this::handleTileCollision);
    }
    protected void handleSceneryCollisions(State state) {
    	state.getGameMap().getSceneryList().forEach(scenery -> this.handleCollision(scenery));
    }

    protected abstract void handleTileCollision(CollisionBox collisionBox);
    
	protected abstract void handleCollisions(State state);

    protected abstract void handleCollision(GameObject other);

    protected abstract void handleMovement();

    protected abstract String decideAnimation();

    private void manageDirection(Movement movement) {
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

    public boolean willCollideX(CollisionBox otherBox) {
        Position positionWithXApplied = Position.copyOf(position);
        positionWithXApplied.applyX(movement);
        positionWithXApplied.subtract(collisionBoxOffset);

        return CollisionBox.of(positionWithXApplied, collisionBoxSize).collidesWith(otherBox);
    }

    public boolean willCollideY(CollisionBox otherBox) {
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
    
    public void apply(Movement movement) {
        manageDirection(movement);
        position.apply(movement);
    }
    
    public void multiplySpeed(double speed) {
    	movement.multiply(speed);
    	apply(movement);
    }
}
