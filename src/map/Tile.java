package map;

import java.awt.Image;
import java.awt.image.BufferedImage;

import game.Game;
import gfx.SpriteLibrary;
import io.Persistable;


public class Tile implements Persistable {

	// To not serialize the images since we can reload the assets
	private transient Image image;
	private transient Image sprite;
	private int tileIndex;
	private String tileName;
	private boolean walkable;

	public Tile() {
		walkable = true;
	}
    public Tile(SpriteLibrary spriteLibrary) {
    	this(spriteLibrary, "grass", true);
    }
    public Tile(SpriteLibrary spriteLibrary, String tileName, boolean walkable) {
        this.image = spriteLibrary.getImage(tileName);
        this.tileName = tileName;
        this.walkable = walkable;
        generateSprite();
    }

    private Tile(Image image, int tileIndex, String tileName, boolean walkable) {
        this.image = image;
        this.tileIndex = tileIndex;
        this.tileName = tileName;
        this.walkable = walkable;
        generateSprite();
    }
    
    public Image getSprite() {
        return sprite;
    }
    public static Tile copyOf(Tile tile) {
        return new Tile(tile.getImage(), tile.getTileIndex(), tile.getTileName(), tile.isWalkable());
    }
    public boolean isWalkable() {
		return walkable;
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
	@Override
	public String serialize() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(this.getClass().getSimpleName());
		stringBuilder.append(DELIMITER);
		stringBuilder.append(tileName);
		stringBuilder.append(DELIMITER);
		stringBuilder.append(tileIndex);
		stringBuilder.append(DELIMITER);
		stringBuilder.append(walkable);
		return stringBuilder.toString();
	}
	@Override
	public void applySerializedData(String serializedData) {
		String[] tokens = serializedData.split(DELIMITER);
		tileName = tokens[1];
		tileIndex = Integer.parseInt(tokens[2]);
		if(tokens.length > 3) {
			walkable = Boolean.parseBoolean(tokens[3]);
		}
	}
	public void setWalkable(boolean walkable) {
		this.walkable = walkable;
		
	}
    
    

}
