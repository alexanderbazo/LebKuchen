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
        DebugInfo.getInstance().set("Player-X", getPosition().getXPos());
        DebugInfo.getInstance().set("Player-Y", getPosition().getYPos());
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

    @Override
    public void handleKeyPressed(KeyPressedEvent event) {
        PlayerMovementDirection newDirection = null;
        switch (event.getKeyCode()) {
            case KeyPressedEvent.VK_W:
                newDirection = PlayerMovementDirection.NORTH;
                break;
            case KeyPressedEvent.VK_S:
                newDirection = PlayerMovementDirection.SOUTH;
                break;
            case KeyPressedEvent.VK_A:
                newDirection = PlayerMovementDirection.WEST;
                break;
            case KeyPressedEvent.VK_D:
                newDirection = PlayerMovementDirection.EAST;
                break;
            default:
                break;
        }
        if(newDirection != null && !directions.contains(newDirection)) {
            directions.add(newDirection);
        }
    }

    @Override
    public void handleKeyReleased(KeyReleasedEvent event) {
        switch (event.getKeyCode()) {
            case KeyPressedEvent.VK_W:
                directions.remove(PlayerMovementDirection.NORTH);
                break;
            case KeyPressedEvent.VK_S:
                directions.remove(PlayerMovementDirection.SOUTH);
                break;
            case KeyPressedEvent.VK_A:
                directions.remove(PlayerMovementDirection.WEST);
                break;
            case KeyPressedEvent.VK_D:
                directions.remove(PlayerMovementDirection.EAST);
                break;
            default:
                break;
        }
    }
}
