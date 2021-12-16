package entity.humanoid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import controller.EntityController;
import core.CollisionBox;
import core.Position;
import core.Size;
import entity.GameObject;
import entity.MovingEntity;
import entity.humanoid.action.Action;
import entity.humanoid.effect.Effect;
import gfx.AnimationManager;
import gfx.SpriteLibrary;
import state.State;

public abstract class Humanoid extends MovingEntity {
    protected List<Effect> effects;
    private static List<String> availableCharacters = new ArrayList<>(List.of( "matt", "melissa", "roger"));
    protected Optional<Action> action;

    public Humanoid(EntityController entityController, SpriteLibrary spriteLibrary) {
        super(entityController, spriteLibrary);

        effects = new ArrayList<>();
        action = Optional.empty();
        
        this.animationManager = new AnimationManager(spriteLibrary.getSpriteSheet(getRandomCharacter()));


        this.collisionBoxSize = new Size(16, 28);
        this.renderOffset = new Position(size.getWidth() / 2, size.getHeight() - 12);
        this.collisionBoxOffset = new Position(collisionBoxSize.getWidth() / 2, collisionBoxSize.getHeight());
    }

    @Override
    public void update(State state) {
        super.update(state);
        handleAction(state);
        effects.forEach(effect -> effect.update(state, this));

        cleanup();
    }

    private void cleanup() {
        List.copyOf(effects).stream()
                .filter(Effect::shouldDelete)
                .forEach(effects::remove);

        if(action.isPresent() && action.get().isDone()) {
            action = Optional.empty();
        }
    }

    @Override
    protected String decideAnimation() {
        if(action.isPresent()) {
            return action.get().getAnimationName();
        } else if(movement.isMoving()) {
            return "walk";
        }

        return "stand";
    }

    private void handleAction(State state) {
    	action.ifPresent(value -> {
            value.update(state, this);
            value.playSound(state.getAudioPlayer());
        });
    }

    protected void handleMovement() {
        if(action.isPresent()) {
            movement.stop(true, true);
        }
    }

    public void perform(Action action) {
    	if(this.action.isPresent() && !this.action.get().isInterruptable()) {
    		return;
    	}
        this.action = Optional.of(action);
    }

    public void addEffect(Effect effect) {
        effects.add(effect);
    }

    protected void clearEffects() {
        effects.clear();
    }

    public boolean isAffectedBy(Class<?> clazz) {
        return effects.stream()
                .anyMatch(effect -> clazz.isInstance(effect));
    }

    @Override
    protected void handleCollision(GameObject other) {}

	public List<Effect> getEffects() {
		return effects;
	}
	
	private String getRandomCharacter() {
        Collections.shuffle(availableCharacters);
        return availableCharacters.get(0);
    }
	
	public void setRenderOffset() {
		 this.renderOffset = new Position(size.getWidth() / 2, size.getHeight() - 12);
		 this.collisionBoxSize = new Size((int)(size.getWidth() * .25), (int)(size.getHeight() *.45));
		 this.collisionBoxOffset = new Position(collisionBoxSize.getWidth() / 2, collisionBoxSize.getHeight());
		 
	}

	@Override
	protected void handleTileCollision(CollisionBox collisionBox) {
		movement.stop(willCollideX(collisionBox), willCollideY(collisionBox));
	}

	@Override
	protected void handleCollisions(State state) {
		
		
	}
}
