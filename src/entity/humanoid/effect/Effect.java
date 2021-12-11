package entity.humanoid.effect;

import entity.humanoid.Humanoid;
import game.state.State;
	
public abstract class Effect {
    private int lifeSpanInUpdates;

    public Effect(int lifeSpanInUpdates) {
        this.lifeSpanInUpdates = lifeSpanInUpdates;
    }

    public void update(State state, Humanoid humanoid) {
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

