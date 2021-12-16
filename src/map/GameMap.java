package map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import core.CollisionBox;
import core.Position;
import core.Size;
import display.Camera;
import entity.Rect;
import entity.Scenery;
import game.Game;
import gfx.SpriteLibrary;
import io.Persistable;

public class GameMap implements Persistable {


	private static final int RENDER_BUFFER = 2;
    private Tile[][] tiles;
    private List<Scenery> sceneryList;

    
    public GameMap() {
    	sceneryList = new ArrayList<>();
    };
    public GameMap(Size size, SpriteLibrary spriteLibrary) {
        tiles = new Tile[size.getWidth()][size.getHeight()];
        sceneryList = new ArrayList<>();
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
		int gridX = (int) x/ Game.SPRITE_SIZE;
		int gridY = (int) y/ Game.SPRITE_SIZE;
		//recursive call if position unwalkable;
		if(!getTile(gridX,gridY).isWalkable() || tileHadUnwalkableScenery(gridX, gridY) ) {
			return getRandomPosition();
		}
		
		return new Position (x, y);
	}

	// Checks if the scenery object collides with grid object to determine that the scenery is within that grid position
	private boolean tileHadUnwalkableScenery(int gridX, int gridY) {
		CollisionBox gridCollisionBox = getGridCollisionBox(gridX,gridY);
		return sceneryList.stream()
				.filter(scenery -> scenery.isWalkable())
				.anyMatch(scenery -> scenery.getCollisionBox().collidesWith(gridCollisionBox));
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
        
        sceneryList.forEach(scenery -> scenery.loadGraphics(spriteLibrary));
    }
    
    public List<CollisionBox> getCollidingUnwalkableTileBoxes(CollisionBox collisionBox) {
        int gridX = (int) (collisionBox.getBounds().getX() / Game.SPRITE_SIZE);
        int gridY = (int) (collisionBox.getBounds().getY() / Game.SPRITE_SIZE);

        List<CollisionBox> collidingUnwalkableTileBoxes = new ArrayList<>();

        for(int x = gridX - 1; x < gridX + 2; x++) {
            for(int y = gridY - 1; y < gridY + 2; y++) {
                if(gridWithinBounds(x, y) && !getTile(x, y).isWalkable()) {
                    CollisionBox gridCollisionBox = getGridCollisionBox(x, y);
                    if(collisionBox.collidesWith(gridCollisionBox)) {
                        collidingUnwalkableTileBoxes.add(gridCollisionBox);
                    }
                }
            }
        }

        return collidingUnwalkableTileBoxes;
    }
    private CollisionBox getGridCollisionBox(int x, int y) {
        return new CollisionBox(new Rect(x * Game.SPRITE_SIZE, y * Game.SPRITE_SIZE, Game.SPRITE_SIZE, Game.SPRITE_SIZE));
    }
    public Tile getTile(int x, int y) {
        return tiles[x][y];
    }
    
	public List<Scenery> getSceneryList() {
		return sceneryList;
	}
	public void setSceneryList(List<Scenery> sceneryList) {
		this.sceneryList = sceneryList;
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
		stringBuilder.append(SECTION_DELIMITER);
		sceneryList.forEach(scenery->{
			stringBuilder.append(scenery.serialize());
			stringBuilder.append(COLUMN_DELIMITER);
		});
		
		return stringBuilder.toString();
	}

	@Override
	public void applySerializedData(String serializedData) {
		// Grabs data and splits based on delimiter set
		String[] tokens = serializedData.split(DELIMITER);
		String[] sections = serializedData.split(SECTION_DELIMITER);
		
		//tiles length is the first data after the class name, x  and y are immediately after one another
		tiles = new Tile[Integer.parseInt(tokens[1])][Integer.parseInt(tokens[2])];
		// Tiles section is nect, split based on section delimiter
		String tileSection = sections[1];
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
		 
			if(sections.length >2) {
				String scenerySection = sections[2];
				String[] serializedSceneries = scenerySection.split(COLUMN_DELIMITER);
				for(String serializedScenery : serializedSceneries) {
					Scenery scenery = new Scenery();
					scenery.applySerializedData(serializedScenery);
					sceneryList.add(scenery);
				}
			}
	}

}
