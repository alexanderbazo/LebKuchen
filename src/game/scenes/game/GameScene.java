package game.scenes.game;

import config.ColorScheme;
import config.Display;
import de.ur.mi.oop.graphics.Point;
import game.actors.Enemy;
import game.actors.Player;
import game.levels.Level;
import game.scenes.BaseScene;
import game.scenes.SceneListener;
import game.scenes.SceneType;

import java.util.ArrayList;

public class GameScene extends BaseScene implements EnemySpawnListener {

    public static final String SCENE_NAME = "Game Scene";

    private Level level;
    private Player player;
    private ArrayList<Enemy> enemies;
    private EnemySpawner spawner;

    public GameScene(SceneListener sceneListener) {
        super(SCENE_NAME, SceneType.GAME, sceneListener);
        setBackgroundColor(ColorScheme.LINEN);
        level = Level.next();
        player = new Player(Display.WINDOW_WIDTH / 2, Display.WINDOW_HEIGHT / 2, level.playerSpeed, this);
        enemies = new ArrayList<>();
        spawner = new EnemySpawner(this, this);
        spawner.setLevel(level);
        addActor(player);
    }

    @Override
    public void update() {
        super.update();
        if (enemies.size() < level.totalNumberOfEnemies) {
            spawner.update();
        }
    }

    public Point getPlayerPosition() {
        return new Point(player.getPosition().getXPos(), player.getPosition().getYPos());
    }

    @Override
    public void onEnemySpawned(Enemy enemy) {
        enemies.add(enemy);
        addActor(enemy);
    }
}
