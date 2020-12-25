package utils;

import de.ur.mi.oop.graphics.Point;

public abstract class Geometry {

    public static Point calculateMovementVector(Point position, Point target, float speed) {
        float angle = (float) Math.atan2(target.getYPos() - position.getYPos(), target.getXPos() - position.getXPos());
        float xMovement = (float) (Math.cos(angle) * speed);
        float yMovement = (float) (Math.sin(angle) * speed);
        return new Point(xMovement, yMovement);
    }
}
