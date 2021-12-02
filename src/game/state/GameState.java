package game.state;

import java.util.List;

import controller.NPCController;
import controller.PlayerController;
import core.Position;
import core.Size;
import entity.NPC;
import entity.Player;
import game.Game;
import input.Input;
import map.GameMap;

public class GameState extends State {

    public GameState(Size windowSize, Input input) {
        super(windowSize, input);
        gameMap = new GameMap(new Size(20, 13), spriteLibrary);
        initializeCharacters();
    }
    
    private void initializeCharacters() {
        Player player = new Player(new PlayerController(input), spriteLibrary, this.getCamera());
        NPC npc = new NPC(new NPCController(), spriteLibrary);
        npc.setPosition(new Position(3 * Game.SPRITE_SIZE, 2 * Game.SPRITE_SIZE));
        gameObjects.addAll(List.of(player, npc));
        camera.focudOn(player);
    }
}
