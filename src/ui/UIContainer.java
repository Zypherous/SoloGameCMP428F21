 package ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.List;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import core.Position;
import core.Size;
import game.state.State;
import gfx.ImageUtils;

public abstract class UIContainer extends UIComponent {

    private Color backgroundColor;
    
    protected List<UIComponent> children;
    
    public UIContainer() {
        super();
        backgroundColor = new Color(255, 0, 0);
        margin = new Spacing(15);
        children = new ArrayList<>();
        calculateSize();
        calculatePosition();
    }
    
    protected abstract Size calculateContentSize();
    protected abstract void calculateContentPosition();

    private void calculateSize() {
    	Size calculatedContentSize = calculateContentSize();
        size = new Size(
	        		padding.getHorizontal() + calculatedContentSize.getWidth(), 
	        		padding.getVertical() + calculatedContentSize.getHeight()
        		);
    }

    private void calculatePosition() {
        position = new Position(margin.getLeft(), margin.getTop());
        calculateContentPosition();
    }

    @Override
    public Image getSprite() {
        BufferedImage image = (BufferedImage) ImageUtils.createCompatibleImage(size, ImageUtils.ALPHA_BIT_MASKED);
        Graphics2D graphics = image.createGraphics();

        graphics.setColor(backgroundColor);
        graphics.fillRect(0, 0, size.getWidth(), size.getHeight());
        
        for(UIComponent uiComponent : children) {
        	graphics.drawImage(
        			uiComponent.getSprite(),
        			(int)uiComponent.getPosition().getX(),
        			(int)uiComponent.getPosition().getY(),
        			null
        	);
        }

        graphics.dispose();
        return image;
    }

    @Override
    public void update(State state) {
    	children.forEach(component -> component.update(state));
        calculateSize();
        calculatePosition();
    }
    
    public void addUicomponent(UIComponent uiComponent) {
    	children.add(uiComponent);
    }

	public void setBackgroundColor(Color color) {
		this.backgroundColor = color;
		
	}
}
