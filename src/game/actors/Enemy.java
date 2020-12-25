package game.actors;

import config.ColorScheme;
import de.ur.mi.oop.graphics.Circle;
import de.ur.mi.oop.graphics.Point;
import game.scenes.BaseScene;
import game.scenes.game.GameScene;
import utils.Geometry;

public class Enemy extends Actor {

    private static final int radius = 10;

    private EnemyMovementSpeed currentSpeed = EnemyMovementSpeed.DEFAULT;
    private Circle body;

    public Enemy(int x, int y, BaseScene hostScene) {
        super(x, y, hostScene);
        body = new Circle(x, y, radius, ColorScheme.AMERICAN_PINK);
    }

    @Override
    public void update() {
        super.update();
        GameScene scene = (GameScene) getHost();
        Point delta = Geometry.calculateMovementVector(getPosition(), scene.getPlayerPosition(), currentSpeed.speed);
        move(delta.getXPos(), delta.getYPos());
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

}
