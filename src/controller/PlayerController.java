package controller;
import java.awt.event.KeyEvent;

import input.Input;

public class PlayerController implements EntityController{
	private Input input;
	
	public PlayerController(Input input) {
		this.input = input;
	}
	@Override
	public boolean isRequestingLeft() {
		return input.isCurrentlyPressed(KeyEvent.VK_LEFT);
	}

	@Override
	public boolean isRequestingRight() {
		return input.isCurrentlyPressed(KeyEvent.VK_RIGHT);
	}

	@Override
	public boolean isRequestingUp() {
		return input.isCurrentlyPressed(KeyEvent.VK_UP);
	}

	@Override
	public boolean isRequestingDown() {
		return input.isCurrentlyPressed(KeyEvent.VK_DOWN);
	}

}
