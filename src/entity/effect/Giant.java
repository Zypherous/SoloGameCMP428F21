
	package entity.effect;

	import core.Size;
import entity.MovingEntity;
import game.GameLoop;
import game.state.State;

	public class Giant extends Effect {
	    public Giant( ) {
	        super(GameLoop.UPDATES_PER_SECOND * 10);
	    }

	    @Override
	    public void update(State state, MovingEntity entity) {
	    	System.out.println(String.format("Giant.java Line 20 ==> Updates Alive: %d", this.getLifeSpanInUpdates()));
	    	super.update(state, entity);
	        if(this.getLifeSpanInUpdates() == 0) {
	        	entity.setSize(new Size(64, 64));
	        }
	        else if(this.getLifeSpanInUpdates() <= 15) {
	        	entity.setSize(new Size(72,72));
	        }
	        else if(this.getLifeSpanInUpdates() <= 30) {
	        	entity.setSize(new Size(84,84));
	        }
	        else if(this.getLifeSpanInUpdates() <= 45) {
	        	entity.setSize(new Size(128,128));
	        }
	        else if(this.getLifeSpanInUpdates() <= 60) {
	        	entity.setSize(new Size(176,176));
	        }
	        else if(this.getLifeSpanInUpdates() <= 75) {
	        	entity.setSize(new Size(200,200));
	        }
	        else if(this.getLifeSpanInUpdates() <= 90) {
	        	entity.setSize(new Size(220,220));
	        }
	        else {
	        	entity.setSize(new Size(256,256));
	        }
	    }
	}

