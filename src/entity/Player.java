package entity;

import java.util.Comparator;
import java.util.Optional;

import controller.EntityController;
import core.Position;
import core.Size;
import entity.effect.Caffeinated;
import entity.effect.Giant;
import game.Game;
import game.state.State;
import gfx.AnimationManager;
import gfx.SpriteLibrary;




public class Player extends MovingEntity{

	private NPC target;
	private double targetRange;
	private SelectionCircle selectionCircle;
	
	public Player(EntityController controller, SpriteLibrary spriteLibrary, Size size, SelectionCircle selectionCirlce) {
		super(controller, spriteLibrary);
		this.size = size;
		this.setPosition(new Position(1280/2,960/2));
		this.selectionCircle = selectionCirlce;
		animationManager = new AnimationManager(spriteLibrary.getUnit("dave"));
		
		this.targetRange = Game.SPRITE_SIZE;
		//TESTING RECT
//		this.setRect(new Rect((int)this.position.getX(), (int) this.position.getY(), this.size.getWidth(), this.getSize().getHeight(),0,0, camera));
		effects.add(new Caffeinated());
		effects.add(new Giant());
	}
	@Override
	public void update(State state) {
		super.update(state);
		handleTarget(state);
	}

	private void handleTarget(State state) {
        Optional<NPC> closestNPC = findClosestNPC(state);

        if(closestNPC.isPresent()) {
            NPC npc = closestNPC.get();
            if(!npc.equals(target)) {
                selectionCircle.setParent(npc);
                target = npc;
            }
        } else {
            selectionCircle.clearParent();
            target = null;
        }
    }
	private Optional<NPC> findClosestNPC(State state) {
        return state.getGameObjectsOfClass(NPC.class).stream()
                .filter(npc -> getPosition().distanceTo(npc.getPosition()) < targetRange)
                .filter(npc -> isFacing(npc.getPosition()))
                .min(Comparator.comparingDouble(npc -> position.distanceTo(npc.getPosition())));
    }
	public void playerLoc() {
		System.out.println(String.format(
				"Player X: %d  Y: %d",
				(int)this.getPosition().getX(),
				(int)this.getPosition().getY()
				));
	}

	@Override
	public void handleCollision(GameObject other) {
		if(other instanceof NPC) {
//			this.movement.stop();
			NPC npc = (NPC) other;
			npc.clearEffects();
		}
		
	}	
	
//	@Override
//	public Image getSprite() {
//		return animationManager.getSprite(size);
//	}

}
