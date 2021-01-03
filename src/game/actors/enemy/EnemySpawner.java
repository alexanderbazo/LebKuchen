package game.actors.enemy;

import de.ur.mi.oop.graphics.Point;
import game.levels.Level;
import game.scenes.BaseScene;
import game.world.GameWorld;

import java.util.Random;

public class EnemySpawner {

    private BaseScene host;
    private EnemySpawnListener listener;
    private GameWorld world;
    private Point[] spawnPoints;
    private Random random;
    private int ticksSinceLastSpawn;
    private Level currentLevel;
    private int spawnedEnemies;

    public EnemySpawner(EnemySpawnListener listener, BaseScene host, GameWorld world) {
        this.listener = listener;
        this.host = host;
        this.world = world;
        this.spawnPoints = initSpawnPoints();
    }

    private Point[] initSpawnPoints() {
        int spawnPointsPerRow = world.getWidth() / Enemy.WIDTH;
        int spawnPointsPerColumn = world.getHeight() / Enemy.HEIGHT;
        Point[] spawnPoints = new Point[2 * spawnPointsPerRow + 2 * spawnPointsPerColumn];
        int spawnX = 0;
        int spawnY = -Enemy.HEIGHT;
        int spawnPointIndex = 0;
        for (int j = 0; j < 2 * spawnPointsPerRow; j++) {
            spawnPoints[spawnPointIndex] = new Point(spawnX, spawnY);
            spawnX += Enemy.WIDTH;
            if (j == spawnPointsPerColumn) {
                spawnX = 0;
                spawnY = world.getHeight() + Enemy.HEIGHT;
            }
            spawnPointIndex++;
        }
        spawnX = -Enemy.WIDTH;
        spawnY = 0;
        for (int j = 0; j < 2 * spawnPointsPerColumn; j++) {
            spawnPoints[spawnPointIndex] = new Point(spawnX, spawnY);
            spawnY += Enemy.HEIGHT;
            if (j == spawnPointsPerColumn) {
                spawnX = world.getWidth() + Enemy.WIDTH;
                spawnY = 0;
            }
            spawnPointIndex++;
        }
        return spawnPoints;
    }

    public void reset(Level level) {
        this.currentLevel = level;
        this.ticksSinceLastSpawn = Integer.MAX_VALUE;
        this.spawnedEnemies = 0;
        random = new Random();
    }

    public void update() {
        if (ticksSinceLastSpawn > currentLevel.enemySpawnDelay && spawnedEnemies < currentLevel.totalNumberOfEnemies) {
            int index = random.nextInt(spawnPoints.length);
            Point spawnPoint = spawnPoints[index];
            Enemy enemy = new Enemy((int) spawnPoint.getXPos(), (int) spawnPoint.getYPos(), host);
            spawnedEnemies++;
            ticksSinceLastSpawn = 0;
            listener.onEnemySpawned(enemy);
            return;
        }
        ticksSinceLastSpawn++;
    }

}
