package gfx;

import java.util.HashMap;
import java.util.Map;
import java.awt.*;

public class SpriteSheet {
	private Map<String, Image> animationSheets;
	
	public SpriteSheet() {
		this.animationSheets = new HashMap<>();
	}
	
	public void addSheet(String name, Image animationSheet) {
		animationSheets.put(name, animationSheet);
	}
	
	public Image get(String name) {
		return animationSheets.get(name);
	}
}
