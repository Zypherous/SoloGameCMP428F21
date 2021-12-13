package map;

import java.util.Arrays;

import core.Position;
import core.Size;
import display.Camera;
import game.Game;
import gfx.SpriteLibrary;
import io.Persistable;

public class GameMap implements Persistable {


	private static final int RENDER_BUFFER = 2;
    private Tile[][] tiles;

    
    public GameMap() {};
    public GameMap(Size size, SpriteLibrary spriteLibrary) {
        tiles = new Tile[size.getWidth()][size.getHeight()];
        initializeTiles(spriteLibrary);
    }

    private void initializeTiles(SpriteLibrary spriteLibrary) {
    	// For each array row in side array tile
        for(Tile[] row: tiles) {
        	
            Arrays.fill(row, new Tile(spriteLibrary));
        }
    }

    public Tile[][] getTiles() {
        return tiles;
    }
    
    public int getWidth() {
    	return tiles.length * Game.SPRITE_SIZE;
    }
    public int getHeight() {
    	return tiles[0].length * Game.SPRITE_SIZE;
    }

	public Position getRandomPosition() {
		double x = Math.random() * tiles.length * Game.SPRITE_SIZE;
		double y = Math.random() * tiles[0].length * Game.SPRITE_SIZE;
		return new Position (x, y);
	}

	public Position getViewableStartingGridPosition(Camera camera) {
		
		return new Position(
				Math.max(0, camera.getPosition().getX()/Game.SPRITE_SIZE - RENDER_BUFFER),
				Math.max(0,camera.getPosition().getY()/Game.SPRITE_SIZE - RENDER_BUFFER)
				);
	}

	public Position getviewableEndingGridPosition(Camera camera) {
		
		return  new Position(
				Math.min(tiles.length, camera.getPosition().getX()/Game.SPRITE_SIZE + camera.getWindowSize().getWidth()/Game.SPRITE_SIZE + RENDER_BUFFER),
				Math.min(tiles[0].length, camera.getPosition().getY()/Game.SPRITE_SIZE +camera.getWindowSize().getHeight()/Game.SPRITE_SIZE + RENDER_BUFFER)
				);
	}
	
	public boolean gridWithinBounds(int gridX, int gridY) {
        return gridX >= 0 && gridX < tiles.length
                && gridY >= 0 && gridY < tiles[0].length;
    }

    public void setTile(int gridX, int gridY, Tile tile) {
        tiles[gridX][gridY] = tile;
    }
    
    public void reloadGraphics(SpriteLibrary spriteLibrary) {
        for(Tile[] row: tiles) {
            for(Tile tile: row) {
                tile.reloadGraphics(spriteLibrary);
            }
        }
    }
    
	@Override
	public String serialize() {
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append(this.getClass().getSimpleName());
		stringBuilder.append(DELIMITER);
		stringBuilder.append(tiles.length);
		stringBuilder.append(DELIMITER);
		stringBuilder.append(tiles[0].length);
		stringBuilder.append(DELIMITER);
		
		stringBuilder.append(SECTION_DELIMITER);
		for (int x = 0; x < tiles.length;x++) {
			for(int y = 0; y < tiles[0].length; y++) {
				stringBuilder.append(tiles[x][y].serialize());
				stringBuilder.append(LIST_DELIMITER);
			}
			stringBuilder.append(COLUMN_DELIMITER);
		}
		
		return stringBuilder.toString();
	}

	@Override
	public void applySerializedData(String serializedData) {
		// Grabs data and splits based on delimiter set
		String[] tokens = serializedData.split(DELIMITER);
		//tiles length is the first data after the class name, x  and y are immediately after one another
		tiles = new Tile[Integer.parseInt(tokens[1])][Integer.parseInt(tokens[2])];
		// Tiles section is nect, split based on section delimiter
		String tileSection = serializedData.split(SECTION_DELIMITER)[1];
		// Splits columns of tiles (y) in each row
		 String[] columns = tileSection.split(COLUMN_DELIMITER);
		// goes through the columns and grabs tiles
		 for(int x = 0; x < tiles.length; x++) {
	            String[] serializedTiles = columns[x].split(LIST_DELIMITER);
	            for(int y = 0; y < tiles[0].length; y++) {
	                Tile tile = new Tile();
	                tile.applySerializedData(serializedTiles[y]);

	                tiles[x][y] = tile;
	            }
	        }
				
	}

}
