package state.editor.ui;

import java.awt.Color;

import core.Position;
import core.Size;
import entity.Scenery;
import gfx.SpriteLibrary;
import input.mouse.action.SceneryPlacer;
import ui.HorizontalContainer;
import ui.clickable.UIToolToggle;

public class UISceneryMenu extends HorizontalContainer{
	public UISceneryMenu(Size windowSize, SpriteLibrary spriteLibrary) {
		super(windowSize);
		backgroundColor = Color.DARK_GRAY;
		
		addUIComponent(new UIToolToggle(spriteLibrary.getImage("pinetree"), new SceneryPlacer(new Scenery(
				"pinetree",
				new Size(64, 93),
				new Position(32, 64),
				new Size(50, 24),
				new Position(24,0),
				false,
				spriteLibrary
		))));
	}
}
