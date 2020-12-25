package game.actors;

import de.ur.mi.oop.graphics.Point;
import game.scenes.BaseScene;

public abstract class Actor {

    private final BaseScene hostScene;
    private Point position;

    public Actor(int x, int y, BaseScene hostScene) {
        this.hostScene = hostScene;
        position = new Point(x, y);
    }

    public BaseScene getHost() {
        return hostScene;
    }

    public void setPosition(float x, float y) {
        this.position.setLocation(x, y);
    }

    public void move(float x, float y) {
        this.position.move(x, y);
    }

    public Point getPosition() {
        return new Point(position.getXPos(), position.getYPos());
    }
    
    public void update() {
        
    }
    
    public void draw() {
        
    }
}
