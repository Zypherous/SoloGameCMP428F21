package game.state;

import controller.NPCController;
import controller.PlayerController;
import core.Size;
import entity.NPC;
import entity.Player;
import entity.effect.Sick;
import input.Input;
import map.GameMap;

public class GameState extends State {

	private Player play;
    public GameState(Size windowSize, Input input) {
        super(windowSize, input);
        gameMap = new GameMap(new Size(20, 13)/*(30,30)*/, spriteLibrary);
        initializeCharacters();
    }
    
    private void initializeCharacters() {
        Player player = new Player(new PlayerController(input), spriteLibrary, this.getCamera(),  new Size(128,128));
        initializeNPCs(100);
//        List<GameObject> listOf = new ArrayList<>();
//        listOf.add(player); 
        gameObjects.add(player);
        camera.focudOn(player);

        play = player;     
    }
    private void initializeNPCs(int numberOfNPCs){
    	for(int i = 0; i < numberOfNPCs; i++) {
    		NPC npc = new NPC(new NPCController(), spriteLibrary, camera);
    		npc.setPosition(gameMap.getRandomPosition());
    		npc.addEffect(new Sick());
    		gameObjects.add(npc);
    	}
    }
    public Player getPlayer() {
    	return this.play;   
    }
}
