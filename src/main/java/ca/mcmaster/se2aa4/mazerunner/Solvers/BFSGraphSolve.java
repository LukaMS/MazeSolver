package ca.mcmaster.se2aa4.mazerunner.Solvers;

import ca.mcmaster.se2aa4.mazerunner.Graph.GraphMaze;
import ca.mcmaster.se2aa4.mazerunner.Graph.GraphNode;
import ca.mcmaster.se2aa4.mazerunner.Graph.GraphPath;
import ca.mcmaster.se2aa4.mazerunner.Maze.Maze;
import ca.mcmaster.se2aa4.mazerunner.Maze.Path;

import java.util.*;

public class BFSGraphSolve implements Solver{

    @Override
    public Path solve(GraphMaze maze){

        PriorityQueue<GraphNode> queue = new PriorityQueue<>(
                Comparator.comparingInt(GraphNode::x).thenComparingInt(GraphNode::y));
        Set<GraphNode> visited = new HashSet<>();
        Map<GraphNode, GraphNode> nodePaths = new HashMap<>();

        GraphNode startNode = maze.getStartNode();
        GraphNode endNode = maze.getEndNode();

        queue.add(startNode);
        visited.add(startNode);

        GraphPath path = new GraphPath();

        while(!queue.isEmpty()){
            GraphNode current = queue.remove();

            if (current.equals(endNode)) {
                path.convertListToPath(nodePaths, startNode, endNode);
                break;
            }
            for(GraphNode neighbour : maze.getNodes(current)) {
                if (!visited.contains(neighbour)) {
                    queue.add(neighbour);
                    visited.add(neighbour);
                    nodePaths.put(neighbour, current);
                }
            }

        }

        return path.getGraphPath();
    }

    @Override
    public Path solve(Maze maze){
        throw new IllegalArgumentException("Unsupported Maze type");
    }

}
