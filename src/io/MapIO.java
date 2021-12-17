package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import gfx.SpriteLibrary;
import map.GameMap;

public class MapIO {


    public static void save(GameMap map, String filePath) {
    	//No longer needed since the file chooser has been implemented
//        final URL urlToResourcesFolder = MapIO.class.getResource("/");
//        File mapsFolder = new File(urlToResourcesFolder.getFile() + "/maps/");

//        if(!mapsFolder.exists()) {
//            mapsFolder.mkdir();
//        }

//        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(mapsFolder.toString() + "/map.wrl"))) {
//            bufferedWriter.write(map.serialize());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    	
    	PersistableIO.save(map, filePath);
    }

    public static GameMap load(SpriteLibrary spriteLibrary, String filePath) {
    	GameMap gameMap = PersistableIO.load(GameMap.class, filePath);
    	gameMap.reloadGraphics(spriteLibrary);
    	return gameMap;
    	
    	// Since the persistable class was introduced, no longer need to open the file reader here.
    	//Instead we load using ther persistableIO, by including the filepath and then reload ther
    	// returned gamemap.
//        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(MapIO.class.getResource("/maps/map.wrl").getFile()))) {
//            GameMap map = new GameMap();
//
//            StringBuilder stringBuilder = new StringBuilder();
//            String line;
//            while((line = bufferedReader.readLine()) != null) {
//                stringBuilder.append(System.lineSeparator());
//                stringBuilder.append(line);
//            }
//
//            map.applySerializedData(stringBuilder.toString());
//            map.reloadGraphics(spriteLibrary);
//            return map;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        return null;
    }
}
