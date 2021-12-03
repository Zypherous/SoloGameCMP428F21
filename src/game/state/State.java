package game.state;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import core.Size;
import display.Camera;
import entity.GameObject;
import entity.Rect;
import gfx.SpriteLibrary;
import input.Input;
import map.GameMap;

public abstract class State {

	// Gives option to pan around with a camera if you want.
    protected GameMap gameMap;
    protected List<GameObject> gameObjects;
    protected SpriteLibrary spriteLibrary;
    protected Input input;
    protected Camera camera;
    
	private Random rand;
    
    
    //Custom
    private Rect rect[];
    private int health = 100;

    public State(Size windowSize, Input input) {
        this.input = input;
        gameObjects = new ArrayList<>();
        spriteLibrary = new SpriteLibrary();
        camera = new Camera(windowSize);
        
        rect = new Rect[10];
        rand = new Random();
		for(int i = 0; i < rect.length;i++) {
			rect[i] = new Rect(1200 - (int) Math.abs(camera.getPosition().getX()), 
					((rand.nextInt(9)+1) * 64 ) + (int) Math.abs(camera.getPosition().getY()),
					64, 64, rand.nextInt(5)+5, 0, camera);
		}
    }

    public void update() {
        gameObjects.forEach(gameObject -> gameObject.update());
        for(int i = 0; i < rect.length; i++) {
			rect[i].moveLeft(rect[i].getVelx());
			if(rect[i].getX() <= 64 ) {		
				rect[i] = new Rect(camera.getPosition().getY() < 0 ? 1200 - (int) Math.abs(camera.getPosition().getX()) : 1200 + (int) camera.getPosition().getX() , 
						camera.getPosition().getY() > 0 ? ((rand.nextInt(9)+1)*64) + (int) Math.abs(camera.getPosition().getY()) :  ((rand.nextInt(9)+1)*64) - (int) Math.abs(camera.getPosition().getY()),
						64, 64, rand.nextInt(5)+5, 0, camera);
				health--;
				System.out.println(String.format("RECT[%d] x: %d, y: %d",i, rect[i].getX(),rect[i].getY()));
			}
			camera.update(this);
		}
    }


    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public GameMap getGameMap() {
        return gameMap;
    }
    
    public Camera getCamera() {
    	return this.camera;
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
