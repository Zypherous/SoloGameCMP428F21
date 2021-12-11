package ui.clickable;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import core.Size;
import entity.Rect;
import game.Game;
import gfx.ImageUtils;
import map.GameMap;
import state.State;

public class UIMiniMap extends UIClickable {
	
	private double ratio;
	private Rect cameraViewBounds;
	private BufferedImage mapImage;
	private Color color;
	
	public UIMiniMap(GameMap gameMap) {
		size = new Size(128,128);
		cameraViewBounds = new Rect(0,0,0,0);
		
		calculateRatio(gameMap);
		generateMap(gameMap);
	}

	private void generateMap(GameMap gameMap) {
		mapImage = (BufferedImage) ImageUtils.createCompatibleImage(size, ImageUtils.ALPHA_OPAQUE);
		Graphics2D graphics = mapImage.createGraphics();
		
		int pixelsPerGrid = (int) Math.round(Game.SPRITE_SIZE * ratio);
		
		for(int x = 0; x < gameMap.getTiles().length; x++) {
			for(int y = 0; y < gameMap.getTiles()[0].length; y++) {
				graphics.drawImage(
						gameMap.getTiles()[x][y].getSprite().getScaledInstance(pixelsPerGrid, pixelsPerGrid, 0),
						x * pixelsPerGrid + (size.getWidth() - gameMap.getTiles().length * pixelsPerGrid) /2, 
						y * pixelsPerGrid + (size.getHeight() - gameMap.getTiles()[0].length * pixelsPerGrid) /2,
						null);
			}
		}
	}

	private void calculateRatio(GameMap gameMap) {
		// Get Smallest Ratio
		ratio = Math.min(
				size.getWidth()  / (double)gameMap.getWidth(),
				size.getHeight() / (double)gameMap.getHeight());
	}

	@Override
	protected void onFocus(State state) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onDrag(State state) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onClick(State state) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Image getSprite() {
		BufferedImage sprite = (BufferedImage) ImageUtils.createCompatibleImage(size, ImageUtils.ALPHA_OPAQUE);
		Graphics2D graphics = sprite.createGraphics();
		
		graphics.drawImage(sprite, 0, 0, null);
		graphics.setColor(color);
		graphics.drawRect(0, 0, size.getWidth() -1, size.getHeight() -1);
		
		graphics.dispose();
		return sprite;
	}
	
}
