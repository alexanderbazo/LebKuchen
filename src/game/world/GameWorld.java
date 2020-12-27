package game.world;

import game.actors.enemy.Enemy;

import java.util.ArrayList;

public interface GameWorld {

    int getWidth();
    int getHeight();
    ArrayList<Enemy> getEnemiesAt(float x, float y, float radius);

}
