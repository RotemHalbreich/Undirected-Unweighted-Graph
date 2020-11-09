package ex0;

import java.util.*;

/**
 * This class represents the "regular" Graph Theory algorithms including methods:
 * 0. clone(); - Deep copy of a graph
 * 1. init(graph); - Initializes the graph
 * 2. isConnected(); - Checks if the vertices are connected to each other by edges
 * 3. int shortestPathDist(int src, int dest); - Checks the shortest path distance
 * 4. List<Node> shortestPath(int src, int dest); - Returns the vertices of the shortest path as an ordered LinkedList
 *
 * @author Rotem Halbreich
 */

public class Graph_Algo implements graph_algorithms {

    private static final String UNVISITED = "white", VISITED = "gray", END_ROUND = "black";
    private graph g_algo = new Graph_DS();


    public Graph_Algo() {
        this.g_algo = new Graph_DS();
    }

    /**
     * Initializes the graph on which this set of algorithms operates on.
     *
     * @param g - the graph
     */
    @Override
    public void init(graph g) {
        if (g == null) {
            g_algo = new Graph_DS();
        } else g_algo = g;
    }

    /**
     * Computes a deep copy of this graph by key(ID), info & tag.
     *
     * @return a new similar graph (which isn't depended on the first graph)
     */
    @Override
    public graph copy() {
        graph ans = new Graph_DS();
        Iterator<node_data> point = g_algo.getV().iterator();
        node_data current;
        int key;
        String info;
        int tag;
        while (point.hasNext()) {
            current = point.next();
            key = current.getKey();
            info = current.getInfo();
            tag = current.getTag();
            node_data vertices = new NodeData(key, info, tag);
            ans.addNode(vertices);
        }
        for (node_data current_V : g_algo.getV()) {
            if (current_V.getNi() != null) {
                for (node_data current_Ni : current_V.getNi()) {
                    ans.connect(current_V.getKey(), current_Ni.getKey());
                }
            }
        }
        return ans;
    }

    /**
     * Checks if there's a valid path between all vertices (aka connected graph).
     * in this method we use the BFS algorithm in order to determine whether the
     * graph is connected by edges or not.
     * I've got the main idea about the BFS algorithm using this video:
     * https://www.youtube.com/watch?v=TIbUeeksXcI&feature=youtu.be&ab_channel=BackToBackSWE
     *
     * @return boolean (true/false)
     */
    @Override
    public boolean isConnected() {
        if (g_algo.nodeSize() <= 1) return true;
        node_data iter = this.g_algo.getV().iterator().next();
        BFSisConnected(iter);
        // For every node, if the info isn't "END_ROUND"
        // we can determine the graph isn't connected
        for (node_data node : this.g_algo.getV()) {
            if (!node.getInfo().equals(END_ROUND))
                return false;
        }
        return true;
    }

    // Help function for isConnected:
    private boolean BFSisConnected(node_data start) {
        Queue<node_data> queue = new LinkedList<>();
        queue.add(start);

        // Sets every node's info as "UNVISITED"
        for (node_data vertices : g_algo.getV()) {
            vertices.setInfo(UNVISITED);
        }
        // For every new vertex in the queue, we set it's info from "UNVISITED" to "VISITED"
        while (!queue.isEmpty()) {
            node_data current = queue.poll();
            if (current.getInfo().equals(UNVISITED)) {
                current.setInfo(VISITED);
            }
            Collection<node_data> current_Ni = current.getNi();
            // Sets this vertex's neighbors info to "VISITED"
            // and adds all the neighbors of this vertex to the Queue
            for (node_data neighbor : current_Ni) {
                if (neighbor.getInfo().equals(UNVISITED)) {
                    neighbor.setInfo(VISITED);
                    queue.add(neighbor);
                }
            }
            current.setInfo(END_ROUND);
        }
        return true;
    }

    /**
     * Returns the length of the shortest path between src to dest (vertices).
     * First we make sure that src & dest vertices really exist, secondly we make sure
     * that src & dest aren't the same vertex, then we use the BFS algorithm as
     * a helping method to calculate the shortest path distance using a Queue.
     * An explanation about BFS algorithm in the link below:
     * https://www.youtube.com/watch?v=oDqjPvD54Ss&ab_channel=WilliamFiset
     *
     * @param src  - start node
     * @param dest - end (target) node
     * @return length of shortest path || -1 (if there's no valid path)
     */
    @Override
    public int shortestPathDist(int src, int dest) {
        if (g_algo.getNode(src) == null || g_algo.getNode(dest) == null) return -1;
        if (src == dest) return 0;
        return BFS(src, dest);
    }

    // Help function for shortestPathDist: (based on BFS algorithm)
    private int BFS(int src, int dest) {
        Queue<node_data> path = new LinkedList<>();
        node_data start = this.g_algo.getNode(src);
        path.add(start); // Adds the first node to the queue

        // Initializes all the vertices with info: UNVISITED & tag: -1
        for (node_data vertices : g_algo.getV()) {
            vertices.setInfo(UNVISITED);
            vertices.setTag(-1);
        }
        start.setTag(0);
        // For every new vertex in the queue, we set it's info from UNVISITED to VISITED
        while (!path.isEmpty()) {
            node_data current = path.poll();
            if (current.getInfo().equals(UNVISITED)) {
                current.setInfo(VISITED);
            }
            if (current.getTag() == -1) return -1;
            if (current.getKey() == dest) return current.getTag();

            // Adds all the neighbors of this vertex to the Queue,
            // sets their info to VISITED and adds +1 to the distance (tag)
            Collection<node_data> current_Ni = current.getNi();
            for (node_data neighbor : current_Ni) {
                if (neighbor.getInfo().equals(UNVISITED)) {
                    neighbor.setInfo(VISITED);
                    path.add(neighbor);
                    neighbor.setTag(current.getTag() + 1);
                }
            }
            current.setInfo(END_ROUND);
        }
        return -1;
    }

    /**
     * Returns the the shortest path between two vertices as an ordered LinkedList of nodes:
     * (src)--> (n1)--> (n2)--> (n3)-->...--> (dest)
     * First we use my shortestPathDist method for getting the shortest path distance,
     * secondly we check if there's a valid path at all (if not return -1),
     * then we use the helping method which reverses the LinkedList from end to start
     * in order to get the shortest path.
     *
     * @param src  - start node
     * @param dest - end (target) node
     * @return LinkedList (the path of the vertices) || null (if no such path)
     */
    @Override
    public List<node_data> shortestPath(int src, int dest) {
        int path_dist = shortestPathDist(src, dest);
        if (path_dist == -1) return null;
        return shortestPathHelp(src, dest);
    }

    // Help function for shortestPath:
    private List<node_data> shortestPathHelp(int src, int dest) {

        LinkedList<node_data> path = new LinkedList<>();
        LinkedList<node_data> listToReturn = new LinkedList<>();

        // Sets the destination as the starting vertex in order to
        // reverse the LinkedList from end to start
        node_data start = this.g_algo.getNode(dest);
        path.add(start);
        listToReturn.add(start); // Adds the first node to the list
        int id = dest;
        int value = Integer.MAX_VALUE;

        // Adds the current vertex's neighbors to the LinkedList and get each
        // and one of them an ID & distance.
        while (!path.isEmpty()) {
            node_data current = path.remove();
            Collection<node_data> current_Ni = current.getNi();
            for (node_data neighbor : current_Ni) {
                if (value > neighbor.getTag()) {
                    id = neighbor.getKey();
                    value = neighbor.getTag();
                }
            }
            path.add(this.g_algo.getNode(id));
            listToReturn.add(this.g_algo.getNode(id));
            if (id == src) return listToReturn;
        }
        if (id == src) return listToReturn;
        return null;
    }
}

