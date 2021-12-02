package display;

import game.Game;
import game.state.State;
import map.Tile;

import java.awt.*;

public class Renderer {
	private Image testImage = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Jonat\\soloGame\\Resources\\Image\\Dungeon Crawl Stone Soup Full\\monster\\cyclops_old.png");
	private Image backgroundTest = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Jonat\\soloGame\\Resources\\Image\\monster2_combat_backgrounds\\mountains.png");
	
	public void render(State state, Graphics graphics   
			//This is for the pink Sprite Background/*,Canvas canvas*/
			) {
		//Pink sprite background for tower def prototype
			
//		for(int row = 0; row < canvas.getWidth(); row +=  240) {
//			for(int col = 0; col < canvas.getHeight(); col += 110) {
//				graphics.drawImage(backgroundTest, row,col, null);
//			}
//		}
		
		Camera camera = state.getCamera();
		renderMap(state, graphics);
		state.getGameObjects().forEach(gameObject -> graphics.drawImage(
				gameObject.getSprite(),
				(int)gameObject.getPosition().getX() - (int)camera.getPosition().getX(),
				(int)gameObject.getPosition().getY() - (int)camera.getPosition().getY(),
//				gameObject.getSize().getWidth(),
//				gameObject.getSize().getHeight(), 
				null
				));
		
		
		for(int i = 0; i < state.getRectArr().length; i++) {
			state.getRect(i).draw(graphics);
			graphics.drawImage(testImage, 
					state.getRect(i).getX() - (int)camera.getPosition().getX(),
					state.getRect(i).getY() - (int)camera.getPosition().getY(), 
					64, 64, null);
		}
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
		Tile[][] tiles = state.getGameMap().getTiles();
		graphics.setColor(Color.LIGHT_GRAY);
		for(int x = 0; x < tiles.length; x++) {
			for (int y = 0; y< tiles[x].length; y++) {
				graphics.drawImage(tiles[x][y].getSprite(),
						x * Game.SPRITE_SIZE,
						y * Game.SPRITE_SIZE,
						null
						);
			}
		}
		
	}
	
}
