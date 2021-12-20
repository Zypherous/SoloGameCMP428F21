import game.Game;
import game.GameLoop;

public class Launcher {

	public static void main(String[] args) {
		// Starts a new thread and instantiates the game loop
		// screen size of 1280 by 960.
		new Thread(new GameLoop(new Game(1280, 960))).start();

	}

}
