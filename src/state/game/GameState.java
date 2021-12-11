package state.game;

import java.awt.Color;
import java.util.List;

import controller.NPCController;
import controller.PlayerController;
import core.Condition;
import core.Size;
import entity.NPC;
import entity.Player;
import entity.SelectionCircle;
import entity.humanoid.effect.Isolated;
import entity.humanoid.effect.Sick;
import game.Game;
import game.settings.GameSettings;
import input.Input;
import map.GameMap;
import state.State;
import state.game.ui.UIGameTime;
import state.game.ui.UISicknessStatistics;
import state.menu.MenuState;
import ui.Alignment;
import ui.Spacing;
import ui.UIContainer;
import ui.UIText;
import ui.VerticalContainer;
import ui.clickable.UIButton;

public class GameState extends State {

	private List<Condition> victoryConditions;
	private List<Condition> defeatConditions;
	private boolean playing;
	
	private Player play;
    public GameState(Size windowSize, Input input, GameSettings gameSettings) {
        super(windowSize, input, gameSettings);
        gameMap = new GameMap(new Size(20, 15)/*(30,30)*/, spriteLibrary);
        playing = true;
        
        initializeCharacters();
        initializeUI(windowSize);
        initializeConditions();
        
        audioPlayer.playMusic("MollysWorld.wav");
    }
    
    private void initializeConditions() {
    	victoryConditions = List.of(()-> getNumberOfSick() == 0);
    	defeatConditions = List.of(()-> (float)getNumberOfSick() / getNumberOfNPC() > 0.25);
    }
	private void initializeCharacters() {
    	SelectionCircle sc = new SelectionCircle();
        Player player = new Player(new PlayerController(input), spriteLibrary,  new Size(64,64), sc);
        initializeNPCs(50);
        
//        List<GameObject> listOf = new ArrayList<>();
//        listOf.add(player); 
        gameObjects.add(player);
        camera.focusOn(player);

        sc.parent(player);
        gameObjects.add(sc);
        play = player;     
        makeNumberOfNPCsSick(5);
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
	
	@Override
	public void update(Game game) {
		super.update(game);
		
		if(playing) {
			if(victoryConditions.stream().allMatch(Condition::isMet)) {
				win();
			}
			if(defeatConditions.stream().allMatch(Condition::isMet)) {
				lose();
			}
		}
	}
	
	
	private void lose() {
		playing = false;

        VerticalContainer loseContainer = new VerticalContainer(camera.getWindowSize());
        loseContainer.setAlignment(new Alignment(Alignment.Position.CENTER, Alignment.Position.CENTER));
        loseContainer.addUIComponent(new UIText("DEFEAT"));
        uiContainers.add(loseContainer);
	}

	private void win() {
		playing = false;

        VerticalContainer winContainer = new VerticalContainer(camera.getWindowSize());
        winContainer.setAlignment(new Alignment(Alignment.Position.CENTER, Alignment.Position.CENTER));
        winContainer.setBackgroundColor(Color.DARK_GRAY);
        winContainer.addUIComponent(new UIButton("Menu", (state) -> state.setNextState(new MenuState(windowSize, input, gameSettings))));
        winContainer.addUIComponent(new UIButton("Options", (state) -> state.setNextState(new MenuState(windowSize, input, gameSettings))));
        winContainer.addUIComponent(new UIButton("Exit", (state) -> System.exit(0)));
        uiContainers.add(winContainer);
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
    	return getGameObjectsOfClass(NPC.class).stream()
                .filter(npc -> npc.isAffectedBy(Sick.class) && !npc.isAffectedBy(Isolated.class))
                .count();

    }
    
    public long getNumberOfIsolated() {
        return getGameObjectsOfClass(NPC.class).stream()
                .filter(npc -> npc.isAffectedBy(Sick.class) && npc.isAffectedBy(Isolated.class))
                .count();
    }

    
    public long getNumberOfHealthy() {
    	return getGameObjectsOfClass(NPC.class).stream()
                .filter(npc -> !npc.isAffectedBy(Sick.class))
                .count();

    }
    public long getNumberOfNPC() {
    	return getGameObjectsOfClass(NPC.class).size();
    }
}
