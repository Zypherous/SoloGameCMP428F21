package display;

import java.util.Optional;

import core.Position;
import core.Size;
import entity.GameObject;
import entity.Rect;
import game.Game;
import state.State;

public class Camera {

    private static final int RENDER_BUFFER = 2 * Game.SPRITE_SIZE;

    private Position position;
    private Size windowSize;

    private Rect viewBounds;

    private Optional<GameObject> objectWithFocus;

    public Camera(Size windowSize) {
        this.position = new Position(0, 0);
        this.windowSize = windowSize;
        calculateViewBounds();
    }

    private void calculateViewBounds() {
        viewBounds = new Rect(
                (int)position.getX(),
                (int)position.getY(),
                windowSize.getWidth() + RENDER_BUFFER,
                windowSize.getHeight() + RENDER_BUFFER
        );
    }

    public void focusOn(GameObject object) {
        this.objectWithFocus = Optional.of(object);
    }

    public void update(State state) {
        if(objectWithFocus.isPresent()) {
            Position objectPosition = objectWithFocus.get().getPosition();

            this.position.setX(objectPosition.getX() - windowSize.getWidth() / 2);
            this.position.setY(objectPosition.getY() - windowSize.getHeight() / 2);

            clampWithinBounds(state);
            calculateViewBounds();
        }
    }

    private void clampWithinBounds(State state) {
        if(position.getX() < 0) {
            position.setX(0);
        }

        if(position.getY() < 0) {
            position.setY(0);
        }

        if(position.getX() + windowSize.getWidth() > state.getGameMap().getWidth()) {
            position.setX(state.getGameMap().getWidth() - windowSize.getWidth());
        }

        if(position.getY() + windowSize.getHeight() > state.getGameMap().getHeight()) {
            position.setY(state.getGameMap().getHeight() - windowSize.getHeight());
        }
    }

    public Position getPosition() {
        return position;
    }

    public boolean isInView(GameObject gameObject) {
        return viewBounds.overlaps(gameObject);
    }

    public Size getWindowSize() {
        return windowSize;
    }
}
