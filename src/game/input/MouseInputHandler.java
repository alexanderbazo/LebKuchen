package game.input;

import de.ur.mi.oop.events.MouseDraggedEvent;
import de.ur.mi.oop.events.MouseMovedEvent;
import de.ur.mi.oop.events.MousePressedEvent;
import de.ur.mi.oop.events.MouseReleasedEvent;

public interface MouseInputHandler {

    void handleMousePressed(MousePressedEvent event);

    void handleMouseReleased(MouseReleasedEvent event);

    void handleMouseMoved(MouseMovedEvent event);

    void handleMouseDragged(MouseDraggedEvent event);

}
