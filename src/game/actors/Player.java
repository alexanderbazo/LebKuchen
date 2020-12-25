package game.actors;

import config.ColorScheme;
import de.ur.mi.oop.colors.Color;
import de.ur.mi.oop.events.KeyPressedEvent;
import de.ur.mi.oop.events.KeyReleasedEvent;
import de.ur.mi.oop.graphics.Circle;
import game.input.KeyboardInputHandler;
import game.scenes.BaseScene;

public class Player extends Actor implements KeyboardInputHandler {

    private static final int radius = 15;

    private PlayerMovementSpeed currentSpeed = PlayerMovementSpeed.DEFAULT;
    private Circle body;

    public Player(int x, int y, PlayerMovementSpeed speed, BaseScene hostScene) {
        super(x, y, hostScene);
        currentSpeed = speed;
        body = new Circle(x, y, radius, ColorScheme.FLACESCENT);
    }

    @Override
    public void move(float x, float y) {
        super.move(x, y);
        body.move(x, y);
    }

    @Override
    public void draw() {
        body.draw();
    }

    @Override
    public void handleKeyPressed(KeyPressedEvent event) {
        switch(event.getKeyCode()) {
            case KeyPressedEvent.VK_W:
                move(0, -currentSpeed.speed);
                break;
            case KeyPressedEvent.VK_S:
                move(0, currentSpeed.speed);
                break;
            case KeyPressedEvent.VK_A:
                move(-currentSpeed.speed, 0);
                break;
            case KeyPressedEvent.VK_D:
                move(currentSpeed.speed, 0);
                break;
            default:
                break;
        }
    }

    @Override
    public void handleKeyReleased(KeyReleasedEvent event) {

    }
}
