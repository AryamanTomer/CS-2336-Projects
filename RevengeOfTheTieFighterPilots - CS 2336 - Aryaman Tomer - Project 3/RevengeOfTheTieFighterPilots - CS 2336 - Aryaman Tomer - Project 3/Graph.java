//axt210052 Aryaman Tomer CS2336.006 Ranran Feng
import java.util.*;

class Graph {
    //This is a map to represent the list for the graph
    private Map<String, Map<String, Integer>> list;
    //Constructor for the graph
    public Graph() {
        this.list = new HashMap<>();
    }
    //Inserting an edge into the graph
    public void insertEdge(String source, String destination, int weight) {
        // Insert an edge into the graph and adding the destination vertex
        list.computeIfAbsent(source, k -> new HashMap<>());
        list.get(source).put(destination, weight);

        //Insert an reverse edge for an undirected graph
        list.computeIfAbsent(destination, k -> new HashMap<>());
        list.get(destination).put(source, weight);
    }
    //Getting the map to return the list
    public Map<String, Map<String, Integer>> getAdjacencyList() {
        return list;
    }
}
