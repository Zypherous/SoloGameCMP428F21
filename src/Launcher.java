import game.Game;
import game.GameLoop;

public class Launcher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Thread(new GameLoop(new Game(800, 600))).start();

	}

}
