package ai.state;

import ai.AITransition;
import entity.NPC;
import game.state.State;

public abstract class AIState {
	private AITransition transition;

	public AIState(AITransition transition) {
		this.transition = initializeTransition();
	}

	protected abstract AITransition initializeTransition();
	protected abstract void update(State state, NPC CurrentCharacter);
	//Wrapper methods for transition class
	public boolean shouldTransition(State state, NPC CurrentCharacter) {
		return transition.shouldTransition(state, CurrentCharacter);
	}
	
	public String getNextState() {
		return transition.getNextState();
	}
}
