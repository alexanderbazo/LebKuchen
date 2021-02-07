package game.input;

import de.ur.mi.oop.events.KeyPressedEvent;
import de.ur.mi.oop.events.KeyReleasedEvent;

public interface KeyboardInputHandler {

    public void handleKeyPressed(KeyPressedEvent event);

    public void handleKeyReleased(KeyReleasedEvent event);

}