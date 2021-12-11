package entity.humanoid.effect;

import entity.humanoid.Humanoid;
import game.GameLoop;
import state.State;

public class Caffeinated extends Effect {

    private double speedMultiplier;

    public Caffeinated() {
        super(GameLoop.UPDATES_PER_SECOND * 5);
        speedMultiplier = 2.5;
    }

    @Override
    public void update(State state, Humanoid humanoid) {
        super.update(state, humanoid);

//        entity.multiplySpeed(speedMultiplier);
    }
}
