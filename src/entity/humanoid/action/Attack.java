package entity.humanoid.action;


	import controller.NPCController;
import entity.Bubble;
import entity.Claw;
import entity.humanoid.Humanoid;
import entity.humanoid.effect.Isolated;
import game.GameLoop;
import state.State;

public class Attack extends Action {

  
	private int lifeSpanInUpdates;
    private Humanoid target;
    private Claw claw;

    public Attack(Humanoid target) {
        lifeSpanInUpdates = GameLoop.UPDATES_PER_SECOND ;
        this.target = target;
        interruptable = true;
    }

    @Override
    public void update(State state, Humanoid humanoid) {
        lifeSpanInUpdates--;

        if(claw == null) {
            attackTarget(state);
        }
        else {
        	claw.setHalted(true);
        }

        if(isDone()) {
            state.despawn(claw);
        }
    }

    private void attackTarget(State state) {

        claw = new Claw(new NPCController(), state.getSpriteLibrary());
        claw.insert(target);
        state.spawn(claw);
    }

    @Override
    public boolean isDone() {
        return lifeSpanInUpdates == 0;
    }

    @Override
    public String getAnimationName() {
        return "attack";
    }
    
    @Override
  	public String getSoundName() {
  		return "claw.wav";
  	}

}


