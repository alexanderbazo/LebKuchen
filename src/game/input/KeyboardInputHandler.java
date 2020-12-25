package game.input;

import de.ur.mi.oop.events.*;

public interface KeyboardInputHandler {

    public void handleKeyPressed(KeyPressedEvent event);

    public void handleKeyReleased(KeyReleasedEvent event);

}