import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        int[][] distances = {
                {0, 100, 50, 20,30},
                {100, 0, 55, 75,90},
                {35, 15, 0, 10,75},
                {40, 25, 30, 0,50},
                {35, 15, 10, 10,0},
        };

        String[] destinations = {"APIIT", "FORT", "DEHIWALA", "PANADURA","NUGEGODA"};
        Graph graph = new Graph(distances, destinations);

        //Serialize the graph
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("graph.ser"))) {
            out.writeObject(graph);
            System.out.println("Graph object serialized and saved.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Deserialize the graph
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("graph.ser"))) {
            Graph deserializedGraph = (Graph) in.readObject();
            System.out.println("Graph object deserialized.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        Tour tour = findOptimalTour(graph, "APIIT");

        //Serialize the tour
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("tour.ser"))) {
            out.writeObject(tour);
            System.out.println("Tour object serialized and saved.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Deserialize the tour
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("tour.ser"))) {
            Tour deserializedTour = (Tour) in.readObject();
            System.out.println("Tour object deserialized.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        printTour(tour);

    }

    public static Tour findOptimalTour(Graph graph, String startDestination) {
        Tour tour = new Tour();
        tour.visit(startDestination);

        while (tour.getPath().size() < graph.getNumDestinations()) {
            String currentDestination = tour.getCurrentDestination();
            String nextDestination = findNearestUnvisitedDestination(graph, tour, currentDestination);
            tour.visit(nextDestination);
        }

        int distanceToStart = graph.getDistance(tour.getCurrentDestination(), startDestination);
        tour.completeTour(distanceToStart);

        return tour;
    }

    public static String findNearestUnvisitedDestination(Graph graph, Tour tour, String currentDestination) {
        int minDistance = Integer.MAX_VALUE;
        String nearestDestination = null;

        for (String destination : graph.getDestinations()) {
            if (!tour.isVisited(destination)) {
                int distance = graph.getDistance(currentDestination, destination);
                if (distance < minDistance) {
                    minDistance = distance;
                    nearestDestination = destination;
                }
            }
        }

        return nearestDestination;
    }

    public static void printTour(Tour tour) {
        List<String> path = tour.getPath();
        System.out.println("Tour:");
        for (int i = 0; i < path.size() - 1; i++) {
            System.out.print(path.get(i) + " -> ");
        }
        System.out.println(path.get(path.size() - 1)); // Print the last destination
        System.out.println("Tour Length: " + tour.getTourLength());
    }
}