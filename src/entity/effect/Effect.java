package entity.effect;

import entity.MovingEntity;
import game.state.State;
	
public abstract class Effect {
    private int lifeSpanInUpdates;

    public Effect(int lifeSpanInUpdates) {
        this.lifeSpanInUpdates = lifeSpanInUpdates;
    }

    public void update(State state, MovingEntity entity) {
        lifeSpanInUpdates--;
//        System.out.println(String.format("Effect.java Line 15 ==> Updates Alive: %d", this.getLifeSpanInUpdates()));
    }

    public boolean shouldDelete() {
        return lifeSpanInUpdates <= 0;
    }
    public int getLifeSpanInUpdates() {
    	return this.lifeSpanInUpdates;
    }
}

