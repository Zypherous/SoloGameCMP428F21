
	package entity.effect;

	import core.CollisionBox;
import core.Size;
import entity.MovingEntity;
import entity.Rect;
import game.GameLoop;
import game.state.State;

	public class Giant extends Effect {
	    public Giant( ) {
	        super(GameLoop.UPDATES_PER_SECOND * 10);
	    }

	    @Override
	    public void update(State state, MovingEntity entity) {
//	    	System.out.println(String.format("Giant.java Line 20 ==> Updates Alive: %d", this.getLifeSpanInUpdates()));
	    	super.update(state, entity);
	        if(this.getLifeSpanInUpdates() == 0) {
	        	entity.setSize(new Size(64, 64));
	        	
	        	// Attempting to have collider grow and shrink with effect.
	        	entity.setCollisionBox(new CollisionBox(
	    				new Rect(
	    						(int)(entity.getPosition().getX() - (entity.getSize().getWidth()/4) +4) ,
	    						(int)(entity.getPosition().getY() - (entity.getSize().getHeight()/2) +4) ,
						32-8,
						32 +16
						)));
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
	        	
	        	// Attempting to have collider grow and shrink with effect.
	        	entity.setCollisionBox(new CollisionBox( 
	        			new Rect(
		        			(int)entity.getPosition().getX() - ((entity.getSize().getWidth()/4)) + 16,
							(int)entity.getPosition().getY() - ((entity.getSize().getHeight())/2) + 16 ,
							entity.getSize().getWidth()/2 -32, 
							entity.getSize().getHeight()/2 + (16*4))
	        			)
	        		);
	        }
	    }
	}

