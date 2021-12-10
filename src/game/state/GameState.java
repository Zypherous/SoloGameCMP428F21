package game.state;

import java.awt.Color;

import controller.NPCController;
import controller.PlayerController;
import core.Size;
import entity.NPC;
import entity.Player;
import entity.SelectionCircle;
import entity.effect.Sick;
import game.ui.UIGameTime;
import input.Input;
import map.GameMap;
import ui.Alignment;
import ui.Spacing;
import ui.UIContainer;
import ui.UIText;
import ui.VerticalContainer;

public class GameState extends State {

	private Player play;
    public GameState(Size windowSize, Input input) {
        super(windowSize, input);
        gameMap = new GameMap(new Size(20, 15)/*(30,30)*/, spriteLibrary);
        initializeCharacters();
        initializeUI(windowSize);
    }
    
    private void initializeCharacters() {
        Player player = new Player(new PlayerController(input), spriteLibrary, this.getCamera(),  new Size(128,128));
        initializeNPCs(100);
        
//        List<GameObject> listOf = new ArrayList<>();
//        listOf.add(player); 
        gameObjects.add(player);
        camera.focusOn(player);

        SelectionCircle sc = new SelectionCircle(this.camera);
        sc.setParent(player);
        gameObjects.add(sc);
        play = player;     
    }
    
    // Intialization of UI with spacing and positions etc
    private void initializeUI(Size windowSize) {
    	UIContainer containerV = new VerticalContainer(windowSize);
    	containerV.setPadding(new Spacing(20));
    	containerV.setBackgroundColor(new Color(0,0,0,0));
    	containerV.addUIComponent(new UIText("Hello  World!"));
    	
    	UIContainer containerV2 = new VerticalContainer(windowSize);
    	containerV2.setPadding(new Spacing(20));
    	containerV2.setBackgroundColor(new Color(0,0,0,0));
    	containerV2.addUIComponent(new UIText("Hello  World!"));
    	
    	containerV2.setAlignment(new Alignment(Alignment.Position.CENTER, Alignment.Position.END));
    	uiContainers.add(containerV);
    	uiContainers.add(containerV2);
    	uiContainers.add(new UIGameTime(windowSize));
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
