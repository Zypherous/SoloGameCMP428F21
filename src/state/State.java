package state;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import audio.AudioPlayer;
import core.Position;
import core.Size;
import display.Camera;
import entity.GameObject;
import entity.Player;
import entity.Rect;
import game.Game;
import game.Time;
import game.settings.GameSettings;
import gfx.SpriteLibrary;
import input.Input;
import input.mouse.MouseHandler;
import io.MapIO;
import map.GameMap;
import ui.UIContainer;

public abstract class State {

	// Gives option to pan around with a camera if you want.
	protected GameSettings gameSettings;
	protected AudioPlayer audioPlayer;
    protected GameMap gameMap;
    protected List<GameObject> gameObjects;
    protected List<UIContainer> uiContainers;
    protected SpriteLibrary spriteLibrary;
    protected Input input;
    protected Camera camera;
    protected Time time;
    protected Size windowSize;
	protected MouseHandler mouseHandler;
    protected Random rand;
	
    
	private State nextState;
    
    //Custom
    private Rect rect[];
    private int health = 100;

    public State(Size windowSize, Input input, GameSettings gameSettings) {
    	this.windowSize = windowSize;
        this.input = input;
        this.gameSettings = gameSettings;
        audioPlayer = new AudioPlayer(gameSettings.getAudioSettings());
        gameObjects = new ArrayList<>();
        uiContainers = new ArrayList<>();
        spriteLibrary = new SpriteLibrary();
        mouseHandler = new MouseHandler();
        camera = new Camera(windowSize);
//        camera = new Camera(new Size(200, 200));
        time = new Time();
        rect = new Rect[10];
        rand = new Random();
    }

    
	

	public void update(Game game) {
		audioPlayer.update();
		time.update();
    	sortObjectsByPosition();
    	updateGameObjects();
    	List.copyOf(uiContainers).forEach(uiContainer -> uiContainer.update(this));
        camera.update(this);
        mouseHandler.update(this);
        if(nextState != null) {
            game.enterState(nextState);
        }
    }
	private void handleMouseInput() {

        if(input.isMouseClicked()) {
            System.out.println(String.format("MOUSE CLICKED AT POSITION x:%d y:%d", input.getMousePosition().intX(), input.getMousePosition().intY()));
        }

        input.clearMouseClick();
    }
	
	
	private void updateGameObjects() {
        for(int i = 0; i < gameObjects.size(); i++) {
            gameObjects.get(i).update(this);
        }
    }

	public  Position getRandomPosition() {
		return gameMap.getRandomPosition();
	}	
	
    private void sortObjectsByPosition() {
    	// Pre built function that allows you to compare simple things like integers in a lambda function
		gameObjects.sort(Comparator.comparing(GameObject::getRenderOrder).thenComparing(gameObject -> gameObject.getPosition().getY()));
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

	public void setHealth(int health) {
		this.health = health;
	}

	public abstract Player getPlayer();

	public List<GameObject> getCollidingGameObjects(GameObject gameObject) {
		return gameObjects.stream()
				.filter(other -> other.collidesWith(gameObject))
				.collect(Collectors.toList());
	}
	
	public List<UIContainer> getUiContainers() {
		return uiContainers;
	}

	public void setUiContainers(List<UIContainer> uiContainers) {
		this.uiContainers = uiContainers;
	}

	public <T extends GameObject> List<T> getGameObjectsOfClass(Class<T> clazz) {
        return gameObjects.stream()
                .filter(clazz::isInstance)
                .map(gameObject -> (T) gameObject)
                .collect(Collectors.toList());
    }

	public SpriteLibrary getSpriteLibrary() {
		return spriteLibrary;
	}

	public void spawn(GameObject gameObject) {
        gameObjects.add(gameObject);
    }

	public int getHealth() {
		return health;
	}




	public Input getInput() {
		return this.input;
	}
	
	public void setNextState(State nextState) {
        this.nextState = nextState;
    }




	public GameSettings getGameSettings() {
		return gameSettings;
	}




	public AudioPlayer getAudioPlayer() {
		return this.audioPlayer;
	}




	public void cleanUp() {
		audioPlayer.clear();
		
	}




	public MouseHandler getMouseHandler() {
		return mouseHandler;
	}
	
	public void loadGameMap() {
        gameMap = MapIO.load(spriteLibrary);
    }
	
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