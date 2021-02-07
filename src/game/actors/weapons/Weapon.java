package game.actors.weapons;

import de.ur.mi.oop.graphics.Point;
import game.scenes.BaseScene;

public class Weapon {

    private final String name;
    private final float damagePerProjectile;
    private final float cooldownFrames;
    private final float splashRadius;
    private final float projectileSpeed;
    private int remainingAmmunition;
    private int framesSinceLastShoot;
    private WeaponListener listener;

    public Weapon(String name, float damagePerProjectile, float cooldownFrames, float splashRadius, float projectileSpeed) {
        this.name = name;
        this.damagePerProjectile = damagePerProjectile;
        this.cooldownFrames = cooldownFrames;
        this.splashRadius = splashRadius;
        this.projectileSpeed = projectileSpeed;
        remainingAmmunition = 0;
        framesSinceLastShoot = 0;
    }

    public void tick() {
        framesSinceLastShoot++;
    }

    public void setListener(WeaponListener listener) {
        this.listener = listener;
    }

    public void addAmmunition(int amount) {
        remainingAmmunition += amount;
    }

    public int getRemainingAmmunition() {
        return remainingAmmunition;
    }

    public void fire(BaseScene host, Point origin, Point target) {
        if (remainingAmmunition == 0) {
            return;
        }
        if (framesSinceLastShoot < cooldownFrames) {
            return;
        }
        Projectile projectile = new Projectile((int) origin.getXPos(), (int) origin.getYPos(), host, target, damagePerProjectile, projectileSpeed, splashRadius);
        remainingAmmunition--;
        framesSinceLastShoot = 0;
        if (listener != null) {
            listener.onProjectileFired(projectile);
        }
    }

}
