package map;

import game.Game;
import gfx.SpriteLibrary;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Tile {

	private Image image;
	private Image sprite;
	private int tileIndex;

    public Tile(SpriteLibrary spriteLibrary) {
    	this(spriteLibrary, "floor_sand_stone_1");
    }
    public Tile(SpriteLibrary spriteLibrary, String tileName) {
        this.image = spriteLibrary.getImage(tileName);
        generateSprite();
    }

    private Tile(Image image, int tileIndex) {
        this.image = image;
        this.tileIndex = tileIndex;
        generateSprite();
    }
    
    public Image getSprite() {
        return sprite;
    }
    public static Tile copyOf(Tile tile) {
        return new Tile(tile.getImage(), tile.getTileIndex());
    }
    public int getTileIndex() {
        return tileIndex;
    }

    public void setTileIndex(int tileIndex) {
        this.tileIndex = tileIndex;
        generateSprite();
    }
    public Image getImage() {
        return image;
    }
    private void generateSprite() {
    	//Creating a one dimensional array using math to determine the position of each tile based on the width of the tileset
    	sprite = ((BufferedImage)image).getSubimage(
                (tileIndex / (image.getWidth(null) / Game.SPRITE_SIZE)) * Game.SPRITE_SIZE,
                (tileIndex % (image.getWidth(null) / Game.SPRITE_SIZE)) * Game.SPRITE_SIZE,
                Game.SPRITE_SIZE,
                Game.SPRITE_SIZE
        );
    }


}
