package game;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import controller.PlayerController;
import core.Size;
import display.Display;
import entity.GameObject;
import entity.Player;
import entity.Rect;
import game.state.GameState;
import game.state.State;
import gfx.SpriteLibrary;
import input.Input;

public class Game {
	
	public static int SPRITE_SIZE = 64;
	
	private Display display;
	

	private Input input;
	private GameState state;
	private GameSettings settings;
	
	private Size size;

	
	
	public Game(int width, int height) {
//		size = new Size(1280, 832);
//		size = new Size(800	, 600);
//		size = new Size(200,200);
		size = new Size(width, height);
		input = new Input();
		
		display = new Display(width, height, input);
		
		state = new GameState(size,input);
		
	}

	public void update() {
		
		state.update();
	}

	public void render() {

		display.render( state);
		
	}
	
	public GameState getState() {
		return state;
	}


	

}
