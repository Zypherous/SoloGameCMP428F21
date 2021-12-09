package game.state;

import java.awt.Color;

import controller.NPCController;
import controller.PlayerController;
import core.Size;
import entity.NPC;
import entity.Player;
import entity.effect.Sick;
import input.Input;
import map.GameMap;
import ui.HorizontalContainer;
import ui.Spacing;
import ui.UIContainer;
import ui.VerticalContainer;

public class GameState extends State {

	private Player play;
    public GameState(Size windowSize, Input input) {
        super(windowSize, input);
        gameMap = new GameMap(new Size(20, 15)/*(30,30)*/, spriteLibrary);
        initializeCharacters();
    }
    
    private void initializeCharacters() {
        Player player = new Player(new PlayerController(input), spriteLibrary, this.getCamera(),  new Size(128,128));
        initializeNPCs(100);
        initializeUI();
//        List<GameObject> listOf = new ArrayList<>();
//        listOf.add(player); 
        gameObjects.add(player);
        camera.focudOn(player);

        play = player;     
    }
    
    // Intialization of UI with spacing and positions etc
    private void initializeUI() {
    	UIContainer containerV = new VerticalContainer();
    	
    	UIContainer containerH0 = new HorizontalContainer();
    	containerH0.setMargin(new Spacing(10));
    	UIContainer containerH1 = new HorizontalContainer();
    	containerH1.setMargin(new Spacing(10));
    	UIContainer containerH2 = new HorizontalContainer();
    	containerH2.setMargin(new Spacing(10));
    	
    	containerV.setPadding(new Spacing(20));
    	containerH0.setBackgroundColor(Color.GRAY);
    	containerH1.setBackgroundColor(Color.GRAY);
    	containerH2.setBackgroundColor(Color.GRAY);
    	containerH0.addUicomponent(new HorizontalContainer());
    	containerH1.addUicomponent(new HorizontalContainer());
    	containerH2.addUicomponent(new HorizontalContainer());
    	containerH2.addUicomponent(new HorizontalContainer());
    	containerV.addUicomponent(containerH0);
    	containerV.addUicomponent(containerH1);
    	containerV.addUicomponent(containerH2);
    	uiContainers.add(containerV);
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
