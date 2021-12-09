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
	private DebugRenderer debugRenderer;
	
	public Display(int width, int height, Input input) {
		setTitle("Tower Defense F21");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		this.renderer = new Renderer();
		this.debugRenderer = new DebugRenderer();
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setFocusable(false);
		
		add(canvas);
		addKeyListener(input);
		pack();
		
		canvas.createBufferStrategy(2);
		
		setLocationRelativeTo(null);
		setVisible(true);
		
		
		
	}
	
	public void render(State state, boolean debug) {
		BufferStrategy bufferStrategy = canvas.getBufferStrategy();
		Graphics graphics = bufferStrategy.getDrawGraphics();
		
		Font font = new Font("Serif", Font.PLAIN, 32);
		
		graphics.setFont(font);
		graphics.setColor(Color.LIGHT_GRAY);
		graphics.fillRect(0,0, canvas.getWidth(),canvas.getHeight());
		
		renderer.render(state, graphics /*, canvas*/);
		if(debug) {
			debugRenderer.render(state, graphics);
		}
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
