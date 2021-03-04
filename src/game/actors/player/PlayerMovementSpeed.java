package game.actors.player;

public enum PlayerMovementSpeed {

    SLOW(1.0f),
    DEFAULT(2.0f),
    HIGH(3.0f);

    public final float speed;

    PlayerMovementSpeed(float speed) {
        this.speed = speed;
    }
}
