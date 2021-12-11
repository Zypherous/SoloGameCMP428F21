package game;
import controller.GameController;
import core.Size;
import display.Display;
import game.settings.GameSettings;
import input.Input;
import state.State;
import state.game.GameState;
import state.menu.MenuState;

public class Game {
	
	public static int SPRITE_SIZE = 64;
	
	private Display display;
	

	private Input input;
	private State state;
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
		
		settings = new GameSettings(false);
		state = new MenuState(new Size(width, height), input, settings);
		gameController = new GameController(input);
		
	}

	public void update() {
		
		state.update(this);
		gameController.update(this);
	}

	
	public void render() {

		display.render(state, settings.isDebugMode());
		
	}
	
	public State getState() {
		return state;
	}

	public GameSettings getSettings() {
		return settings;
	}

	public void enterState(State nextState) {
		state.cleanUp();
        state = nextState;
    }

}
