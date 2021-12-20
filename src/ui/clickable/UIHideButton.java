package ui.clickable;

import java.awt.Color;

import core.Size;
import input.mouse.MouseConsumer;
import state.State;
import ui.UIComponent;
import ui.UIContainer;

public class UIHideButton extends UIButton  {
	
	private UIContainer parentContainer;
	private UIComponent componentToHide;
	public UIHideButton(UIContainer parentContainer, UIComponent componentToHide) {
		super("↓", addOrRemove(parentContainer,componentToHide));
		this.parentContainer = parentContainer;
		this.componentToHide = componentToHide;
		container.setFixedSize(new Size(30, 30));
		backgroundColor = Color.DARK_GRAY;
	}
	
	private static ClickAction addOrRemove(UIContainer parentContainer, UIComponent componentToHide) {
		return state -> {
			if(parentContainer.hasComponent(componentToHide)) {
				parentContainer.removeComponent(componentToHide);
			}
			else {
				parentContainer.addUIComponent(componentToHide);
			}
		};
	}
	
	@Override
	public void update(State state) {
		super.update(state);
		label.setText("↓");
		
		if(!parentContainer.hasComponent(componentToHide)) {
			label.setText("↑");
		}
	}
}
