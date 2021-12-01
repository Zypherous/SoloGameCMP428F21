package game;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import controller.PlayerController;
import core.Position;
import display.Display;
import entity.Enemy;
import entity.GameObject;
import entity.Player;
import entity.Rect;
import gfx.SpriteLibrary;
import input.Input;

public class Game {
	
	public static int SPRITE_SIZE = 64;
	
	private Display display;
	private Rect rect[];
	private Random rand;
	private Input input;
	private SpriteLibrary spriteLibrary;
	
	private Player player;
	
	private List<GameObject> gameObjects;
	

	private int health = 100;
	
	public Game(int width, int height) {
		input = new Input();
		rand = new Random();
		display = new Display(width, height, input);
		rect = new Rect[10];
		gameObjects = new ArrayList<>();
		spriteLibrary = new SpriteLibrary();
		player = new Player(new PlayerController(input), spriteLibrary);
		gameObjects.add(player);
		for(int i = 0; i < rect.length;i++) {
			int randY = rand.nextInt((9)+1)*64;
			Position randomPos = new Position(1200,randY);
			rect[i] = new Rect(1200,randY, 64, 64, rand.nextInt(5)+5, 0,randomPos);
			Enemy enemy = new Enemy(rect[i], i, player);
			gameObjects.add(enemy);
		}
	}

	public void update() {
		// TODO Auto-generated method stub
		for(int i = 0; i < rect.length; i++) {
			rect[i].moveLeft(rect[i].getVelx());
			if(rect[i].getX() <= 64) {	
				gameObjects.remove(i+1);
				
				int randY = rand.nextInt((9)+1)*64;
				Position randomPos = new Position(1200,randY);
				rect[i] = new Rect(1200, randY, 64, 64, rand.nextInt(5)+5, 0, randomPos);
				Enemy enemy = new Enemy(rect[i], i, player);
				gameObjects.add(enemy);
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
