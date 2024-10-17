package ca.mcmaster.se2aa4.mazerunner.Maze;

import java.util.ArrayList;
import java.util.List;

public class Path {

    private final List<Character> path = new ArrayList<>();

    public Path(){};

    public Path(String inputPath){
        String expandedPath = expandFactorizedPath(inputPath);
        for (Character c : expandedPath.toCharArray()) {
            if (c != ' ') {
                if (c != 'F' && c != 'L' && c != 'R') {
                    throw new IllegalArgumentException("Instruction '" + c + "' is invalid. Must be 'F', 'L', or 'R'.");
                }
                path.add(c);
            }
        }
    }

    public void add(Character move){
        path.add(move);
    }

    public List<Character> getPath(){
        return path;
    }

    public String factorizePath(){
        StringBuilder result = new StringBuilder();

        char currentChar = path.get(0);
        int count = 1;

        for (int i = 1; i < path.size(); i++) {
            if (path.get(i) == currentChar) {
                count++;
            } else {
                if(count > 1){
                    result.append(count);
                }
                result.append(currentChar).append(" ");
                currentChar = path.get(i);
                count = 1;
            }
        }
        if(count > 1){
            result.append(count);
        }
        result.append(currentChar).append(" ");

        return result.toString();
    }

    public String expandFactorizedPath(String path) {
        StringBuilder expanded = new StringBuilder();

        for (int i = 0; i < path.length(); i++) {
            if (!Character.isDigit(path.charAt(i))) {
                expanded.append(path.charAt(i));
            } else {
                int count = 0;
                int digit = 0;
                do {
                    count *= (int) Math.pow(10, digit++);
                    count += Character.getNumericValue(path.charAt(i++));
                } while (Character.isDigit(path.charAt(i)));

                String step = String.valueOf(path.charAt(i)).repeat(count);
                expanded.append(step);
            }
        }

        return expanded.toString();
    }

    public int getLength(){
        return path.size();
    }
}
