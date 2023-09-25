import java.io.Serializable;
public class Graph implements Serializable {
    private int[][] distances;
    private String[] destinations;

    public Graph(int[][] distances, String[] destinations) {
        this.distances = distances;
        this.destinations = destinations;
    }

    public int getDistance(String from, String to) {
        return distances[indexOfDestination(from)][indexOfDestination(to)];
    }

    public int getNumDestinations() {
        return destinations.length;
    }

    public String[] getDestinations() {
        return destinations;
    }

    public int indexOfDestination(String destination) {
        for (int i = 0; i < destinations.length; i++) {
            if (destinations[i].equals(destination)) {
                return i;
            }
        }
        return -1;
    }
}
