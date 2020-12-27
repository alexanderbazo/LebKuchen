package game.actors.weapons;

import game.actors.enemy.Enemy;

import java.util.ArrayList;

public interface ProjectileListener {

    void onProjectileHitEnemies(Projectile projectile, ArrayList<Enemy> enemies);
    void onProjectileLeftCanvas(Projectile projectile);

}
