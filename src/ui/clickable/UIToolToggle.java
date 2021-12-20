package ui.clickable;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import gfx.ImageUtils;
import input.mouse.action.MouseAction;
import state.State;
import ui.UIImage;

public class UIToolToggle extends UIClickable {

    private UIImage image;
    private BufferedImage activeSprite;
    private MouseAction mouseAction;
    private boolean active;

    public UIToolToggle(Image image, MouseAction mouseAction) {					// Scales the tile menu tiles
        this.image = new UIImage(image/*.getScaledInstance(32, 32, Image.SCALE_AREA_AVERAGING)*/);
        this.mouseAction = mouseAction;
        size = this.image.getSize();
        generateActiveSprite();
    }

    private void generateActiveSprite() {
        activeSprite = (BufferedImage) ImageUtils.createCompatibleImage(size, ImageUtils.ALPHA_OPAQUE);
        Graphics2D graphics = activeSprite.createGraphics();

        // Draw base image
        graphics.drawImage(image.getSprite(), 0, 0, null);

        // White color with an alpha for transparency
        graphics.setColor(new Color(255, 255, 255, 75));
        //For blending the source and destination colors to achieve blend/transparency effects
        graphics.setComposite(AlphaComposite.SrcOver);
        graphics.fillRect(0, 0, size.getWidth(), size.getHeight());

        // Draw border
        graphics.setColor(Color.WHITE);
        graphics.setStroke(new BasicStroke(2));
        
        //Creates a nicer inside border since stroke seems to grow past the 0,0 coordinate
        graphics.drawRect(1, 1, size.getWidth() - 2, size.getHeight() - 2);

        graphics.dispose();
    }

    @Override
    public void update(State state) {
        super.update(state);
        active = state.getMouseHandler().getPrimaryButtonAction().equals(mouseAction);
    }

    @Override
    protected void onFocus(State state) {

    }

    @Override
    public void onClick(State state) {
        state.getMouseHandler().switchPrimaryButtonAction(mouseAction);
    }

    @Override
    public void onDrag(State state) {

    }

    @Override
    public Image getSprite() {
    	if(active) {
    		return activeSprite;
    	}
    	if(hasFocus) {
    		Image imageWithFocus = ImageUtils.createCompatibleImage(image.getSize(), ImageUtils.ALPHA_OPAQUE);
    		Graphics2D graphics =(Graphics2D) imageWithFocus.getGraphics();
    		graphics.drawImage(image.getSprite(),0,0,null);
    		graphics.setColor(new Color(255,255,255,75));
    		graphics.fillRect(0, 0, image.getSize().getWidth(), image.getSize().getHeight());
    		
    		graphics.dispose();
    		return  imageWithFocus;   		
    	}
        return  image.getSprite();
    }

	@Override
	public void onRelease(State state) {
		// TODO Auto-generated method stub
		
	}
}
