package ui.clickable;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import core.Position;
import core.Size;
import display.Camera;
import entity.GameObject;
import game.Game;
import gfx.ImageUtils;
import map.GameMap;
import state.State;

public class UIMiniMap extends UIClickable {

    private double ratio;
    private int pixelsPerGrid;
    private Position pixelOffset;
    private Rectangle cameraViewBounds;
    private BufferedImage mapImage;
    private Color color;
    
    // To store images and improve the performance of minimap drawing
    private Map<Image, Image> cachedScaledImages;

    public UIMiniMap(GameMap gameMap) {
        size = new Size(128, 128);
        cameraViewBounds = new Rectangle(0, 0, 0, 0);
        color = Color.GRAY;

        calculateRatio(gameMap);
        cachedScaledImages = new HashMap<>();
        generateMap(gameMap, List.of());  // Passes in an empty game List which can be instantiated this way
    }

    @Override
    public void update(State state) {
        super.update(state);
        // Four times in a second
        if(state.getTime().secondsDivisbleBy(0.25)) {
        	generateMap(state.getGameMap(), state.getGameObjects());
        }
        Camera camera = state.getCamera();
        cameraViewBounds = new Rectangle(
                (int) (camera.getPosition().getX() * ratio + pixelOffset.intX()),
                (int) (camera.getPosition().getY() * ratio + pixelOffset.intY()),
                (int) (camera.getWindowSize().getWidth() * ratio),
                (int) (camera.getWindowSize().getHeight() * ratio)
        );

        color = Color.GRAY;
        if(hasFocus) {
            color = Color.WHITE;
        }
    }

    private void generateMap(GameMap gameMap, List<GameObject> gameObjects) {
        mapImage = (BufferedImage) ImageUtils.createCompatibleImage(size, ImageUtils.ALPHA_OPAQUE);
        Graphics2D graphics = mapImage.createGraphics();

        for(int x = 0; x < gameMap.getTiles().length; x++) {
            for(int y = 0; y < gameMap.getTiles()[0].length; y++) {
                graphics.drawImage(
                		getScaledSprite( gameMap.getTiles()[x][y].getSprite()),
                        x * pixelsPerGrid + pixelOffset.intX(),
                        y * pixelsPerGrid + pixelOffset.intY(),
                        null
                );
            }
        }
        gameObjects.forEach(gameObject -> {
            Position positionWithOffset = Position.copyOf(gameObject.getPosition());
            positionWithOffset.subtract(gameObject.getRenderOffset());

            graphics.drawImage(
                    getScaledSprite(gameObject.getSprite()),
                    (int) Math.round(positionWithOffset.getX() / Game.SPRITE_SIZE * pixelsPerGrid),
                    (int) Math.round(positionWithOffset.getY() / Game.SPRITE_SIZE * pixelsPerGrid),
                    null
            );
        });
    }

    private Image getScaledSprite(Image sprite) {
        if(cachedScaledImages.containsKey(sprite)) {
            return cachedScaledImages.get(sprite);
        }

        Size scaledSize = new Size(
                (sprite.getWidth(null) / Game.SPRITE_SIZE) * pixelsPerGrid,
                (sprite.getHeight(null) / Game.SPRITE_SIZE) * pixelsPerGrid
        );

        Image scaledSprite = sprite.getScaledInstance(scaledSize.getWidth(), scaledSize.getHeight(), Image.SCALE_AREA_AVERAGING);
        // Maps sprites to the scaled value
        cachedScaledImages.put(sprite, scaledSprite);
        return scaledSprite;
    }
    
    private void calculateRatio(GameMap gameMap) {
        ratio = Math.min(
                size.getWidth() / (double) gameMap.getWidth(),
                size.getHeight() / (double) gameMap.getHeight()
        );

        pixelsPerGrid = (int) Math.round(Game.SPRITE_SIZE * ratio);

        pixelOffset = new Position(
                (size.getWidth() - gameMap.getTiles().length * pixelsPerGrid) / 2,
                (size.getHeight() - gameMap.getTiles()[0].length * pixelsPerGrid) / 2
        );
    }

    @Override
    protected void onFocus(State state) {

    }

    @Override
    public void onDrag(State state) {
        Position mousePosition = Position.copyOf(state.getInput().getMousePosition());
        mousePosition.subtract(absolutePosition);
        mousePosition.subtract(pixelOffset);

        state.getCamera().setPosition(
            new Position(
                    mousePosition.getX() / ratio - cameraViewBounds.getSize().getWidth() / ratio / 2,
                    mousePosition.getY() / ratio - cameraViewBounds.getSize().getHeight() / ratio / 2
            )
        );
    }

    @Override
    public void onClick(State state) {

    }

    @Override
    public Image getSprite() {
        BufferedImage sprite = (BufferedImage) ImageUtils.createCompatibleImage(size, ImageUtils.ALPHA_OPAQUE);
        Graphics2D graphics = sprite.createGraphics();

        graphics.drawImage(mapImage, 0, 0, null);

        graphics.setColor(color);
        graphics.drawRect(0, 0, size.getWidth() - 1, size.getHeight() - 1);

        graphics.draw(cameraViewBounds);

        graphics.dispose();
        return sprite;
    }

	@Override
	public void onRelease(State state) {
		// TODO Auto-generated method stub
		
	}
}
