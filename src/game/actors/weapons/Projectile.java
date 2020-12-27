package game.actors.weapons;

import config.ColorScheme;
import de.ur.mi.oop.colors.Color;
import de.ur.mi.oop.graphics.Circle;
import de.ur.mi.oop.graphics.Point;
import de.ur.mi.oop.graphics.Rectangle;
import game.actors.Actor;
import game.actors.enemy.Enemy;
import game.scenes.BaseScene;
import game.world.GameWorld;
import utils.Geometry;

import java.util.ArrayList;

public class Projectile extends Actor {

    private final static int PROJECTILE_RADIUS = 5;
    private final static Color PROJECTILE_COLOR = ColorScheme.GREY;

    public final float damage;
    public final float speed;
    public final float splashRadius;
    private final Point velocity;
    private final Circle body;
    private ProjectileListener listener;
    private GameWorld world;

    public Projectile(int x, int y, BaseScene host, Point target, float damage, float speed, float splashRadius) {
        super(x, y, host);
        this.damage = damage;
        this.speed = speed;
        this.splashRadius = splashRadius;
        velocity = Geometry.calculateMovementVector(new Point(x,y), target, speed);
        body = new Circle(x, y, PROJECTILE_RADIUS, PROJECTILE_COLOR);
    }

    public void setListener(ProjectileListener listener) {
        this.listener = listener;
    }

    public void setWorld(GameWorld world) {
        this.world = world;
    }

    @Override
    public void update() {
        super.update();
        body.move(velocity.getXPos(), velocity.getYPos());
        Rectangle bounds = new Rectangle(0, 0, world.getWidth(), world.getHeight());
        if(!bounds.hitTest(body.getXPos(), body.getYPos())) {
            listener.onProjectileLeftCanvas(this);
        }
        ArrayList<Enemy> hitEnemies = world.getEnemiesAt(body.getXPos(), body.getYPos(), PROJECTILE_RADIUS);
        if(hitEnemies.size() == 0) {
            return;
        }
        ArrayList<Enemy> nearbyEnemies = world.getEnemiesAt(body.getXPos(), body.getYPos(), splashRadius);
        listener.onProjectileHitEnemies(this, nearbyEnemies);
    }

    @Override
    public void draw() {
        super.draw();
        body.draw();
    }
}
