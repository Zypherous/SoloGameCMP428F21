package state.editor.ui;

import core.Size;
import game.Game;
import gfx.SpriteLibrary;
import map.Tile;
import ui.*;
import ui.clickable.UITileToggle;

import java.awt.*;

public class UITileMenu extends HorizontalContainer {
    public UITileMenu(Size windowSize, SpriteLibrary spriteLibrary) {
        super(windowSize);
        setBackgroundColor(Color.DARK_GRAY);
        setAlignment(new Alignment(Alignment.Position.START, Alignment.Position.END));

        addUIComponent(new UITileToggle(new Tile(spriteLibrary, "stonefloor")));
        addUIComponent(getTileSet(spriteLibrary, "concrete"));
        addUIComponent(getTileSet(spriteLibrary, "dirt"));
        addUIComponent(getTileSet(spriteLibrary, "dungeon"));
    }

    private UIComponent getTileSet(SpriteLibrary spriteLibrary, String tileset) {
        UIContainer main = new HorizontalContainer(new Size(0, 0));
        main.setMargin(new Spacing(0));
        main.setPadding(new Spacing(0));
        Tile tile = new Tile(spriteLibrary, tileset);

        int tilesX = tile.getImage().getWidth(null) / Game.SPRITE_SIZE;
        int tilesY = tile.getImage().getHeight(null) / Game.SPRITE_SIZE;

        for(int x = 0; x < tilesX; x++) {
            UIContainer column = new VerticalContainer(new Size(0, 0));
            column.setPadding(new Spacing(0));
            column.setMargin(new Spacing(0));

            for(int y = 0; y < tilesY; y++) {
                Tile indexedTile = Tile.copyOf(tile);
                //Convert from two dimensional array to one
                indexedTile.setTileIndex(x * tilesX + y);
                UITileToggle toggle = new UITileToggle(indexedTile);
                column.addUIComponent(toggle);
            }

            main.addUIComponent(column);
        }

        return main;
    }
}
