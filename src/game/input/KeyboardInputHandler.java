package game.input;

import de.ur.mi.oop.events.KeyPressedEvent;
import de.ur.mi.oop.events.KeyReleasedEvent;

public interface KeyboardInputHandler {

    void handleKeyPressed(KeyPressedEvent event);
    void handleKeyReleased(KeyReleasedEvent event);

}
