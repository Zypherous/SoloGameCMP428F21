import game.Game;
import game.GameLoop;

public class Launcher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Thread(new GameLoop(new Game(1280, 960))).start();

	}

}
