package game.actors;

public enum EnemyMovementSpeed {

    SLOW(0.5f),
    DEFAULT(1.0f),
    HIGH(2.0f);

    public final float speed;

    EnemyMovementSpeed(float speed) {
        this.speed = speed;
    }
}
