package gfx;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SpriteLibrary {

    private Map<String, SpriteSheet> spriteSheets;
    private Map<String, Image> images;

    public SpriteLibrary() {
        spriteSheets = new HashMap<>();
        images = new HashMap<>();
        loadSpritesFromDisk();
    }

    private void loadSpritesFromDisk() {
        loadSpriteSets("/sprites/units");
        loadImages("/sprites/tiles");
        loadImages("/sprites/effects");
    }

    private void loadImages(String path) {
        String[] imagesInFolder = getImagesInFolder(path);

        for(String filename: imagesInFolder) {
            images.put(
                    filename.substring(0, filename.length() - 4),
                    ImageUtils.loadImage(path + "/" + filename));
        }
    }

    private void loadSpriteSets(String path) {
        String[] folderNames = getFolderNames(path);

        for(String folderName: folderNames) {
            SpriteSheet spriteSheet = new SpriteSheet();
            String pathToFolder = path + "/" + folderName;
            String[] sheetsInFolder = getImagesInFolder(pathToFolder);

            for(String sheetName: sheetsInFolder) {
                spriteSheet.addSheet(
                        sheetName.substring(0, sheetName.length() - 4),
                        ImageUtils.loadImage(pathToFolder + "/" + sheetName));
            }

            spriteSheets.put(folderName, spriteSheet);
        }
    }

    private String[] getImagesInFolder(String basePath) {
        URL resource = SpriteLibrary.class.getResource(basePath);
        File file = new File(resource.getFile());
        return file.list((current, name) -> new File(current, name).isFile());
    }

    private String[] getFolderNames(String basePath) {
        URL resource = SpriteLibrary.class.getResource(basePath);
        File file = new File(resource.getFile());
        return file.list((current, name) -> new File(current, name).isDirectory());
    }

    public SpriteSheet getSpriteSheet(String name) {
        return spriteSheets.get(name);
    }

    public Image getImage(String name) {
        return images.get(name);
    }
}
