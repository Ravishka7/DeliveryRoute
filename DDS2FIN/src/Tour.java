import java.io.Serializable;
import java.util.*;

public class Tour implements Serializable {
    private Set<String> visited;
    private Stack<String> path;
    private int tourLength;

    public Tour() {
        visited = new HashSet<>();
        path = new Stack<>();
        tourLength = 0;
    }

    public boolean isVisited(String destination) {
        return visited.contains(destination);
    }

    public void visit(String destination) {
        visited.add(destination);
        path.push(destination);
    }

    public String getCurrentDestination() {
        return path.peek();
    }

    public void completeTour(int distanceToStart) {
        path.push(path.firstElement());
        tourLength += distanceToStart;
    }

    public int getTourLength() {
        return tourLength;
    }

    public List<String> getPath() {
        return new ArrayList<>(path);
    }
}
