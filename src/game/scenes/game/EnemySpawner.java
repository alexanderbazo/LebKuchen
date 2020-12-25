package game.scenes.game;

import config.Display;
import game.actors.Enemy;
import game.levels.Level;
import game.scenes.BaseScene;

import java.util.Random;

public class EnemySpawner {

    private BaseScene host;
    private EnemySpawnListener listener;
    private Random random;
    private int ticksSinceLastSpawn;
    private Level currentLevel;

    public EnemySpawner(EnemySpawnListener listener, BaseScene host) {
        this.listener = listener;
        this.host = host;
        this.ticksSinceLastSpawn = Integer.MAX_VALUE;
        random = new Random();
    }

    public void setLevel(Level level) {
        currentLevel = level;
    }

    public void update() {
        if(ticksSinceLastSpawn > currentLevel.enemySpawnDelay) {
            int x = random.nextInt(Display.WINDOW_WIDTH);
            int y = random.nextInt(Display.WINDOW_HEIGHT);
            Enemy enemy = new Enemy(x, y, host);
            listener.onEnemySpawned(enemy);
            ticksSinceLastSpawn = 0;
            return;
        }
        ticksSinceLastSpawn++;
    }

}
