package map;

import java.awt.Image;


import gfx.SpriteLibrary;

public class Tile {

    private Image sprite;

    public Tile(SpriteLibrary spriteLibrary) {
    	this(spriteLibrary, "floor_sand_stone_1");
    }
    public Tile(SpriteLibrary spriteLibrary, String tileName) {
        this.sprite = spriteLibrary.getImage(tileName);
    }

    private Tile(Image sprite) {
        this.sprite = sprite;
    }
    
    public Image getSprite() {
        return sprite;
    }
    public static Tile copyOf(Tile tile) {
        return new Tile(tile.getSprite());
    }
}
