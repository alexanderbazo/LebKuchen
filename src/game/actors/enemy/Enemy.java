package game.actors.enemy;

import config.ColorScheme;
import de.ur.mi.oop.graphics.Circle;
import de.ur.mi.oop.graphics.Point;
import game.actors.Actor;
import game.scenes.BaseScene;
import game.scenes.game.GameScene;
import utils.Geometry;

import java.util.Random;

public class Enemy extends Actor {

    private static final int radius = 10;
    private static final int MIN_TARGET_FUZZINES = -10;
    private static final int MAX_TARGET_FUZZINES = 10;

    private float health;
    private EnemyMovementSpeed currentSpeed = EnemyMovementSpeed.DEFAULT;
    private Circle body;
    private Point targetFuzziness;
    private EnemyListener listener;

    public Enemy(int x, int y, BaseScene hostScene) {
        super(x, y, hostScene);
        // @TODO Think about shared random generator for all game objects
        Random random = new Random();
        this.health = 100;
        body = new Circle(x, y, radius, ColorScheme.AMERICAN_PINK);
        targetFuzziness = new Point(random.nextInt(MAX_TARGET_FUZZINES - MIN_TARGET_FUZZINES) + MIN_TARGET_FUZZINES, random.nextInt(MAX_TARGET_FUZZINES - MIN_TARGET_FUZZINES) + MIN_TARGET_FUZZINES);
    }

    public void setListener(EnemyListener listener) {
        this.listener = listener;
    }

    public void hit(float damage) {
        health -= damage;
        if(health <= 0) {
            listener.onEnemyDestroyed(this);
        }
    }

    @Override
    public void update() {
        super.update();
        GameScene scene = (GameScene) getHost();
        Point targetPosition = new Point(scene.getPlayerPosition().getXPos() + targetFuzziness.getXPos(), scene.getPlayerPosition().getYPos() + targetFuzziness.getYPos());
        Point delta = Geometry.calculateMovementVector(getPosition(), targetPosition, currentSpeed.speed);
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
