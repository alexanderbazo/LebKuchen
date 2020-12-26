package game.actors;

import config.ColorScheme;
import de.ur.mi.oop.events.KeyPressedEvent;
import de.ur.mi.oop.events.KeyReleasedEvent;
import de.ur.mi.oop.graphics.Circle;
import de.ur.mi.oop.graphics.Point;
import game.input.KeyboardInputHandler;
import game.scenes.BaseScene;
import utils.DebugInfo;

import java.util.ArrayList;

public class Player extends Actor implements KeyboardInputHandler {

    private static final int radius = 15;

    private PlayerMovementSpeed currentSpeed = PlayerMovementSpeed.DEFAULT;
    private Circle body;
    private ArrayList<PlayerMovementDirection> directions;

    public Player(int x, int y, PlayerMovementSpeed speed, BaseScene hostScene) {
        super(x, y, hostScene);
        currentSpeed = speed;
        directions = new ArrayList<>();
        body = new Circle(x, y, radius, ColorScheme.FLACESCENT);
    }

    @Override
    public void move(float x, float y) {
        super.move(x, y);
        body.move(x, y);
        DebugInfo.set("Player-X", getPosition().getXPos());
        DebugInfo.set("Player-Y", getPosition().getYPos());
    }

    @Override
    public void draw() {
        body.draw();
    }

    @Override
    public void update() {
        super.update();
        Point newPosition = getMovementVector();
        move(newPosition.getXPos() * currentSpeed.speed, newPosition.getYPos() * currentSpeed.speed);
    }

    private Point getMovementVector() {
        Point vector = new Point(0, 0);
        for (PlayerMovementDirection direction : directions) {
            float x = vector.getXPos() + direction.x;
            float y = vector.getYPos() + direction.y;
            vector.setLocation(x, y);
        }
        return vector;
    }

    private void addDirection(PlayerMovementDirection direction) {
        if (!directions.contains(direction)) {
            directions.add(direction);
        }
    }

    private void removeDirection(PlayerMovementDirection direction) {
        directions.remove(direction);
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
    public void handleKeyPressed(KeyPressedEvent event) {
        switch (event.getKeyCode()) {
            case KeyPressedEvent.VK_W:
                addDirection(PlayerMovementDirection.NORTH);
                break;
            case KeyPressedEvent.VK_S:
                addDirection(PlayerMovementDirection.SOUTH);
                break;
            case KeyPressedEvent.VK_A:
                addDirection(PlayerMovementDirection.WEST);
                break;
            case KeyPressedEvent.VK_D:
                addDirection(PlayerMovementDirection.EAST);
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
            case KeyPressedEvent.VK_S:
                removeDirection(PlayerMovementDirection.SOUTH);
                break;
            case KeyPressedEvent.VK_A:
                removeDirection(PlayerMovementDirection.WEST);
                break;
            case KeyPressedEvent.VK_D:
                removeDirection(PlayerMovementDirection.EAST);
                break;
            case KeyPressedEvent.VK_SHIFT:
                deactivateFastMovement();
                break;
            default:
                break;
        }
    }
}
