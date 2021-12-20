package ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import core.Position;
import core.Size;
import gfx.ImageUtils;
import state.State;

public abstract class UIContainer extends UIComponent {

	protected boolean centerChildren;
    protected Color backgroundColor;

    protected Alignment alignment;
    protected Size windowSize;

    protected Size fixedSize;

    protected List<UIComponent> children;
    //Cache image for UI optimization
    protected Image sprite;

    public UIContainer(Size windowSize) {
        super();
        this.windowSize = windowSize;
        centerChildren = false;
        alignment = new Alignment(Alignment.Position.START, Alignment.Position.START);
        backgroundColor = new Color(0, 0, 0, 0);
        margin = new Spacing(5);
        padding = new Spacing(5);
        children = new ArrayList<>();
        calculateSize();
        calculatePosition();
    }

    protected abstract Size calculateContentSize();
    protected abstract void calculateContentPosition();

    private void calculateSize() {
        Size calculatedContentSize = calculateContentSize();
        size = fixedSize != null
                ? fixedSize
                : new Size(
                padding.getHorizontal() + calculatedContentSize.getWidth(),
                padding.getVertical() + calculatedContentSize.getHeight());
    }

    private void calculatePosition() {
        int x = padding.getLeft();
        if(alignment.getHorizontal().equals(Alignment.Position.CENTER)) {
            x = windowSize.getWidth() / 2 - size.getWidth() / 2;
        }
        if(alignment.getHorizontal().equals(Alignment.Position.END)) {
            x = windowSize.getWidth() - size.getWidth() - margin.getRight();
        }

        int y = padding.getTop();
        if(alignment.getVertical().equals(Alignment.Position.CENTER)) {
            y = windowSize.getHeight() / 2 - size.getHeight() / 2;
        }
        if(alignment.getVertical().equals(Alignment.Position.END)) {
            y = windowSize.getHeight() - size.getHeight() - margin.getBottom();
        }

        this.relativePosition = new Position(x, y);
        if(parent == null) {
        	this.absolutePosition = new Position(x, y);  
        }
        calculateContentPosition();
    }

    @Override
    public Image getSprite() {
    	return sprite;
    }

    @Override
    public void update(State state) {
        children.forEach(component -> component.update(state));
        calculateSize();
        calculatePosition();
        if(state.getTime().secondsDivisbleBy(0.01)) {
        	generateSprite();
        }
    }

    private void generateSprite() {
        sprite = ImageUtils.createCompatibleImage(size, ImageUtils.ALPHA_BIT_MASKED);
        Graphics2D graphics = (Graphics2D) sprite.getGraphics();

        graphics.setColor(backgroundColor);
        graphics.fillRect(0, 0, size.getWidth(), size.getHeight());

        for(UIComponent uiComponent : children) {
            graphics.drawImage(
                    uiComponent.getSprite(),
                    uiComponent.getRelativePosition().intX(),
                    uiComponent.getRelativePosition().intY(),
                    null
            );
        }

        graphics.dispose();
        		
	}

	public void addUIComponent(UIComponent uiComponent) {
        children.add(uiComponent);
        uiComponent.setParent(this);
    }

    public void setBackgroundColor(Color color) {
        backgroundColor = color;
    }

    public void setAlignment(Alignment alignment) {
        this.alignment = alignment;
    }

    public void setFixedSize(Size fixedSize) {
        this.fixedSize = fixedSize;
    }

	public boolean isCenterChildren() {
		return centerChildren;
	}

	public void setCenterChildren(boolean centerChildren) {
		this.centerChildren = centerChildren;
	}
    
	   protected void clear() {
	        children.clear();
	    }

	public void removeComponent(UIComponent component) {
		this.children.remove(component);
	}

	public boolean hasComponent(UIComponent component) {
		return children.contains(component);
	}
}
