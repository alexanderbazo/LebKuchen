package game.scenes.game;

import config.ColorScheme;
import config.Display;
import config.Weapons;
import de.ur.mi.oop.graphics.Circle;
import de.ur.mi.oop.graphics.Point;
import game.actors.Actor;
import game.actors.enemy.Enemy;
import game.actors.enemy.EnemyListener;
import game.actors.enemy.EnemySpawnListener;
import game.actors.enemy.EnemySpawner;
import game.actors.player.Player;
import game.actors.weapons.Projectile;
import game.actors.weapons.ProjectileListener;
import game.actors.weapons.Weapon;
import game.actors.weapons.WeaponListener;
import game.levels.Level;
import game.scenes.BaseScene;
import game.scenes.SceneListener;
import game.scenes.SceneType;
import game.world.GameWorld;
import utils.DebugInfo;

import java.util.ArrayList;

public class GameScene extends BaseScene implements GameWorld, EnemySpawnListener, EnemyListener, WeaponListener, ProjectileListener {

    public static final String SCENE_NAME = "Game Scene";

    private Level level;
    private Player player;
    private ArrayList<Actor> actorsToRemoveAfterFrame;
    private EnemySpawner spawner;
    private Weapon weapon;

    private int enemiesDestroyed = 0;

    public GameScene(SceneListener sceneListener) {
        super(SCENE_NAME, SceneType.GAME, sceneListener);
        setBackgroundColor(ColorScheme.LINEN);
        level = Level.next();
        player = new Player(Display.WINDOW_WIDTH / 2, Display.WINDOW_HEIGHT / 2, level.playerSpeed, this);
        addActor(player);
        spawner = new EnemySpawner(this, this);
        weapon = new Weapon("Pistol", Weapons.PISTOL_DAMAGE, Weapons.PISTOL_COOLDOWN, Weapons.PISTOL_SPLASH_RADIUS, Weapons.PISTOL_PROJECTILE_SPEED);
        weapon.setListener(this);
        weapon.addAmmunition(100);
        player.setWeapon(weapon);
        actorsToRemoveAfterFrame = new ArrayList<>();
    }

    @Override
    public void play() {
        spawner.reset(level);
    }

    @Override
    public void update() {
        super.update();
        spawner.update();
        weapon.tick();
    }

    @Override
    public void draw() {
        super.draw();
        removeObsoleteActors();
    }

    private void removeObsoleteActors() {
        for(Actor actor: actorsToRemoveAfterFrame) {
            removeActor(actor);
        }
        actorsToRemoveAfterFrame.clear();
    }

    public Point getPlayerPosition() {
        return new Point(player.getPosition().getXPos(), player.getPosition().getYPos());
    }

    @Override
    public void onEnemySpawned(Enemy enemy) {
        enemy.setListener(this);
        addActor(enemy);
        DebugInfo.set("Active enemies", getActorOfType(Enemy.class).size());
    }

    @Override
    public void onProjectileFired(Projectile projectile) {
        projectile.setListener(this);
        projectile.setWorld(this);
        addActor(projectile);
        DebugInfo.set("Active projectiles", getActorOfType(Projectile.class).size());
    }

    @Override
    public void onProjectileHitEnemies(Projectile projectile, ArrayList<Enemy> enemies) {
        actorsToRemoveAfterFrame.add(projectile);
        for(Enemy enemy: enemies) {
            enemy.hit(projectile.damage);
        }
    }

    @Override
    public void onProjectileLeftCanvas(Projectile projectile) {
        actorsToRemoveAfterFrame.add(projectile);
    }

    @Override
    public int getWidth() {
        return Display.WINDOW_WIDTH;
    }

    @Override
    public int getHeight() {
        return Display.WINDOW_HEIGHT;
    }

    @Override
    public ArrayList<Enemy> getEnemiesAt(float x, float y, float radius) {
        ArrayList<Enemy> enemies = new ArrayList<>();
        Circle bounds = new Circle(x, y, radius);
        for(Actor enemy: getActorOfType(Enemy.class)) {
            // @TODO Use hitBox instead of center to determine if enemies are within radius
            if(bounds.hitTest(enemy.getPosition().getXPos(), enemy.getPosition().getYPos())) {
                enemies.add((Enemy) enemy);
            }
        }
        return enemies;
    }

    @Override
    public void onEnemyDestroyed(Enemy enemy) {
        actorsToRemoveAfterFrame.add(enemy);
        DebugInfo.set("Enemies destroyed", ++enemiesDestroyed);
    }
}
