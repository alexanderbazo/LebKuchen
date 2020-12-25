package game.levels;

import game.actors.EnemyMovementSpeed;
import game.actors.PlayerMovementSpeed;

public class Level {

    public final int id;
    public final PlayerMovementSpeed playerSpeed;
    public final EnemyMovementSpeed enemySpeed;
    public final int totalNumberOfEnemies;
    public final int enemySpawnDelay;

    public Level(int id, PlayerMovementSpeed playerSpeed, EnemyMovementSpeed enemySpeed, int totalNumberOfEnemies, int enemySpawnDelay) {
        this.id = id;
        this.playerSpeed = playerSpeed;
        this.enemySpeed = enemySpeed;
        this.totalNumberOfEnemies = totalNumberOfEnemies;
        this.enemySpawnDelay = enemySpawnDelay;
    }

    public static Level next() {
        return new Level(1, PlayerMovementSpeed.DEFAULT, EnemyMovementSpeed.DEFAULT, 10, 60);
    }

}
