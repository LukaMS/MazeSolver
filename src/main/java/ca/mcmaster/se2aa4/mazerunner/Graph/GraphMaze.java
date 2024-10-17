package ca.mcmaster.se2aa4.mazerunner.Graph;

import ca.mcmaster.se2aa4.mazerunner.Maze.Direction;
import ca.mcmaster.se2aa4.mazerunner.Maze.Location;
import ca.mcmaster.se2aa4.mazerunner.Maze.Maze;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphMaze {

    private final Maze maze;
    private final Map<GraphNode, List<GraphNode>> graph;
    private final GraphNode endNode;
    private final GraphNode startNode;

    public GraphMaze(Maze maze){
        this.maze = maze;
        this.graph = mazeToGraph();

        Location startLoc = maze.getStartLoc();
        this.startNode = new GraphNode(startLoc.x(), startLoc.y());

        Location endLoc = maze.getEndLoc();
        this.endNode = new GraphNode(endLoc.x(), endLoc.y());
    }

    public Map<GraphNode, List<GraphNode>> mazeToGraph(){
        Map<GraphNode, List<GraphNode>> graph = new HashMap<>();

        for (int i = 0; i < maze.getSizeX(); i++){
            for(int j = 0; j < maze.getSizeY(); j++){
                Location loc = new Location(i,j);
                if(!maze.isWall(loc)){
                    GraphNode node = new GraphNode(loc.x(), loc.y());
                    graph.putIfAbsent(node,new ArrayList<>());

                    Direction[] directions = new Direction[] {Direction.UP, Direction.DOWN, Direction.RIGHT, Direction.LEFT};
                    for(Direction dir : directions){
                        Location tempLoc = loc.move(dir);
                        if(withinMaze(tempLoc)){
                            if(!maze.isWall(tempLoc)){
                                graph.get(node).add(new GraphNode(tempLoc.x(), tempLoc.y()));
                            }
                        }
                    }

                }
            }
        }

        return graph;
    }

    public boolean withinMaze(Location loc){
        return (loc.x() >= 0 && loc.x() < maze.getSizeX()) && (loc.y() >= 0 && loc.y() < maze.getSizeY());
    }

    public List<GraphNode> getNodes(GraphNode node){
        return this.graph.get(node);
    }

    public GraphNode getStartNode(){
        return this.startNode;
    }

    public GraphNode getEndNode(){
        return this.endNode;
    }

}
