package ca.mcmaster.se2aa4.mazerunner.Solvers;

import ca.mcmaster.se2aa4.mazerunner.Graph.GraphMaze;
import ca.mcmaster.se2aa4.mazerunner.Maze.Maze;
import ca.mcmaster.se2aa4.mazerunner.Maze.Path;

public interface Solver {
    Path solve(Maze maze);
    Path solve(GraphMaze maze);
}
