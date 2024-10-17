package ca.mcmaster.se2aa4.mazerunner.Maze;

public enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT;

    public Direction turnRight() {
        switch (this) {
            case UP -> {
                return RIGHT;
            }
            case DOWN -> {
                return LEFT;
            }
            case LEFT -> {
                return UP;
            }
            case RIGHT -> {
                return DOWN;
            }
        }
        throw new IllegalStateException("Unexpected value");
    }

    public Direction turnLeft() {
        switch (this) {
            case UP -> {
                return LEFT;
            }
            case DOWN -> {
                return RIGHT;
            }
            case LEFT -> {
                return DOWN;
            }
            case RIGHT -> {
                return UP;
            }
        }
        throw new IllegalStateException("Unexpected value");
    }
}
