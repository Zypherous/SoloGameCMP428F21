package state.editor.ui;

import java.awt.Color;

import core.Size;
import game.Game;
import game.settings.GameSettings;
import gfx.SpriteLibrary;
import input.mouse.action.TilePlacer;
import input.mouse.action.TileWalkability;
import map.Tile;
import ui.Alignment;
import ui.HorizontalContainer;
import ui.Spacing;
import ui.UIComponent;
import ui.UIContainer;
import ui.UIHorizontalDivider;
import ui.UITabContainer;
import ui.VerticalContainer;
import ui.clickable.UICheckbox;
import ui.clickable.UIToolToggle;

public class UITileMenu extends VerticalContainer {
    public UITileMenu(Size windowSize, SpriteLibrary spriteLibrary, GameSettings gameSettings) {
        super(windowSize);
        setBackgroundColor(Color.DARK_GRAY);
        setAlignment(new Alignment(Alignment.Position.START, Alignment.Position.END));
        setPadding(new Spacing(5));

        UITabContainer tileContainer = new UITabContainer(windowSize);
        tileContainer.addTab("grass",getTileSet(spriteLibrary, "grass", true));
        tileContainer.addTab("concrete",getTileSet(spriteLibrary, "concrete", true));
        tileContainer.addTab("dirt",getTileSet(spriteLibrary, "dirt", true));
        tileContainer.addTab("dungeon",getTileSet(spriteLibrary, "dungeon1", true));
        tileContainer.addTab("water",getTileSet(spriteLibrary, "water", false));
        tileContainer.setPadding(new Spacing(0));
        
        addUIComponent(tileContainer);
        addUIComponent(getToolContainer(spriteLibrary, gameSettings));
    }
    
    private UIComponent getToolContainer(SpriteLibrary spriteLibrary, GameSettings gameSettings) {
        UIContainer toolsContainer = new HorizontalContainer(size);
        toolsContainer.setCenterChildren(true);
        toolsContainer.setPadding(new Spacing(0));
        toolsContainer.setMargin(new Spacing(5, 0, 0, 0));

        toolsContainer.addUIComponent(new UICheckbox("Autotile", gameSettings.getEditorSettings().getAutotile()));
        toolsContainer.addUIComponent(new UIHorizontalDivider());
        toolsContainer.addUIComponent(new UIToolToggle(spriteLibrary.getImage("nowalk"), new TileWalkability(gameSettings.getRenderSettings())));

        return toolsContainer;
    }

    private UIContainer getTileSet(SpriteLibrary spriteLibrary, String tileset, boolean walkable) {
        UIContainer main = new HorizontalContainer(new Size(0, 0));
        main.setMargin(new Spacing(0));
        main.setPadding(new Spacing(0));
        Tile tile = new Tile(spriteLibrary, tileset, walkable);

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
                UIToolToggle toggle = new UIToolToggle(indexedTile.getSprite(), new TilePlacer(indexedTile));
                column.addUIComponent(toggle);
            }

            main.addUIComponent(column);
        }

        return main;
    }
}
