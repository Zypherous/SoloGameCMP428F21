package game.state;

import controller.PlayerController;
import core.Size;
import entity.Player;
import input.Input;
import map.GameMap;

public class GameState extends State {

    public GameState(Size windowSize, Input input) {
        super(windowSize, input);
        Player player = new Player(new PlayerController(input), spriteLibrary, camera);
        gameObjects.add(player);
        gameMap = new GameMap(new Size(20, 13), spriteLibrary);
        camera.focudOn(player);
    }
}
