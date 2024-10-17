package ca.mcmaster.se2aa4.mazerunner;

import ca.mcmaster.se2aa4.mazerunner.Graph.GraphMaze;
import ca.mcmaster.se2aa4.mazerunner.Maze.Maze;
import ca.mcmaster.se2aa4.mazerunner.Maze.Path;
import ca.mcmaster.se2aa4.mazerunner.Solvers.RightHandSolve;
import ca.mcmaster.se2aa4.mazerunner.Solvers.Solver;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RighthandTest {

    @Test
    void testRightHandSolve() throws Exception {
        Maze maze = new Maze("./examples/tiny.maz.txt");

        Solver solver = new RightHandSolve();
        Path result = solver.solve(maze);

        assertEquals("5F 2R 2F R 2F R 2F 2R 2F R 2F R 3F ", result.factorizePath());
    }

    @Test
    void testIllegalRightHandSolve() throws Exception {
        Maze maze = new Maze("./examples/tiny.maz.txt");
        GraphMaze graphMaze = new GraphMaze(maze);
        Solver solver = new RightHandSolve();

        assertThrows(IllegalArgumentException.class, () -> solver.solve(graphMaze));
    }

}
