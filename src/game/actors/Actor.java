package game.actors;

import de.ur.mi.oop.graphics.Point;
import game.scenes.BaseScene;

import java.util.ArrayList;

public abstract class Actor {

    private BaseScene hostScene;
    private Point position;

    public Actor(int x, int y, BaseScene hostScene) {
        this.hostScene = hostScene;
        position = new Point(x, y);
    }

    public BaseScene getHost() {
        return hostScene;
    }

    public void setPosition(int x, int y) {
        this.position.setLocation(x, y);
    }

    public void move(float dx, float dy) {
        this.position.move(dx, dy);
    }

    public Point getPosition() {
        return new Point(position.getXPos(), position.getYPos());
    }

    public void update() {

    }

    public void draw() {

    }


}
