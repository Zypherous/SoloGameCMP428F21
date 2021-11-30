package display;

import game.Game;
import java.awt.*;

public class Renderer {
	private Image testImage = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Jonat\\soloGame\\Resources\\Image\\Dungeon Crawl Stone Soup Full\\monster\\cyclops_old.png");
	private Image backgroundTest = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Jonat\\soloGame\\Resources\\Image\\monster2_combat_backgrounds\\mountains.png");
	
	public void render(Game game, Graphics graphics, Canvas canvas) {
		
		for(int row = 0; row < canvas.getWidth(); row +=  240) {
			for(int col = 0; col < canvas.getHeight(); col += 110) {
				graphics.drawImage(backgroundTest, row,col, null);
			}
		}
		for(int row = 1; row < 10; row++) {
			graphics.fillRect(32, row*64, 64, 64);
		}
		
		for(int i = 0; i < game.getRectArr().length; i++) {
			game.getRect(i).draw(graphics);
			graphics.drawImage(testImage, game.getRect(i).getX(),game.getRect(i).getY(), 64, 64, null);
		}
		game.getGameObjects().forEach(gameObject -> graphics.drawImage(
				gameObject.getSprite(),
				(int)gameObject.getPosition().getX(),
				(int)gameObject.getPosition().getY(),
//				gameObject.getSize().getWidth(),
//				gameObject.getSize().getHeight(), 
				null
				));
	}
	
}
