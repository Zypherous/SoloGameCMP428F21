package ai.state;

import java.util.ArrayList;
import java.util.List;

import ai.AITransition;
import controller.NPCController;
import core.Position;
import entity.NPC;
import game.state.State;

public class Wander  extends AIState {
    private List<Position> targets;
    
	private int updatesAlive;

	public Wander() {
		super();
		targets = new ArrayList<>();
	}
    @Override
    protected AITransition initializeTransition() {
        return new AITransition("stand", ((state, currentCharacter) -> arrived(currentCharacter)));
    }

    @Override
    public void update(State state, NPC currentCharacter) {
        if(targets.isEmpty()) {
        	targets.add(state.getRandomPosition());
        }
        NPCController controller = (NPCController) currentCharacter.getController();
        controller.moveToTarget(targets.get(0), currentCharacter.getPosition());
        
        if(arrived(currentCharacter)) {
        	controller.stop();
        }
    }
    
    private boolean arrived(NPC currentCharacter) {
    	return currentCharacter.getPosition().isInRangeOf(targets.get(0));
    }
}
