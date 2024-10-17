package ca.mcmaster.se2aa4.mazerunner.BenchMarker;

public class BenchMarkResults {
    private final double timeMillis;
    private final long pathLength;

    public BenchMarkResults(double timeMillis, long pathLength) {
        this.timeMillis = timeMillis;
        this.pathLength = pathLength;
    }

    public double getTimeMillis() {
        return timeMillis;
    }

    public long getPathLength() {
        return pathLength;
    }
}
