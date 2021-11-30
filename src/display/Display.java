package display;
import java.awt.*;
import java.awt.image.BufferStrategy;
import javax.swing.*;

import game.Game;
import input.Input;


public class Display extends JFrame {
	private Image testImage = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Jonat\\soloGame\\Resources\\Image\\Dungeon Crawl Stone Soup Full\\monster\\cyclops_old.png");
	private Image backgroundTest = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Jonat\\soloGame\\Resources\\Image\\monster2_combat_backgrounds\\mountains.png");
	
	
	private Canvas canvas;
	
	public Display(int width, int height, Input input) {
		setTitle("Tower Defense F21");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setFocusable(false);
		
		add(canvas);
		addKeyListener(input);
		pack();
		
		canvas.createBufferStrategy(3);
		
		setLocationRelativeTo(null);
		setVisible(true);
		
		
		
	}
	
	public void render(Game game) {
		BufferStrategy bufferStrategy = canvas.getBufferStrategy();
		Graphics graphics = bufferStrategy.getDrawGraphics();
		
		Font font = new Font("Serif", Font.PLAIN, 32);
		
		graphics.setFont(font);
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0,0, canvas.getWidth(),canvas.getHeight());
		
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
		graphics.setColor(Color.GREEN);
//		graphics.fillRect(  rect.x + 5,
//							rect.y + 5,
//							rect.h - 10,
//							rect.w - 10);
		

		game.getGameObjects().forEach(gameObject -> graphics.drawImage(
															gameObject.getSprite(),
															gameObject.getPosition().getX(),
															gameObject.getPosition().getY(),
//															gameObject.getSize().getWidth(),
//															gameObject.getSize().getHeight(), 
															null
		));
		
		graphics.drawString(String.format("Health: %d", game.getHealth()), 64, 64);
		
		
		// Free Memory
		graphics.dispose();
		// Brings drawn buffer Strat drawn to the front
		bufferStrategy.show();
		
		
	}
}
