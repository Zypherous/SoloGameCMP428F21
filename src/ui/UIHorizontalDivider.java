package ui;

import core.Size;
import gfx.ImageUtils;
import state.State;

import java.awt.*;

public class UIHorizontalDivider extends UIComponent {

    private Image sprite;

    public UIHorizontalDivider() {
        size = new Size(2, 24);
        padding = new Spacing(0);
        margin = new Spacing(16, 0);
        generateSprite();
    }

    private void generateSprite() {
        sprite = ImageUtils.createCompatibleImage(size, ImageUtils.ALPHA_OPAQUE);
        Graphics2D graphics = (Graphics2D) sprite.getGraphics();

        graphics.setColor(Color.GRAY);
        graphics.fillRect(0, 0, size.getWidth(), size.getHeight());

        graphics.dispose();
    }

    @Override
    public Image getSprite() {
        return sprite;
    }

    @Override
    public void update(State state) {

    }
}
