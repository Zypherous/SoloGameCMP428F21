package game.state;

import controller.PlayerController;
import core.Size;
import entity.GameObject;
import entity.Player;
import entity.Rect;
import gfx.SpriteLibrary;
import input.Input;
import map.GameMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class State {

	// Gives option to pan around with a camera if you want.
    protected GameMap gameMap;
    protected List<GameObject> gameObjects;
    protected SpriteLibrary spriteLibrary;
    protected Input input;
    
	private Random rand;
    
    
    //Custom
    private Rect rect[];
    private int health = 100;

    public State(Input input) {
        this.input = input;
        gameObjects = new ArrayList<>();
        spriteLibrary = new SpriteLibrary();
        
        rect = new Rect[10];
        rand = new Random();
		for(int i = 0; i < rect.length;i++) {
			rect[i] = new Rect(1200, (rand.nextInt(9)+1) * 64, 64, 64, rand.nextInt(5)+5, 0);
		}
    }

    public void update() {
        gameObjects.forEach(gameObject -> gameObject.update());
        for(int i = 0; i < rect.length; i++) {
			rect[i].moveLeft(rect[i].getVelx());
			if(rect[i].getX() <= 64) {		
				rect[i] = new Rect(1200, (rand.nextInt(9)+1)*64, 64, 64, rand.nextInt(5)+5, 0);
				health--;
			}
		}
    }


    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public GameMap getGameMap() {
        return gameMap;
    }
    
    
    
    
    
    // Custom 
    public Rect []getRectArr() {
		return this.rect;
	}
	public Rect getRect(int i) {
		return rect[i];
	}
	
	public int getHealth() {
		return this.health;
	}
	
	public Rect[] getRect() {
		return rect;
	}

	public void setRect(Rect[] rect) {
		this.rect = rect;
	}

	public Random getRand() {
		return rand;
	}

	public void setRand(Random rand) {
		this.rand = rand;
	}


	public void setHealth(int health) {
		this.health = health;
	}
}
