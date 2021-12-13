package map;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.Serializable;

import game.Game;
import gfx.SpriteLibrary;


public class Tile implements Serializable {

	// To not serialize the images since we can reload the assets
	private transient Image image;
	private transient Image sprite;
	private int tileIndex;
	private String tileName;

    public Tile(SpriteLibrary spriteLibrary) {
    	this(spriteLibrary, "floor_sand_stone_1");
    }
    public Tile(SpriteLibrary spriteLibrary, String tileName) {
        this.image = spriteLibrary.getImage(tileName);
        this.tileName = tileName;
        generateSprite();
    }

    private Tile(Image image, int tileIndex, String tileName) {
        this.image = image;
        this.tileIndex = tileIndex;
        this.tileName = tileName;
        generateSprite();
    }
    
    public Image getSprite() {
        return sprite;
    }
    public static Tile copyOf(Tile tile) {
        return new Tile(tile.getImage(), tile.getTileIndex(), tile.getTileName());
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
    public void reloadGraphics(SpriteLibrary spriteLibrary) {
        image = spriteLibrary.getImage(tileName);
        generateSprite();
    }
	public String getTileName() {
		return tileName;
	}
    
    

}
