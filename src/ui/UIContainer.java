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
    
    protected Alignment alignment;
    protected Size windowSize;
    
    protected List<UIComponent> children;
    
    public UIContainer(Size windowSize) {
        super();
        this.windowSize = windowSize;
        alignment = new Alignment(Alignment.Position.CENTER, Alignment.Position.START);
        backgroundColor = new Color(0, 0, 0, 0);
        margin = new Spacing(15);
        padding = new Spacing(5);
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
    	
    	int x = padding.getLeft();
    	if(alignment.getHorizontal().equals(Alignment.Position.CENTER)) {
    		// Places at center since things are drawn from left top
    		x = windowSize.getWidth()/2 - size.getWidth()/2;
    	}
    	if(alignment.getHorizontal().equals(Alignment.Position.END)) {
    		// Places at end
    		x = windowSize.getWidth() - size.getWidth() - margin.getRight();
    	}
    	
    	int y = padding.getTop();
    	if(alignment.getVertical().equals(Alignment.Position.CENTER)) {
    		// Places at center since things are drawn from left top
    		y = windowSize.getHeight()/2 - size.getHeight()/2;
    	}
    	if(alignment.getVertical().equals(Alignment.Position.END)) {
    		// Places at end
    		y = windowSize.getHeight() - size.getHeight() - margin.getTop();
    	}
    	
        this.position = new Position(x, y);
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
    
    public void addUIComponent(UIComponent uiComponent) {
    	children.add(uiComponent);
    }

	public void setBackgroundColor(Color color) {
		this.backgroundColor = color;
		
	}

	public Alignment getAlignment() {
		return alignment;
	}

	public void setAlignment(Alignment alignment) {
		this.alignment = alignment;
	}
	
	
}
