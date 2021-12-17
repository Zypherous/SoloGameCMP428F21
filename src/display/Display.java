package display;
import java.awt.*;
import java.awt.image.BufferStrategy;
import javax.swing.*;

import game.Game;
import input.Input;
import state.State;
import state.menu.MenuState;


public class Display extends JFrame {
	
	
	private Canvas canvas;
	private Renderer renderer;
	private DebugRenderer debugRenderer;
	
	public Display(int width, int height, Input input) {
		setTitle("Molly's Worlds");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		this.renderer = new Renderer();
		this.debugRenderer = new DebugRenderer();
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setFocusable(false);
		canvas.addMouseListener(input);
		canvas.addMouseMotionListener(input);
		
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
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0,0, canvas.getWidth(),canvas.getHeight());
	
		drawMenuImage( graphics, state);
	
		renderer.render(state, graphics );
		if(debug) {
			debugRenderer.render(state, graphics);
		}
		
		// Free Memory
		graphics.dispose();
		// Brings drawn buffer Strat drawn to the front
		bufferStrategy.show();
		
		
	}
	private void drawMenuImage(Graphics graphics,State state) {
		Image splash = state.getSpriteLibrary().getImage("mollysworld");
        graphics.drawImage(splash, 0, 0, 1280, 960, null);
	}
}
