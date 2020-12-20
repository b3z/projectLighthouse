package app.Model;

/**
 * A Direction (either left or right).
 */
public enum Direction {
    RIGHT(1),
    LEFT(-1);

    private int dir;

    private Direction(int dir) {
        this.dir = dir;
    }

    public int getInt() {
        return dir;
    }

    
}