package ca.mcmaster.se2aa4.mazerunner.Graph;

import ca.mcmaster.se2aa4.mazerunner.Maze.Direction;
import ca.mcmaster.se2aa4.mazerunner.Maze.Path;

import java.util.LinkedList;
import java.util.Map;

public class GraphPath {

    private final Path graphPath = new Path();

    public GraphPath(){}

    public Direction getDirectionChange(Direction currentDir, int dx, int dy){
        switch (currentDir){
            case UP -> {
                if(dx == 1){
                    graphPath.add('R');
                    graphPath.add('F');
                    return currentDir.turnRight();
                } else if (dx == -1){
                    graphPath.add('L');
                    graphPath.add('F');
                    return currentDir.turnLeft();
                } else {
                    graphPath.add('F');
                    return currentDir;
                }
            }
            case DOWN -> {
                if(dx == 1){
                    graphPath.add('L');
                    graphPath.add('F');
                    return currentDir.turnLeft();
                } else if (dy == -1){
                    graphPath.add('R');
                    graphPath.add('F');
                    return currentDir.turnRight();
                } else {
                    graphPath.add('F');
                    return currentDir;
                }
            }
            case RIGHT -> {
                if(dy == 1){
                    graphPath.add('R');
                    graphPath.add('F');
                    return currentDir.turnRight();
                } else if (dy == -1){
                    graphPath.add('L');
                    graphPath.add('F');
                    return currentDir.turnLeft();
                } else {
                    graphPath.add('F');
                    return currentDir;
                }
            }
            case LEFT -> {
                if(dy == 1){
                    graphPath.add('L');
                    graphPath.add('F');
                    return currentDir.turnLeft();
                } else if (dy == -1){
                    graphPath.add('R');
                    graphPath.add('F');
                    return currentDir.turnRight();
                } else {
                    graphPath.add('F');
                    return currentDir;
                }
            }
        }
        return null;
    }

    public void convertListToPath(Map<GraphNode, GraphNode> nodePaths, GraphNode startNode, GraphNode endNode){
        LinkedList<GraphNode> listPath = new LinkedList<>();
        GraphNode current = endNode;
        Direction direction = Direction.RIGHT;

        while (current != null) {
            listPath.addFirst(current); // Add to the beginning of the list to reverse the path
            current = nodePaths.get(current);
        }

        for(int i = 0; i < listPath.size() - 1; i++){
            GraphNode currentNode = listPath.get(i);
            GraphNode nextNode = listPath.get(i + 1);

            int dx = nextNode.x() - currentNode.x();
            int dy = nextNode.y() - currentNode.y();

            direction = getDirectionChange(direction, dx, dy);

        }
    }

    public Path getGraphPath(){
        return graphPath;
    }

}
