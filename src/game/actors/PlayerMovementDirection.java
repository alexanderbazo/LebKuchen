package game.actors;

public enum PlayerMovementDirection {
    NORTH(0, -1),
    EAST(1, 0),
    SOUTH(0, 1),
    WEST(-1, 0);

    public final float x;
    public final float y;

    PlayerMovementDirection(float x, float y) {
        this.x = x;
        this.y = y;
    }
}
