package ca.mcmaster.se2aa4.mazerunner;

import ca.mcmaster.se2aa4.mazerunner.Graph.GraphMaze;
import ca.mcmaster.se2aa4.mazerunner.Graph.GraphNode;
import ca.mcmaster.se2aa4.mazerunner.Maze.Maze;
import ca.mcmaster.se2aa4.mazerunner.Maze.Path;
import ca.mcmaster.se2aa4.mazerunner.Solvers.BFSGraphSolve;
import ca.mcmaster.se2aa4.mazerunner.Solvers.RightHandSolve;
import ca.mcmaster.se2aa4.mazerunner.Solvers.Solver;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BFSTest {

    @Test
    void testBFSSolve() throws Exception {
        Maze maze = new Maze("./examples/tiny.maz.txt");
        GraphMaze graphMaze = new GraphMaze(maze);
        Solver solver = new BFSGraphSolve();
        Path result = solver.solve(graphMaze);

        assertEquals("3F L 4F R 3F ", result.factorizePath());
    }

    @Test
    void testIllegalBFSSolve() throws Exception {
        Maze maze = new Maze("./examples/tiny.maz.txt");
        Solver solver = new BFSGraphSolve() ;

        assertThrows(IllegalArgumentException.class, () -> solver.solve(maze));
    }

}
