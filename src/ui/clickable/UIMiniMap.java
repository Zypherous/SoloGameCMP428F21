package ui.clickable;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import core.Position;
import core.Size;
import display.Camera;
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
    private int pixelsPerGrid;
    private Position pixelOffset;
    
    public UIMiniMap(GameMap gameMap) {
        size = new Size(128, 128);
        cameraViewBounds = new Rect(0, 0, 0, 0);
        color = Color.GRAY;

        calculateRatio(gameMap);
        generateMap(gameMap);
    }

    @Override
    public void update(State state) {
        super.update(state);

        Camera camera = state.getCamera();
        cameraViewBounds = new Rect(
                (int) (camera.getPosition().getX() * ratio + (int)pixelOffset.getX()),
                (int) (camera.getPosition().getY() * ratio + (int)pixelOffset.getY()),
                (int) (camera.getWindowSize().getWidth() * ratio),
                (int) (camera.getWindowSize().getHeight() * ratio)
        );

        color = Color.GRAY;
        if(hasFocus) {
            color = Color.WHITE;
        }
    }

    private void generateMap(GameMap gameMap) {
        mapImage = (BufferedImage) ImageUtils.createCompatibleImage(size, ImageUtils.ALPHA_OPAQUE);
        Graphics2D graphics = mapImage.createGraphics();

        

        for(int x = 0; x < gameMap.getTiles().length; x++) {
            for(int y = 0; y < gameMap.getTiles()[0].length; y++) {
                graphics.drawImage(
                        gameMap.getTiles()[x][y].getSprite().getScaledInstance(pixelsPerGrid, pixelsPerGrid, 0),
                        x * pixelsPerGrid + (int)pixelOffset.getX(),
                        y * pixelsPerGrid + (int)pixelOffset.getX(),
                        null
                );
            }
        }
    }

    private void calculateRatio(GameMap gameMap) {
        ratio = Math.min(
                size.getWidth() / (double) gameMap.getWidth(),
                size.getHeight() / (double) gameMap.getHeight()
        );
        pixelsPerGrid = (int) Math.round(Game.SPRITE_SIZE * ratio);
        
        pixelOffset =  new Position(
        		(size.getWidth() - gameMap.getTiles().length * pixelsPerGrid) / 2,
                (size.getHeight() - gameMap.getTiles()[0].length * pixelsPerGrid) / 2
        		);
    }

    @Override
    protected void onFocus(State state) {

    }

    @Override
    protected void onDrag(State state) {
        Position mousePosition = Position.copyOf(state.getInput().getMousePosition());
        mousePosition.subtract(absolutePosition);
        mousePosition.subtract(pixelOffset);

        state.getCamera().setPosition(
            new Position(
                    mousePosition.getX() / ratio - cameraViewBounds.getW()/ ratio / 2,
                    mousePosition.getY() / ratio - cameraViewBounds.getH() / ratio / 2
            )
        );
    }

    @Override
    protected void onClick(State state) {

    }

    @Override
    public Image getSprite() {
        BufferedImage sprite = (BufferedImage) ImageUtils.createCompatibleImage(size, ImageUtils.ALPHA_OPAQUE);
        Graphics2D graphics = sprite.createGraphics();

        graphics.drawImage(mapImage, 0, 0, null);

        graphics.setColor(color);
        graphics.drawRect(0, 0, size.getWidth() - 1, size.getHeight() - 1);

        cameraViewBounds.draw(graphics, color);

        graphics.dispose();
        return sprite;
    }
}
