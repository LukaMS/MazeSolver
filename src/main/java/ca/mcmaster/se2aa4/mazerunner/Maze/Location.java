package ca.mcmaster.se2aa4.mazerunner.Maze;

public record Location (int x, int y){


    public Location move(Direction direction) {
        switch (direction) {
            case UP -> {
                return new Location(this.x, this.y - 1);
            }
            case DOWN -> {
                return new Location(this.x, this.y + 1);
            }
            case LEFT -> {
                return new Location(this.x - 1, this.y);
            }
            case RIGHT -> {
                return new Location(this.x + 1, this.y);
            }
        }
        throw new IllegalStateException("Unexpected value");
    }
}
