package input;

import core.Position;

import java.awt.event.*;

public class Input implements KeyListener, MouseListener, MouseMotionListener {

    private Position mousePosition;
    private boolean mouseClicked;
    private boolean mousePressed;
    private boolean mouseReleased;
    private boolean rightMouseClicked;
    private boolean rightMousePressed;
    private boolean rightMouseReleased;
    private boolean wheelMouseClicked;
    private boolean wheelMousePressed;
    private boolean wheelMouseReleased;

    // Array holding information of what key is currently being pressed
    // and one that determines when a button has been pressed
    
    private boolean[] currentlyPressed;
    private boolean[] pressed;

    
    //Constructor that initiates input and provides an array in excess size for receiving 
    // input booleans, as well as instantiating the position of the mouse to "off screen"
    // since upon creation, the various elements come into existence at position 0,0
    // causing the menu sounds to be played as if you hovered over an option
    public Input() {
        pressed = new boolean[1000];
        currentlyPressed = new boolean[1000];
        mousePosition = new Position(-1, -1);
    }

    // Checks to see if the button was pressed and if it is currently being pressed to return true
    // other wise it returns false if you continue to hold it down thereby allowing differentiating
    // between a single press and the in ability of a human to press for a "single  update"
    public boolean isPressed(int keyCode) {
        if(!pressed[keyCode] && currentlyPressed[keyCode]) {
            pressed[keyCode] = true;
            return true;
        }

        return false;
    }

    // Check for if button is held down
    public boolean isCurrentlyPressed(int keyCode) {
        return currentlyPressed[keyCode];
    }
    
    

    // Clears the input of the Mouse to allow for setting flags without registering multiple clicks
    public void clearMouseClick() {
        mouseClicked = false;
        rightMouseClicked = false;
        wheelMouseClicked = false;
        mouseReleased = false;
        wheelMouseReleased = false;
        rightMouseReleased = false;
    }

    // Returns the position of the mouse stored in a custom class holding an x and y double
    public Position getMousePosition() {
        return mousePosition;
    }

    // Check for if the mouse is clicked or not
    public boolean isMouseClicked() {
        return mouseClicked;
    }

    // standard check for mouse pressed
    public boolean isMousePressed() {
        return mousePressed;
    }

    // standard check for right mouse clicked
    public boolean isRightMouseClicked() {
		return rightMouseClicked;
	}

    // standard check for mouse pressed
	public boolean isRightMousePressed() {
		return rightMousePressed;
	}

	// standard check for wheel button on mouse clicked
	public boolean isWheelMouseClicked() {
		return wheelMouseClicked;
	}

	// standard check for wheel button on mouse pressed
	public boolean isWheelMousePressed() {
		return wheelMousePressed;
	}

	// The next three check for the release of mouse buttons
	public boolean isMouseReleased() {
		return mouseReleased;
	}
	public boolean isRightMouseReleased() {
		return rightMouseReleased;
	}

	public boolean isWheelMouseReleased() {
		return wheelMouseReleased;
	}

	// This is where the listening is done  and the input information is
	// obtained by Java
	@Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        currentlyPressed[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        currentlyPressed[e.getKeyCode()] = false;
        pressed[e.getKeyCode()] = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
    	mousePressed = e.getButton() == MouseEvent.BUTTON1;
    	wheelMousePressed = e.getButton() == MouseEvent.BUTTON2;
    	rightMousePressed = e.getButton() == MouseEvent.BUTTON3;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    	if(e.getButton() == MouseEvent.BUTTON1) {
    		mouseClicked = true;
    		mousePressed = false;
    		mouseReleased = true;
    	}
    	if(e.getButton() == MouseEvent.BUTTON2) {
    		wheelMouseClicked = true;
    		wheelMousePressed = false;
    		wheelMouseReleased = true;
    	}
    	if(e.getButton() == MouseEvent.BUTTON3) {
    		rightMouseClicked = true;
    		rightMousePressed = false;
    		rightMouseReleased = true;
    	}
    	
    	
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {
        mousePosition = new Position(e.getPoint().getX(), e.getPoint().getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mousePosition = new Position(e.getPoint().getX(), e.getPoint().getY());
    }

}
