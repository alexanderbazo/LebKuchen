package game.actors;

public enum PlayerMovementSpeed {

    SLOW(1.0f),
    DEFAULT(2.0f),
    HIGH(3.0f);

    public final float speed;

    private PlayerMovementSpeed(float speed) {
        this.speed = speed;
    }
}
