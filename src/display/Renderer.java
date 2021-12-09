package display;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import core.Position;
import game.Game;
import game.state.State;
import map.GameMap;

public class Renderer {
	private Image testImage = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Jonat\\soloGame\\Resources\\Image\\Dungeon Crawl Stone Soup Full\\monster\\cyclops_old.png");
	private Image backgroundTest = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Jonat\\soloGame\\Resources\\Image\\monster2_combat_backgrounds\\mountains.png");
	
	public void render(State state, Graphics graphics ) {  
		renderMap(state, graphics);
		renderGameObjects(state,graphics);
		renderUI(state,graphics);
	}
	
	private void renderUI(State state, Graphics graphics) {
		state.getUiContainers().forEach(uiContainer -> graphics.drawImage(
					uiContainer.getSprite(),
					(int)uiContainer.getPosition().getX(),
					(int)uiContainer.getPosition().getY(),
					null
				));
	}

	private void renderGameObjects(State state, Graphics graphics) {
		Camera camera = state.getCamera();
		state.getGameObjects().stream()
				// This stream allows use of useful functions such as filter which will return only the 
		        //  elements that return true without modifying original collection
		        // here we are filtering the positions of the gameobjects that are not in view of the camera in order to not render them.
				.filter(gameObject -> camera.isInView(gameObject))
				.forEach(gameObject -> graphics.drawImage(
				gameObject.getSprite(),
				(int)gameObject.getPosition().getX() - (int)camera.getPosition().getX() - gameObject.getSize().getWidth()/2,
				(int)gameObject.getPosition().getY() - (int)camera.getPosition().getY() - gameObject.getSize().getHeight()/2,
				gameObject.getSize().getWidth(),
				gameObject.getSize().getHeight(), 
				null
				));
		

		graphics.setColor(Color.BLUE);
		for(int row = 1; row < 10; row++) {
			graphics.fillRect(64- (int)camera.getPosition().getX(), row*64- (int)camera.getPosition().getY(), 64, 64);
		}
		graphics.setColor(Color.RED);
		for(int row = 1; row < 10; row++) {
			graphics.drawRect(64 - (int)camera.getPosition().getX(), row*64- (int)camera.getPosition().getY(), 64 , 64);
		}
		graphics.setColor(Color.GREEN);
		for(int row = 1; row < 10; row++) {
			graphics.drawRect(64 + 16 - (int)camera.getPosition().getX(), row*64 + 16- (int)camera.getPosition().getY(), 32 , 32);
		}
	}

	private void renderMap(State state, Graphics graphics) {
		GameMap map = state.getGameMap();
		Camera camera = state.getCamera();
		
		//		Tile[][] tiles = state.getGameMap().getTiles();
		graphics.setColor(Color.LIGHT_GRAY);
		Position start = map.getViewableStartingGridPosition(camera);
		Position end = map.getviewableEndingGridPosition(camera);
		for(int x = (int)start.getX(); x < (int)end.getX(); x++) {
			for (int y = (int)start.getY(); y< (int)end.getY(); y++) {
				graphics.drawImage(map.getTiles()[x][y].getSprite(),
						x * Game.SPRITE_SIZE - (int)state.getCamera().getPosition().getX(),
						y * Game.SPRITE_SIZE - (int)state.getCamera().getPosition().getY(),
						Game.SPRITE_SIZE,
						Game.SPRITE_SIZE,
						null
						);
			}
		}
		
	}
	
}




//This is for the pink Sprite Background/*,Canvas canvas*/

//Pink sprite background for tower def prototype

//for(int row = 0; row < canvas.getWidth(); row +=  240) {
//for(int col = 0; col < canvas.getHeight(); col += 110) {
//	graphics.drawImage(backgroundTest, row,col, null);
//}
//}




//CYCLOPS ARMY DRAW
//for(int i = 0; i < state.getRectArr().length; i++) {
//	state.getRect(i).draw(graphics);
//	graphics.drawImage(testImage, 
//			state.getRect(i).getX() - (int)camera.getPosition().getX(),
//			state.getRect(i).getY() - (int)camera.getPosition().getY(), 
//			64, 64, null);
//}