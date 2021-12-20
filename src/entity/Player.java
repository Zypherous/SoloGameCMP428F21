package entity;

import java.util.Comparator;
import java.util.Optional;

import controller.EntityController;
import core.Position;
import core.Size;
import entity.humanoid.Humanoid;
import entity.humanoid.action.Attack;
import entity.humanoid.effect.Caffeinated;
import entity.humanoid.effect.Giant;
import entity.humanoid.effect.Isolated;
import game.Game;
import gfx.AnimationManager;
import gfx.SpriteLibrary;
import state.State;
import state.game.GameState;




public class Player extends Humanoid{

	private NPC target;
	private double targetRange;
	private SelectionCircle selectionCircle;
	private GameState state;
	private boolean transition;
	
	public Player(EntityController controller, SpriteLibrary spriteLibrary, Size size, GameState state) {
		super(controller, spriteLibrary);
		this.size = size;
		this.selectionCircle = new SelectionCircle();
		this.state = state;
		this.transition = false;
		this.setPosition(new Position(1280/2,960 - 3*Game.SPRITE_SIZE));
//		perform(new WalkInDirection(new Vector2D(0, -1)));
		animationManager = new AnimationManager(spriteLibrary.getSpriteSheet("molly"),20);
		
		this.targetRange = Game.SPRITE_SIZE;
		//TESTING RECT
//		this.setRect(new Rect((int)this.position.getX(), (int) this.position.getY(), this.size.getWidth(), this.getSize().getHeight(),0,0, camera));
//		effects.add(new Caffeinated());
//		effects.add(new Giant());
	}
	@Override
	public void update(State state) {
		super.update(state);
		handleTarget(state);
		handleInput(state);
	}

	private void handleInput(State state) {
		if(entityController.isRequestingAction()) {
			if(target != null) {
				this.perform(new Attack(target));
			}
		}
	}
	private void handleTarget(State state) {
        Optional<NPC> closestNPC = findClosestNPC(state);

        if(closestNPC.isPresent()) {
            NPC npc = closestNPC.get();
            if(!npc.equals(target)) {
                if(target != null) {
                    target.detach(selectionCircle);
                }
                npc.attach(selectionCircle);
                target = npc;
            }
        } else {
            if(target != null) {
                target.detach(selectionCircle);
                target = null;
            }
        }
    }
	private Optional<NPC> findClosestNPC(State state) {
        return state.getGameObjectsOfClass(NPC.class).stream()
                .filter(npc -> getPosition().distanceTo(npc.getPosition()) < targetRange)
                .filter(npc -> isFacing(npc.getPosition()))
                .filter(npc -> !npc.isAffectedBy(Isolated.class))
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
		if(other instanceof Scenery && !((Scenery)other).isWalkable()) {
//			System.out.println(String.format("Line 97 : Player  COLLIDING WITH INVISIBLE atX:%d|Y:%d", other.getPosition().intX(), other.getPosition().intY()));
            movement.stop(willCollideX(other.getCollisionBox()), willCollideY(other.getCollisionBox()));
//            if(other.getCollisionBox().getBounds().overlaps(this.getCollisionBox().getBounds())) {
//            	this.apply(other.getCollisionBox().getBounds().pushBack(this.getCollisionBox().getBounds()));
//            }
        }
		if(other instanceof Scenery && ((Scenery)other).isWalkable() && ((Scenery)other).collidesWith(this)) {
			System.out.println(String.format("Line 104 : Player  COLLIDING WITH INVISIBLE atX:%d|Y:%d", other.getPosition().intX(), other.getPosition().intY()));
			if(this.position.intX() < 150 && !this.transition) {
				this.transition = true;
				setPosition(new Position(18*Game.SPRITE_SIZE, state.getWindowSize().getHeight()/2));
				state.setCurrentRoomX(state.getCurrentRoomX()-1);
			}
			if(this.position.intX() > 1000 && !this.transition) {
				this.transition = true;
				setPosition(new Position(2*Game.SPRITE_SIZE, state.getWindowSize().getHeight()/2));
				state.setCurrentRoomX(state.getCurrentRoomX()+1);
			}
			if(this.position.intY() < 100 && !this.transition) {
				this.transition = true;
				setPosition(new Position(state.getWindowSize().getWidth()/2,13*Game.SPRITE_SIZE ));
				state.setCurrentRoomY(state.getCurrentRoomY() +1);
				
			}
				
			if(this.position.intY() > 800 && !this.transition) {
				this.transition = true;
				System.out.println("ffffffffffff");
				setPosition(new Position(state.getWindowSize().getWidth()/2, 4*Game.SPRITE_SIZE ));
				state.setCurrentRoomY(state.getCurrentRoomY()-1);
				
			}
			if(state.getCurrentRoomY() == 4) {
				state.getAudioPlayer().clear();
	        	state.getAudioPlayer().playMusic("ThingsAreGettingWeird.wav");
	        	effects.add(new Giant());	
			}
			System.out.println(String.format("Line 123 Player:  CurrentX Room = %d   currentYRoom =%d", state.getCurrentRoomX(), state.getCurrentRoomY()));
			state.loadGameMap(getClass().getResource(state.getGameMaps()[state.getCurrentRoomX()][state.getCurrentRoomY()]).getFile());
			this.transition = false;
		}
    
	}
		
}	
	
//	@Override
//	public Image getSprite() {
//		return animationManager.getSprite(size);
//	}
