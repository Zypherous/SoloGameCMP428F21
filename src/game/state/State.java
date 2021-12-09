package game.state;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import core.Position;
import core.Size;
import display.Camera;
import entity.GameObject;
import entity.Player;
import entity.Rect;
import game.Time;
import gfx.SpriteLibrary;
import input.Input;
import map.GameMap;
import ui.UIContainer;

public abstract class State {

	// Gives option to pan around with a camera if you want.
    protected GameMap gameMap;
    protected List<GameObject> gameObjects;
    protected List<UIContainer> uiContainers;
    protected SpriteLibrary spriteLibrary;
    protected Input input;
    protected Camera camera;
    protected Time time;
    
	private Random rand;
    
    
    //Custom
    private Rect rect[];
    private int health = 100;

    public State(Size windowSize, Input input) {
        this.input = input;
        gameObjects = new ArrayList<>();
        uiContainers = new ArrayList<>();
        spriteLibrary = new SpriteLibrary();
        camera = new Camera(windowSize);
//        camera = new Camera(new Size(200, 200));
        time = new Time();
        rect = new Rect[10];
        rand = new Random();
		
    }

    
	

	public void update() {
    	sortObjectsByPosition();
        gameObjects.forEach(gameObject -> gameObject.update(this));
        uiContainers.forEach(uiContainer -> uiContainer.update(this));
        camera.update(this);
//        
    }

	public  Position getRandomPosition() {
		return gameMap.getRandomPosition();
	}	
	
    private void sortObjectsByPosition() {
    	// Pre built function that allows you to compare simple things like integers in a lambda function
		gameObjects.sort(Comparator.comparing(gameObject -> gameObject.getPosition().getY()));
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
    
    public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
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


	public abstract Player getPlayer();


	public List<GameObject> getCollidingGameObjects(GameObject gameObject) {
		return gameObjects.stream()
				.filter(other -> other.collidesWith(gameObject))
				.collect(Collectors.toList());
	}
	
//	public Player getPlayer(List<GameObject> gameObjects) {
//		return (Player)(gameObjects.stream().filter(gameObject -> (gameObject.getID() == 0)));
//	}


	public void handleDead() {
		
	}
	
	public List<UIContainer> getUiContainers() {
		return uiContainers;
	}


	public void setUiContainers(List<UIContainer> uiContainers) {
		this.uiContainers = uiContainers;
	}

	
	
	
	
	
	
	
	
	
	
	// CYCLOPS RECTS IN INITIALIZE AREA DONT USE
//	for(int i = 0; i < rect.length;i++) {
//		rect[i] = new Rect(1200 - (int) Math.abs(camera.getPosition().getX()), 
//				((rand.nextInt(9)+1) * 64 ) + (int) Math.abs(camera.getPosition().getY()),
//				64, 64, rand.nextInt(5)+5, 0, camera);
//	}
	
	
	
	
	
	
	//CYCLOPS ARMY MOVEMENT CODE DO NOT USE
//	for(int i = 0; i < rect.length; i++) {
//		rect[i].moveLeft(rect[i].getVelx());
//		if(rect[i].getX() <= 64 ) {		
//			rect[i] = new Rect(camera.getPosition().getY() < 0 ? 1200 - (int) Math.abs(camera.getPosition().getX()) : 1200 + (int) camera.getPosition().getX() , 
//					camera.getPosition().getY() > 0 ? ((rand.nextInt(9)+1)*64) + (int) Math.abs(camera.getPosition().getY()) :  ((rand.nextInt(9)+1)*64) - (int) Math.abs(camera.getPosition().getY()),
//					64, 64, rand.nextInt(5)+5, 0, camera);
//			health--;
//			//System.out.println(String.format("RECT[%d] x: %d, y: %d",i, rect[i].getX(),rect[i].getY()));
//		}
//		camera.update(this);
//	}
}
	
