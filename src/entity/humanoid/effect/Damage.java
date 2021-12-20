package entity.humanoid.effect;

import core.Direction;
import entity.humanoid.Humanoid;
import state.State;

public class Damage extends Effect {

	public Damage(int lifeSpanInUpdates) {
		super(lifeSpanInUpdates);
		// TODO Auto-generated constructor stub
	}

	@Override
    public void update(State state, Humanoid humanoid) {
        super.update(state, humanoid);
        Direction direction = humanoid.getDirection();
        switch (direction) {
        
        case N:
        	break;
        case NE:
        	break;
        case E:
        	break;
        case SE:
        	break;
        case S:
        	break;
        case SW:
        	break;
        case W:
        	break;
        case NW:
        	break;
        default:
        	break;
        }
    }

}
