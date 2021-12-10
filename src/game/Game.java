package game;
import controller.GameController;
import core.Size;
import display.Display;
import game.settings.GameSettings;
import game.state.GameState;
import input.Input;

public class Game {
	
	public static int SPRITE_SIZE = 64;
	
	private Display display;
	

	private Input input;
	private GameState state;
	private GameSettings settings;
	
	private Size size;

	private GameController gameController;
	
	
	public Game(int width, int height) {
//		size = new Size(1280, 832);
//		size = new Size(800	, 600);
//		size = new Size(200,200);
		size = new Size(width, height);
		input = new Input();
		
		display = new Display(width, height, input);
		
		state = new GameState(size,input);
		settings = new GameSettings(true);
		gameController = new GameController(input);
		
	}

	public void update() {
		
		state.update();
		gameController.update(this);
	}

	
	public void render() {

		display.render(state, settings.isDebugMode());
		
	}
	
	public GameState getState() {
		return state;
	}

	public GameSettings getSettings() {
		return settings;
	}

	

}
