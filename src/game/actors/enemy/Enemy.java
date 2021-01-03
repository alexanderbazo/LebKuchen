package game.actors.enemy;

import config.Assets;
import config.ColorScheme;
import de.ur.mi.oop.colors.Color;
import de.ur.mi.oop.graphics.Circle;
import de.ur.mi.oop.graphics.Image;
import de.ur.mi.oop.graphics.Point;
import de.ur.mi.oop.graphics.Rectangle;
import game.actors.Actor;
import game.actors.ui.Animation;
import game.scenes.BaseScene;
import game.scenes.game.GameScene;
import utils.Geometry;

import java.util.Random;

public class Enemy extends Actor {

    private static final int WIDTH = 48;
    private static final int HEIGHT = 48;
    private static final int HEALTH_BAR_WIDTH = 48;
    private static final int HEALTH_BAR_HEIGHT = 8;
    private static final int ANIMATION_SPEED_IN_FRAMES = 3;
    private static final Color HEALTH_BAR_BACKGROUND_COLOR = ColorScheme.GREY;
    private static final Color HEALTH_BAR_VALUE_COLOR = ColorScheme.FIRE_OPAL;
    private static final int MIN_TARGET_FUZZINES = -10;
    private static final int MAX_TARGET_FUZZINES = 10;
    private static final int MAX_HEALTH = 100;

    private float health;
    private EnemyMovementSpeed currentSpeed = EnemyMovementSpeed.DEFAULT;
    private Animation body;
    private Point targetFuzziness;
    private EnemyListener listener;
    private Rectangle healthBar;
    private Rectangle healthBarValue;

    public Enemy(int x, int y, BaseScene hostScene) {
        super(x, y, hostScene);
        // @TODO Think about shared random generator for all game objects
        Random random = new Random();
        this.health = MAX_HEALTH;
        body = new Animation(x, y, WIDTH, HEIGHT, Assets.ENEMY_SPRITE_PATHS, ANIMATION_SPEED_IN_FRAMES);
        targetFuzziness = new Point(random.nextInt(MAX_TARGET_FUZZINES - MIN_TARGET_FUZZINES) + MIN_TARGET_FUZZINES, random.nextInt(MAX_TARGET_FUZZINES - MIN_TARGET_FUZZINES) + MIN_TARGET_FUZZINES);
        healthBar = new Rectangle(x, y - HEALTH_BAR_HEIGHT, HEALTH_BAR_WIDTH, HEALTH_BAR_HEIGHT, HEALTH_BAR_BACKGROUND_COLOR);
        healthBarValue = new Rectangle(x, y - HEALTH_BAR_HEIGHT, HEALTH_BAR_WIDTH, HEALTH_BAR_HEIGHT, HEALTH_BAR_VALUE_COLOR);
        body.play();
    }

    public void setListener(EnemyListener listener) {
        this.listener = listener;
    }

    public void hit(float damage) {
        health -= damage;
        healthBarValue.setWidth(((HEALTH_BAR_WIDTH / 100f) * health));
        if(health <= 0) {
            listener.onEnemyDestroyed(this);
        }
    }

    @Override
    public Rectangle getHitBox() {
        return new Rectangle(body.getXPos(), body.getYPos(), body.getWidth(), body.getHeight());
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
        healthBar.move(x, y);
        healthBarValue.move(x, y);
    }

    @Override
    public void draw() {
        body.draw();
        if(health != MAX_HEALTH) {
            healthBar.draw();
            healthBarValue.draw();
        }
    }



}
