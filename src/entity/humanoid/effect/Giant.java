
	package entity.humanoid.effect;

	import core.Size;
import entity.humanoid.Humanoid;
import game.GameLoop;
import state.State;

	public class Giant extends Effect {
	    public Giant( ) {
	        super(GameLoop.UPDATES_PER_SECOND * 1);
	    }

	    @Override
	    public void update(State state, Humanoid humanoid) {
//	    	System.out.println(String.format("Giant.java Line 20 ==> Updates Alive: %d", this.getLifeSpanInUpdates()));
	    	super.update(state, humanoid);
	        if(this.getLifeSpanInUpdates() == 0) {
	        	humanoid.setSize(new Size(64, 64));
//	        	
//	        	// Attempting to have collider grow and shrink with effect.
//	        	entity.setCollisionBox(1);
	        }
	        else if(this.getLifeSpanInUpdates() <= 15) {
	        	humanoid.setSize(new Size(72,72));
	        }
	        else if(this.getLifeSpanInUpdates() <= 30) {
	        	humanoid.setSize(new Size(84,84));
	        }
	        else if(this.getLifeSpanInUpdates() <= 45) {
	        	humanoid.setSize(new Size(128,128));
	        }
	        else if(this.getLifeSpanInUpdates() <= 60) {
	        	humanoid.setSize(new Size(176,176));
	        }
	        else if(this.getLifeSpanInUpdates() <= 75) {
	        	humanoid.setSize(new Size(200,200));
	        }
	        else if(this.getLifeSpanInUpdates() <= 90) {
	        	humanoid.setSize(new Size(220,220));
	        }
	        else {
	        	humanoid.setSize(new Size(256,256));
//	        	
//	        	// Attempting to have collider grow and shrink with effect.
//	        	entity.setCollisionBox(4);
	        }
	    }
	}

