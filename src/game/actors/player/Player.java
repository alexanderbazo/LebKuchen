package game.actors.player;

import config.ColorScheme;
import de.ur.mi.oop.events.*;
import de.ur.mi.oop.graphics.Circle;
import de.ur.mi.oop.graphics.Point;
import de.ur.mi.oop.graphics.Rectangle;
import game.actors.Actor;
import game.actors.weapons.Weapon;
import game.input.KeyboardInputHandler;
import game.input.MouseInputHandler;
import game.scenes.BaseScene;
import game.world.GameWorld;
import utils.DebugInfo;

import java.util.ArrayList;

public class Player extends Actor implements KeyboardInputHandler, MouseInputHandler {

    private static final int radius = 15;

    private PlayerMovementSpeed currentSpeed = PlayerMovementSpeed.DEFAULT;
    private Circle body;
    private ArrayList<PlayerMovementDirection> directions;
    private Weapon weapon;
    private GameWorld world;

    public Player(int x, int y, PlayerMovementSpeed speed, BaseScene hostScene) {
        super(x, y, hostScene);
        currentSpeed = speed;
        directions = new ArrayList<>();
        body = new Circle(x, y, radius, ColorScheme.FLACESCENT);
    }

    public void setWorld(GameWorld world) {
        this.world = world;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
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
        if (body.getLeftBorder() < 0 || body.getTopBorder() < 0 || body.getRightBorder() > world.getWidth() || body.getBottomBorder() > world.getHeight()) {
            move(-(newPosition.getXPos() * currentSpeed.speed), -(newPosition.getYPos() * currentSpeed.speed));
        }
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
    public Rectangle getHitBox() {
        return new Rectangle(body.getXPos(), body.getYPos(), body.getWidth(), body.getHeight());
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


    @Override
    public void handleMousePressed(MousePressedEvent event) {

    }

    @Override
    public void handleMouseReleased(MouseReleasedEvent event) {
        weapon.fire(getHost(), getPosition(), new Point(event.getXPos(), event.getYPos()));
    }

    @Override
    public void handleMouseMoved(MouseMovedEvent event) {

    }

    @Override
    public void handleMouseDragged(MouseDraggedEvent event) {

    }
}
