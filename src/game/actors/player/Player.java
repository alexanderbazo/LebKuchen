package game.actors.player;

import config.ColorScheme;
import de.ur.mi.oop.events.KeyPressedEvent;
import de.ur.mi.oop.events.KeyReleasedEvent;
import de.ur.mi.oop.graphics.Circle;
import de.ur.mi.oop.graphics.Point;
import game.actors.Actor;
import game.input.KeyboardInputHandler;
import game.scenes.BaseScene;

import java.util.ArrayList;

public class Player extends Actor implements KeyboardInputHandler {

    private static final int PLAYER_BODY_RADIUS = 15;
    private PlayerMovementSpeed currentSpeed;
    private ArrayList<PlayerMovementDirection> currentDirections;

    private Circle body;

    public Player(int x, int y, BaseScene hostScene) {
        super(x, y, hostScene);
        body = new Circle(x, y, PLAYER_BODY_RADIUS, ColorScheme.FLACESCENT);
        currentSpeed = PlayerMovementSpeed.DEFAULT;
        currentDirections = new ArrayList<>();
    }

    private void addDirection(PlayerMovementDirection direction) {
        if (currentDirections.contains(direction)) {
            return;
        }
        currentDirections.add(direction);
    }

    private void removeDirection(PlayerMovementDirection direction) {
        currentDirections.remove(direction);
    }

    private void activateFastMovement() {
        if (currentSpeed == PlayerMovementSpeed.HIGH) {
            return;
        }
        currentSpeed = PlayerMovementSpeed.HIGH;
    }

    private void deactivateFastMovement() {
        currentSpeed = PlayerMovementSpeed.DEFAULT;
    }

    @Override
    public void move(float dx, float dy) {
        super.move(dx, dy);
        body.move(dx, dy);
    }

    @Override
    public void update() {
        Point movementVector = getMovementVector();
        move(movementVector.getXPos() * currentSpeed.speed, movementVector.getYPos() * currentSpeed.speed);
    }

    private Point getMovementVector() {
        Point vector = new Point(0, 0);
        for (PlayerMovementDirection direction : currentDirections) {
            vector.move(direction.x, direction.y);
        }

        return vector;
    }

    @Override
    public void draw() {
        body.draw();
    }

    @Override
    public void handleKeyPressed(KeyPressedEvent event) {
        switch (event.getKeyCode()) {
            case KeyPressedEvent.VK_W:
                addDirection(PlayerMovementDirection.NORTH);
                break;
            case KeyPressedEvent.VK_D:
                addDirection(PlayerMovementDirection.EAST);
                break;
            case KeyPressedEvent.VK_S:
                addDirection(PlayerMovementDirection.SOUTH);
                break;
            case KeyPressedEvent.VK_A:
                addDirection(PlayerMovementDirection.WEST);
                break;
            case KeyPressedEvent.VK_SHIFT:
                activateFastMovement();
                break;
            default:
                break;
        }
    }

    @Override
    public void handleKeyReleased(KeyReleasedEvent event) {
        switch (event.getKeyCode()) {
            case KeyPressedEvent.VK_W:
                removeDirection(PlayerMovementDirection.NORTH);
                break;
            case KeyPressedEvent.VK_D:
                removeDirection(PlayerMovementDirection.EAST);
                break;
            case KeyPressedEvent.VK_S:
                removeDirection(PlayerMovementDirection.SOUTH);
                break;
            case KeyPressedEvent.VK_A:
                removeDirection(PlayerMovementDirection.WEST);
                break;
            case KeyPressedEvent.VK_SHIFT:
                deactivateFastMovement();
                break;
            default:
                break;
        }
    }
}
