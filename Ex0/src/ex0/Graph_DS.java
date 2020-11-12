package ex0;

import java.util.Collection;
import java.util.HashMap;

/**
 * This class represents an undirectional unweighted graph.
 *
 * @author Rotem Halbreich
 */

public class Graph_DS implements graph {

    private int edges = 0;
    private int vertices = 0;
    private int mc = 0;
    private HashMap<Integer, node_data> my_graph;

    // Default constructor:
    public Graph_DS() {
        this.my_graph = new HashMap<Integer, node_data>();
    }

    /**
     * Returns the vertex by its unique key (ID).
     *
     * @param key - vertex's ID
     * @return Vertex's ID || null (if none)
     */
    @Override
    public node_data getNode(int key) {
        if(my_graph.get(key) == null) return null;
        return this.my_graph.get(key);
    }

    /**
     * Checks if there's an edge connecting two vertices.
     *
     * @param node1 - first vertex
     * @param node2 - second vertex
     * @return boolean (true/false)
     */
    @Override
    public boolean hasEdge(int node1, int node2) {
        if (node1 == node2) return false;
        if (getNode(node1) == null || getNode(node2) == null) return false;

        return getNode(node1).hasNi(node2);
    }

    /**
     * Adds a new vertex to the graph.
     *
     * @param n - the new graph's vertex
     */
    @Override
    public void addNode(node_data n) {
        if (n == null) return;
        this.my_graph.put(n.getKey(), n);
        vertices++;
        mc++;
    }

    /**
     * Connects between two vertices (with an edge).
     * First we check if both vertex are exist,
     * if the first vertex has any neighbor at all,
     * if there's only one vertex in the graph.
     *
     * @param node1 - first vertex
     * @param node2 - second vertex
     */
    @Override
    public void connect(int node1, int node2) {
        if (getNode(node1) == null || getNode(node2) == null) return;
        if (getNode(node1).hasNi(node2)) return;
        if (node1 == node2) return;
        this.getNode(node1).addNi(this.getNode(node2));
        this.getNode(node2).addNi(this.getNode(node1));
        edges++;
        mc++;
    }

    /**
     * Returns a pointer for the collection
     * representing all the vertices of the graph.
     *
     * @return Collection<node_data> - All the graph's vertices
     */
    @Override
    public Collection<node_data> getV() {
        return this.my_graph.values();
    }

    /**
     * Returns a collection containing all the neighbors of the vertex.
     *
     * @return Collection<node_data> - All the vertex's neighbors
     */
    @Override
    public Collection<node_data> getV(int node_id) {
        if (getNode(node_id) == null) return null;
        return this.getNode(node_id).getNi();
    }

    /**
     * Deletes the graph's vertex and removes all its neighbors.
     *
     * @param key
     * @return the ID of the removed vertex || null (if none)
     */
    @Override
    public node_data removeNode(int key) {
        if (getNode(key) == null) return null;
        for (node_data vertex : this.getNode(key).getNi()) {
            vertex.removeNode(my_graph.get(key));
            edges--;
        }
        vertices--;
        mc++;
        return this.my_graph.remove(key);
    }

    /**
     * Deletes the edge between two vertices.
     *
     * @param node1 - first vertex
     * @param node2 - second vertex
     */
    @Override
    public void removeEdge(int node1, int node2) {
        if (!getNode(node1).hasNi(node2)) return;
        if (getNode(node1) == null || getNode(node2) == null) return;
        getNode(node1).removeNode(getNode(node2));
        getNode(node2).removeNode(getNode(node1));
        edges--;
        mc++;
    }

    /**
     * @return vertices - the number of vertices in the graph
     */
    @Override
    public int nodeSize() {
        return vertices;
    }

    /**
     * @return edges - the number of edges in the graph
     */
    @Override
    public int edgeSize() {
        return edges;
    }

    /**
     * @return mc - the number of changes made to the graph
     */
    @Override
    public int getMC() {
        return mc;
    }

    /**
     * Represents the graph as a string.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "my_graph:{" + "Vertices: " + vertices + " ,Edges: " + edges + ", MC: " + mc + "}";
    }
}
