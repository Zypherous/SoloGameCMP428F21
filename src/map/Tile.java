package map;

import java.awt.Image;


import gfx.SpriteLibrary;

public class Tile {

    private Image sprite;

    public Tile(SpriteLibrary spriteLibrary) {
        this.sprite = spriteLibrary.getImage("floor_sand_stone_1");
    }

    public Image getSprite() {
        return sprite;
    }
}
