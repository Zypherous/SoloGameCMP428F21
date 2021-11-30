package game;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import controller.PlayerController;
import display.Display;
import entity.GameObject;
import entity.Player;
import entity.Rect;
import gfx.SpriteLibrary;
import input.Input;

public class Game {
	private Display display;
	private Rect rect[];
	private Random rand;
	private Input input;
	private SpriteLibrary spriteLibrary;
	
	private List<GameObject> gameObjects;
	

	private int health = 100;
	
	public Game(int width, int height) {
		input = new Input();
		rand = new Random();
		display = new Display(width, height, input);
		rect = new Rect[10];
		gameObjects = new ArrayList<>();
		gameObjects.add(new Player(new PlayerController(input)));
		spriteLibrary = new SpriteLibrary();
		for(int i = 0; i < rect.length;i++) {
			rect[i] = new Rect(1200, (rand.nextInt(9)+1) * 64, 64, 64, rand.nextInt(5)+5, 0);
		}
	}

	public void update() {
		// TODO Auto-generated method stub
		for(int i = 0; i < rect.length; i++) {
			rect[i].moveLeft(rect[i].getVelx());
			if(rect[i].getX() <= 64) {		
				rect[i] = new Rect(1200, (rand.nextInt(9)+1)*64, 64, 64, rand.nextInt(5)+5, 0);
				health--;
			}
		}
		gameObjects.forEach(gameObject -> gameObject.update());
	}

	public void render() {
		// TODO Auto-generated method stub
		display.render( this);
		
	}
	
	public Rect []getRectArr() {
		return this.rect;
	}
	public Rect getRect(int i) {
		return rect[i];
	}
	
	public int getHealth() {
		return this.health;
	}
//	public void pauseGame(KeyEvent e) {
//		if( e = VK_SPACE) {
//			
//		}
//	}
	
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

	public List<GameObject> getGameObjects() {
		return gameObjects;
	}

	public void setGameObjects(List<GameObject> gameObjects) {
		this.gameObjects = gameObjects;
	}

	public void setHealth(int health) {
		this.health = health;
	}

}
