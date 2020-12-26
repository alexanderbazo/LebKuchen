package game.scenes.game;

import config.Display;
import game.actors.Enemy;
import game.levels.Level;
import game.scenes.BaseScene;
import utils.DebugInfo;

import java.util.Random;

public class EnemySpawner {

    private BaseScene host;
    private EnemySpawnListener listener;
    private Random random;
    private int ticksSinceLastSpawn;
    private Level currentLevel;
    private int spawnedEnemies;

    public EnemySpawner(EnemySpawnListener listener, BaseScene host) {
        this.listener = listener;
        this.host = host;
    }

    public void reset(Level level) {
        this.currentLevel = level;
        this.ticksSinceLastSpawn = Integer.MAX_VALUE;
        this.spawnedEnemies = 0;
        random = new Random();
    }

    public void update() {
        if(ticksSinceLastSpawn > currentLevel.enemySpawnDelay && spawnedEnemies < currentLevel.totalNumberOfEnemies) {
            int x = random.nextInt(Display.WINDOW_WIDTH);
            int y = random.nextInt(Display.WINDOW_HEIGHT);
            Enemy enemy = new Enemy(x, y, host);
            spawnedEnemies++;
            ticksSinceLastSpawn = 0;
            listener.onEnemySpawned(enemy);
            return;
        }
        ticksSinceLastSpawn++;
    }

}
