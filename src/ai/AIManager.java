package ai;

import entity.NPC;
import game.state.State;

public class AIManager {

	private String nextState;
	private AICondition condition;
	public AIManager(String nextState, AICondition condition) {
		this.nextState = nextState;
		this.condition = condition;
	}
	public boolean shouldTransition(State state, NPC currentCharacter) {
		return condition.isMet(state, currentCharacter);
	}
	public String getNextState() {
		return nextState;
	}
	public void setNextState(String nextState) {
		this.nextState = nextState;
	}
	public AICondition getCondition() {
		return condition;
	}
	public void setCondition(AICondition condition) {
		this.condition = condition;
	}
	
	
}
