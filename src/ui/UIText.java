package ui;

import core.Size;
import gfx.ImageUtils;
import state.State;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UIText extends UIComponent {

    private String text;
    private int fontSize;
    private int fontStyle;
    private String fontFamily;
    private Color color;

    private boolean dropShadow;
    private int dropShadowOffset;
    private Color shadowColor;

    private Font font;

    public UIText(String text) {
        this.text = text;
        this.fontSize = 16;
        this.fontStyle = Font.BOLD;
        this.fontFamily = "Joystix Monospace";
        this.color = Color.WHITE;

        this.dropShadow = true;
        this.dropShadowOffset = 2;
        this.shadowColor = new Color(140,140, 140);
    }

    @Override
    public Image getSprite() {
        BufferedImage image = (BufferedImage) ImageUtils.createCompatibleImage(size, ImageUtils.ALPHA_BIT_MASKED);
        Graphics2D graphics = image.createGraphics();
        graphics.setFont(font);

     // Since text gets drawn from bottom left we need to add an offset
        if(dropShadow) {
            graphics.setColor(shadowColor);
            graphics.drawString(text, padding.getLeft() + dropShadowOffset, fontSize + padding.getTop() + dropShadowOffset);
        }

        graphics.setColor(color);
        
        graphics.drawString(text, padding.getLeft(), fontSize + padding.getTop());

        graphics.dispose();
        return image;
    }

    @Override
    public void update(State state) {
        createFont();
        calculateSize();
    }


    private void createFont() {
        font = new Font(fontFamily, fontStyle, fontSize);
    }

	public void setText(String text) {
		this.text = text;
	}
	private void calculateSize() {
		// "Hacky" way to get fontmetrics. Since depending on font the width of the string will vary
        FontMetrics fontMetrics = new Canvas().getFontMetrics(font);
        int width = fontMetrics.stringWidth(text) + padding.getHorizontal();
        int height = fontMetrics.getHeight() + padding.getVertical();

        if(dropShadow) {
            width += dropShadowOffset;
        }

        size = new Size(width, height);
    }
}
