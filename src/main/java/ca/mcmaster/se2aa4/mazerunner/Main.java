package ca.mcmaster.se2aa4.mazerunner;

import ca.mcmaster.se2aa4.mazerunner.BenchMarker.BenchMarker;
import ca.mcmaster.se2aa4.mazerunner.Graph.GraphMaze;
import ca.mcmaster.se2aa4.mazerunner.Maze.Maze;
import ca.mcmaster.se2aa4.mazerunner.Maze.Path;
import ca.mcmaster.se2aa4.mazerunner.Solvers.BFSGraphSolve;
import ca.mcmaster.se2aa4.mazerunner.Solvers.RightHandSolve;
import ca.mcmaster.se2aa4.mazerunner.Solvers.Solver;
import org.apache.commons.cli.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    protected static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        try {
            logger.info("** Starting Maze Runner");

            Configuration config = configure(args);

            logger.info("**** Reading the maze from file " + config.inputFile());

            Maze maze = new Maze(config.inputFile());

            //Validate Path
            if(config.inputPath() != null){
                logger.info("**** Checking path");
                Path path = new Path(config.inputPath());
                if(maze.validatePath(path)){
                    System.out.println("*** Correct Path");
                } else {
                    System.out.println("*** Incorrect Path");
                }
            }
            else if(config.baseline() != null){
                BenchMarker benchmarker = new BenchMarker(config.inputMethod(), config.baseline(), config.inputFile());
                logger.info("*** Bench Marking");
                benchmarker.benchMark();
            }
            //Compute Path based on Method
            else {
                String method = config.inputMethod();
                Path path = solveMaze(method, maze);
                System.out.println("*** Computed Path " + path.factorizePath());
            }
            logger.info("** End of MazeRunner");
        } catch (Exception e) {
            logger.error("/!\\ An error has occured /!\\" + e.getMessage());
            System.exit(1);
        }
    }

    private static Path solveMaze(String method, Maze maze) throws Exception{
        Solver solver;
        switch (method){
            case "rightHand" -> {
                logger.info("*** Right Hand Method Selected");
                logger.info("*** Computing Path");
                solver = new RightHandSolve();
                return solver.solve(maze);
            }
            case "BFS" -> {
                logger.info("*** BFS Method Selected");
                logger.info("*** Computing Path");
                GraphMaze graphMaze = new GraphMaze(maze);
                solver = new BFSGraphSolve();
                return solver.solve(graphMaze);
            }
            default -> {
                throw new Exception("Unsupported Maze Method: " + method);
            }
        }

    }


    private static Configuration configure(String[] args) throws ParseException {
        Options options = new Options();

        Option inputOption = new Option("i", "input", true, "Input File Path");
        Option pathInputOption = new Option("p", "path", true, "Maze Path");
        Option methodInputOption = new Option("m", "method", true, "Method");
        Option baselineInputOption = new Option("baseline", "baseline", true, "baseline");
        inputOption.setRequired(true);

        options.addOption(inputOption);
        options.addOption(pathInputOption);
        options.addOption(methodInputOption);
        options.addOption(baselineInputOption);

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        String inputFilePath = cmd.getOptionValue("i");
        String inputPath = cmd.getOptionValue("p");
        String inputMethod = cmd.getOptionValue("m", "righthand");
        String baseline = cmd.getOptionValue("baseline" );

        return new Configuration(inputFilePath, inputPath, inputMethod, baseline);
    }

    private record Configuration(String inputFile, String inputPath, String inputMethod, String baseline){ }

}
