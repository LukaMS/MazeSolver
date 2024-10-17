package ca.mcmaster.se2aa4.mazerunner.Solvers;

import ca.mcmaster.se2aa4.mazerunner.Graph.GraphMaze;
import ca.mcmaster.se2aa4.mazerunner.Maze.Direction;
import ca.mcmaster.se2aa4.mazerunner.Maze.Location;
import ca.mcmaster.se2aa4.mazerunner.Maze.Maze;
import ca.mcmaster.se2aa4.mazerunner.Maze.Path;

public class RightHandSolve implements Solver {

    @Override
    public Path solve(Maze maze) {
        Path path = new Path();

        Location loc = maze.getStartLoc();
        Direction direction = Direction.RIGHT;
        while (!loc.equals(maze.getEndLoc())) {
            if (!maze.isWall(loc.move(direction.turnRight()))) {
                direction = direction.turnRight();
                path.add('R');
                loc = loc.move(direction);
                path.add('F');
            } else {
                if (!maze.isWall(loc.move(direction))) {
                    loc = loc.move(direction);
                    path.add('F');
                } else if (!maze.isWall(loc.move(direction.turnLeft()))) {
                    direction = direction.turnLeft();
                    path.add('L');
                    loc = loc.move(direction);
                    path.add('F');
                } else {
                    direction = direction.turnRight().turnRight();
                    path.add('R');
                    path.add('R');
                }
            }
        }
        return path;
    }

    @Override
    public Path solve(GraphMaze maze){
        throw new IllegalArgumentException("Unsupported Maze Type");
    }

}
