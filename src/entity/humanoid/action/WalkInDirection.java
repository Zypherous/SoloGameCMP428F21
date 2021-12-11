package entity.humanoid.action;

import core.Movement;
import core.Vector2D;
import entity.humanoid.Humanoid;
import game.GameLoop;
import state.State;

public class WalkInDirection extends Action {

    private int walkTime;
    private Movement movement;

    public WalkInDirection(Vector2D direction) {
        walkTime = GameLoop.UPDATES_PER_SECOND * 3;
        movement = new Movement(1);
        movement.add(direction);
    }

    @Override
    public void update(State state, Humanoid humanoid) {
        walkTime--;

        humanoid.apply(movement);
    }

    @Override
    public boolean isDone() {
        return walkTime == 0;
    }

    @Override
    public String getAnimationName() {
        return "walk";
    }

    @Override
    public String getSoundName() {
        return null;
    }
}
