package game.state;

import java.awt.Color;

import controller.NPCController;
import controller.PlayerController;
import core.Size;
import entity.NPC;
import entity.Player;
import entity.SelectionCircle;
import entity.humanoid.Humanoid;
import entity.humanoid.action.Levitate;
import entity.humanoid.effect.Isolated;
import entity.humanoid.effect.Sick;
import game.ui.UIGameTime;
import game.ui.UISicknessStatistics;
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
    	SelectionCircle sc = new SelectionCircle();
        Player player = new Player(new PlayerController(input), spriteLibrary,  new Size(64,64), sc);
        initializeNPCs(100);
        
//        List<GameObject> listOf = new ArrayList<>();
//        listOf.add(player); 
        gameObjects.add(player);
        camera.focusOn(player);

        sc.parent(player);
        gameObjects.add(sc);
        play = player;     
        makeNumberOfNPCsSick(10);
    }
    
    // Intialization of UI with spacing and positions etc
    private void initializeUI(Size windowSize) {
    	UIContainer containerV = new VerticalContainer(windowSize);
    	containerV.setPadding(new Spacing(20));
    	containerV.setBackgroundColor(new Color(0,0,0,0));
    	containerV.addUIComponent(new UIText("Hello  World!"));
    	containerV.setAlignment(new Alignment(Alignment.Position.CENTER, Alignment.Position.START));
    	
    	UIContainer containerV2 = new VerticalContainer(windowSize);
    	containerV2.setPadding(new Spacing(20));
    	containerV2.setBackgroundColor(new Color(0,0,0,0));
    	containerV2.addUIComponent(new UIText("Hello  World!"));
    	
    	containerV2.setAlignment(new Alignment(Alignment.Position.CENTER, Alignment.Position.END));
    	uiContainers.add(containerV);
    	uiContainers.add(containerV2);
    	uiContainers.add(new UIGameTime(windowSize));
    	uiContainers.add(new UISicknessStatistics(windowSize));
	}

	private void initializeNPCs(int numberOfNPCs){
    	for(int i = 0; i < numberOfNPCs; i++) {
    		NPC npc = new NPC(new NPCController(), spriteLibrary);
    		npc.setPosition(gameMap.getRandomPosition());
    		gameObjects.add(npc);
    	}
    }
	private void makeNumberOfNPCsSick(int number) {
        getGameObjectsOfClass(NPC.class).stream()
                .limit(number)
                .forEach(npc -> npc.addEffect(new Sick()));
    }
    public Player getPlayer() {
    	return this.play;   
    }
    
    public long getNumberOfSick() {
    	return getGameObjectsOfClass(Humanoid.class).stream()
                .filter(humanoid -> humanoid.isAffectedBy(Sick.class) && !humanoid.isAffectedBy(Isolated.class))
                .count();

    }
    
    public long getNumberOfIsolated() {
        return getGameObjectsOfClass(Humanoid.class).stream()
                .filter(humanoid -> humanoid.isAffectedBy(Sick.class) && humanoid.isAffectedBy(Isolated.class))
                .count();
    }

    
    public long getNumberOfHealthy() {
    	return getGameObjectsOfClass(Humanoid.class).stream()
                .filter(humanoid -> !humanoid.isAffectedBy(Sick.class))
                .count();

    }
}
