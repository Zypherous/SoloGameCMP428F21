package display;

import java.awt.Color;
import java.awt.Graphics;

import core.CollisionBox;
import core.Position;
import entity.GameObject;
import game.Game;
import map.GameMap;
import state.State;
import state.game.GameState;

public class Renderer {
//	private Image testImage = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Jonat\\soloGame\\Resources\\Image\\Dungeon Crawl Stone Soup Full\\monster\\cyclops_old.png");
//	private Image backgroundTest = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Jonat\\soloGame\\Resources\\Image\\monster2_combat_backgrounds\\mountains.png");
	
	public void render(State state, Graphics graphics ) {  
		renderMap(state, graphics);
		renderGameObjects(state,graphics);
		renderUI(state,graphics);
	}
	
	private void renderUI(State state, Graphics graphics) {
		state.getMouseHandler().getPrimaryButtonUI().ifPresent(uiImage -> graphics.drawImage(
				uiImage.getSprite(),
				(int)uiImage.getAbsolutePosition().getX(),
				(int)uiImage.getAbsolutePosition().getY(),
				null));
        state.getUiContainers().forEach(uiContainer -> graphics.drawImage(
                uiContainer.getSprite(),
                uiContainer.getRelativePosition().intX(),
                uiContainer.getRelativePosition().intY(),
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
				.forEach(gameObject -> {
					renderGameObject(graphics, camera, gameObject);
					if(state.getGameSettings().getRenderSettings().getCollisionBox().getValue()) {
						drawCollisionBox(gameObject.getCollisionBox(), graphics, camera);
					}
				});
		

		
	}

	// Draws the attachments from each of the individiual gameobjects
	private void renderGameObject(Graphics graphics, Camera camera, GameObject gameObject) {
		gameObject.getAttachments().forEach(attachment -> renderGameObject(graphics,camera,attachment));
		graphics.drawImage(
			gameObject.getSprite(),
			(int)gameObject.getRenderPosition(camera).getX(),
			(int)gameObject.getRenderPosition(camera).getY(),
			gameObject.getSize().getWidth(),
			gameObject.getSize().getHeight(), 
			null
		);
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
		//Refactored to draw lines instead of rects, lines along the x axis and lines along the y axis
		// Before it would call drawRect for each grid position, with the lines its far less as a line
		// can be drawn across entire map at once
		if(state.getGameSettings().getRenderSettings().getShouldRenderGrid().getValue()) {
			graphics.setColor(Color.BLACK);
			for(int x = start.intX(); x< end.intX(); x++) {
				graphics.drawLine(
							x * Game.SPRITE_SIZE - camera.getPosition().intX(),
							start.intY() * Game.SPRITE_SIZE - camera.getPosition().intY(),
							x * Game.SPRITE_SIZE - camera.getPosition().intX(),
							end.intY() * Game.SPRITE_SIZE  - camera.getPosition().intY()
						);
			}
			for(int y = start.intY(); y< end.intY(); y++) {
				graphics.drawLine(
							start.intX() * Game.SPRITE_SIZE - camera.getPosition().intX(),
							y * Game.SPRITE_SIZE - camera.getPosition().intY(),
							end.intX() * Game.SPRITE_SIZE - camera.getPosition().intX(),
							y * Game.SPRITE_SIZE  - camera.getPosition().intY()
						);
			}
		}
	}
	private void drawCollisionBox(CollisionBox collisionBox, Graphics graphics, Camera camera) {
        graphics.setColor(Color.red);
        graphics.drawRect(
                (int) collisionBox.getBounds().getX() - camera.getPosition().intX(),
                (int) collisionBox.getBounds().getY() - camera.getPosition().intY(),
                (int) collisionBox.getBounds().getW(),
                (int) collisionBox.getBounds().getH()
        );
    }
//	private void drawCollisionBox(CollisionBox collisionBox, Graphics graphics, Camera camera) {
//        collisionBox.getBounds().draw(graphics,Color.red);
//    }
//	
}


//BLUE SQUARES TEST

//if(state instanceof GameState) {
//	graphics.setColor(Color.BLUE);
//	for(int row = 1; row < 10; row++) {
//		graphics.fillRect(64- (int)camera.getPosition().getX(), row*64- (int)camera.getPosition().getY(), 64, 64);
//	}
//	graphics.setColor(Color.RED);
//	for(int row = 1; row < 10; row++) {
//		graphics.drawRect(64 - (int)camera.getPosition().getX(), row*64- (int)camera.getPosition().getY(), 64 , 64);
//	}
//	graphics.setColor(Color.GREEN);
//	for(int row = 1; row < 10; row++) {
//		graphics.drawRect(64 + 16 - (int)camera.getPosition().getX(), row*64 + 16- (int)camera.getPosition().getY(), 32 , 32);
//	}
//}

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