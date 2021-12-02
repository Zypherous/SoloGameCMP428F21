package display;
import java.awt.*;
import java.awt.image.BufferStrategy;
import javax.swing.*;

import game.Game;
import game.state.State;
import input.Input;


public class Display extends JFrame {
	
	
	private Canvas canvas;
	private Renderer renderer;
	
	public Display(int width, int height, Input input) {
		setTitle("Tower Defense F21");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		this.renderer = new Renderer();
		
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
	
	public void render(State state) {
		BufferStrategy bufferStrategy = canvas.getBufferStrategy();
		Graphics graphics = bufferStrategy.getDrawGraphics();
		
		Font font = new Font("Serif", Font.PLAIN, 32);
		
		graphics.setFont(font);
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0,0, canvas.getWidth(),canvas.getHeight());
		
		renderer.render(state, graphics /*, canvas*/);
		graphics.setColor(Color.GREEN);
//		graphics.fillRect(  rect.x + 5,
//							rect.y + 5,
//							rect.h - 10,
//							rect.w - 10);
		

		
		
		graphics.drawString(String.format("Health: %d", state.getHealth()), 64, 64);
		
		
		// Free Memory
		graphics.dispose();
		// Brings drawn buffer Strat drawn to the front
		bufferStrategy.show();
		
		
	}
}
