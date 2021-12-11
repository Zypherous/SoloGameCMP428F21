
	package entity.humanoid.effect;

	import core.Size;
import entity.humanoid.Humanoid;
import game.GameLoop;
import state.State;

	public class Giant extends Effect {
	    public Giant( ) {
	        super(GameLoop.UPDATES_PER_SECOND * 10);
	    }

	    @Override
	    public void update(State state, Humanoid humanoid) {
//	    	System.out.println(String.format("Giant.java Line 20 ==> Updates Alive: %d", this.getLifeSpanInUpdates()));
	    	super.update(state, humanoid);
	        if(this.getLifeSpanInUpdates() == 0) {
	        	humanoid.setSize(new Size(64, 64));
	        	humanoid.setRenderOffset();
//	        	
//	        	// Attempting to have collider grow and shrink with effect.
//	        	entity.setCollisionBox(1);
	        }
	        else if(this.getLifeSpanInUpdates() <= 15) {
	        	humanoid.setSize(new Size(72,72));
	        	humanoid.setRenderOffset();
	        }
	        else if(this.getLifeSpanInUpdates() <= 30) {
	        	humanoid.setSize(new Size(84,84));
	        	humanoid.setRenderOffset();
	        }
	        else if(this.getLifeSpanInUpdates() <= 45) {
	        	humanoid.setSize(new Size(128,128));
	        	humanoid.setRenderOffset();
	        }
	        else if(this.getLifeSpanInUpdates() <= 60) {
	        	humanoid.setSize(new Size(176,176));
	        	humanoid.setRenderOffset();
	        }
	        else if(this.getLifeSpanInUpdates() <= 75) {
	        	humanoid.setSize(new Size(200,200));
	        	humanoid.setRenderOffset();
	        }
	        else if(this.getLifeSpanInUpdates() <= 90) {
	        	humanoid.setSize(new Size(220,220));
	        	humanoid.setRenderOffset();
	        }
	        else {
	        	humanoid.setSize(new Size(256,256));
	        	humanoid.setRenderOffset();
//	        	// Attempting to have collider grow and shrink with effect.
//	        	entity.setCollisionBox(4);
	        }
	    }
	}

