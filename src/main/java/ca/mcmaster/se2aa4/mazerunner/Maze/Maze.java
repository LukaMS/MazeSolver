package ca.mcmaster.se2aa4.mazerunner.Maze;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Maze {

    private static final Logger logger = LogManager.getLogger();

    private final List<List<Boolean>> maze = new ArrayList<>();

    private final Location startLoc;
    private final Location endLoc;


    public Maze(String inputFile) throws Exception{
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        String line;
        while ((line = reader.readLine()) != null){
            List<Boolean> newLine = new ArrayList<>();
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == '#') {
                    newLine.add(true);
                } else if (line.charAt(i) == ' ') {
                    newLine.add(false);
                }
            }
            maze.add(newLine);
        }

        startLoc = findStartLoc();
        endLoc = findEndLoc();
    }

    private Location findStartLoc() throws Exception{
        for(int i = 0; i < maze.size(); i++){
            if(!maze.get(i).get(0)){
                return new Location(0,i);
            }
        }
        throw new Exception("No start position");
    }

    private Location findEndLoc() throws Exception{
        int mazeWidth = maze.get(0).size() - 1;
        for(int i = 0; i < maze.size(); i++){
            if(!maze.get(i).get(mazeWidth)){
                return new Location(mazeWidth,i);
            }
        }
        throw new Exception("No End position");
    }

    public Location getStartLoc(){
        return startLoc;
    }

    public Location getEndLoc(){
        return endLoc;
    }

    public int getSizeX(){
        return maze.get(0).size();
    }

    public int getSizeY(){
        return maze.size();
    }

    public Boolean isWall(Location loc){
        return maze.get(loc.y()).get(loc.x());
    }

    public Boolean validatePath(Path path){
        return validatePathDirectional(path, startLoc, Direction.RIGHT, endLoc) || validatePathDirectional(path, endLoc, Direction.LEFT, startLoc);
    }

    public Boolean validatePathDirectional(Path path, Location start, Direction dir, Location end){
        Location loc = start;
        for (Character c : path.getPath()) {
            switch (c) {
                case 'F' -> {
                    loc = loc.move(dir);

                    if (loc.x() >= maze.get(0).size() || loc.y() >= maze.size() || loc.x() < 0 || loc.y() < 0) {
                        return false;
                    }
                    if (isWall(loc)) {
                        return false;
                    }
                }
                case 'R' -> dir = dir.turnRight();
                case 'L' -> dir = dir.turnLeft();
            }
            logger.debug("Current Position: " + loc);
        }

        return loc.equals(end);
    }
}
