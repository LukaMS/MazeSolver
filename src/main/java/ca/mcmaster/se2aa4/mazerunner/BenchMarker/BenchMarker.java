package ca.mcmaster.se2aa4.mazerunner.BenchMarker;

import ca.mcmaster.se2aa4.mazerunner.Graph.GraphMaze;
import ca.mcmaster.se2aa4.mazerunner.Maze.Maze;
import ca.mcmaster.se2aa4.mazerunner.Maze.Path;
import ca.mcmaster.se2aa4.mazerunner.Solvers.BFSGraphSolve;
import ca.mcmaster.se2aa4.mazerunner.Solvers.RightHandSolve;
import ca.mcmaster.se2aa4.mazerunner.Solvers.Solver;

public class BenchMarker {

    private final String method;
    private final String baseline;
    private BenchMarkResults methodBenchMark;
    private BenchMarkResults baselineBenchMark;
    private double loadingTime;
    private final Maze maze;

    public BenchMarker(String method, String baseline, String inputFile) throws Exception {
        this.method = method;
        this.baseline = baseline;

        double startTime = System.nanoTime();
        this.maze = new Maze(inputFile);
        double endTime = System.nanoTime();
        this.loadingTime = ((endTime-startTime)/1000000);

    }

    private BenchMarkResults benchMarkMaze(String method){
        Solver solver;
        GraphMaze graphMaze = null;
        boolean useGraphMaze = false;
        switch(method) {
            case "righthand" -> {
                solver = new RightHandSolve();
            }
            case "BFS" -> {
                graphMaze = new GraphMaze(maze);
                useGraphMaze = true;
                solver = new BFSGraphSolve();
            }
            default -> {
                throw new UnsupportedOperationException("Method Unsupported for Benchmarking");
            }
        }
        double startTime = System.nanoTime();
        Path path = (useGraphMaze) ? solver.solve(graphMaze) : solver.solve(maze);
        double endTime = System.nanoTime();
        double durationTime = ((endTime-startTime)/1000000);
        return new BenchMarkResults(durationTime, path.getLength());
    }


    public void benchMark(){
        methodBenchMark = benchMarkMaze(method);
        baselineBenchMark = benchMarkMaze(baseline);

        double methodTime = methodBenchMark.getTimeMillis();
        double baselineTime = baselineBenchMark.getTimeMillis();

        long methodPathLength = methodBenchMark.getPathLength();
        long baselinePathLength = baselineBenchMark.getPathLength();
        double speedUp = (double) baselinePathLength/methodPathLength;
        String speedUpMessage = (speedUp >= 1) ? "faster" : "slower";
        double speedUpAdjusted = (speedUp >= 1) ? speedUp : 1/speedUp;


        System.out.printf("Time to load maze: %.2f milliseconds%n", loadingTime);
        System.out.printf("Time to solve maze using %s: %.2f milliseconds", method, methodTime);
        System.out.println();
        System.out.printf("Time to solve maze using baseline %s: %.2f milliseconds", baseline, baselineTime);
        System.out.println();

        System.out.printf("Path length improvement: %s (%d steps) is %.2f times %s than %s (%d steps).", method, methodPathLength, speedUpAdjusted, speedUpMessage, baseline, baselinePathLength);
        System.out.println();
    }

}
