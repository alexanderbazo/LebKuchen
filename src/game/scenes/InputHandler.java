package game.scenes;

import de.ur.mi.oop.events.*;

public interface InputHandler {

    public void handleMousePressed(MousePressedEvent event);

    public void handleMouseReleased(MouseReleasedEvent event);

    public void handleMouseMoved(MouseMovedEvent event);

    public void handleMouseDragged(MouseDraggedEvent event);

    public void handleKeyPressed(KeyPressedEvent event);

    public void handleKeyReleased(KeyReleasedEvent event);

}
