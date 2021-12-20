package controller;
import java.awt.event.*;
import game.Game;
import input.Input;

public class GameController {
	
	private Input input;
// Listens always for keys related to debug and speeding up and down the game
	// can theoretically be used to pause the game , but it would prevent renders from happening so 
	// the logic would need to be done properly
	public GameController(Input input) {
		this.input = input;
	}
	
	public void update(Game game) {
		if(input.isPressed((KeyEvent.VK_F2))) {
			game.getSettings().toggleDebugMode();
		}
		
		if(input.isPressed(KeyEvent.VK_Y)) {
			game.getSettings().increaseGameSpeed();
		}
		
		if(input.isPressed(KeyEvent.VK_U)) {
			game.getSettings().decreaseGameSpeed();
		}
	}
}
