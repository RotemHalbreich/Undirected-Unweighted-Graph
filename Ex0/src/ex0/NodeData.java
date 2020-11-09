package ex0;

import java.util.Collection;
import java.util.HashMap;

/**
 * This class represents the set of operations applicable on a
 * node (vertex) in an unidirectional unweighted graph.
 *
 * @author Rotem Halbreich
 */

public class NodeData implements node_data {

    private int key;
    private String info;
    private int tag;
    private HashMap<Integer, node_data> hash_neighbors;
    private static int count = 0;

    // Default constructor:
    public NodeData() {
        this.key = count++;
        this.info = "";
        this.tag = 0;
        this.hash_neighbors = new HashMap<Integer, node_data>();
    }

    // Constructor:
    public NodeData(int key, String info, int tag) {
        this.key = key;
        this.info = info;
        this.tag = tag;
        this.hash_neighbors = new HashMap<Integer, node_data>();
    }

    /**
     * @return the unique key (id) associated with each vertex.
     */
    @Override
    public int getKey() {
        return key;
    }

    /**
     * @return a collection including all the neighbors of the vertex
     */
    @Override
    public Collection<node_data> getNi() {
        return this.hash_neighbors.values();
    }

    /**
     * Checks if a vertex has a neighbor by containing a unique key
     * (aka connected by an edge to another vertex).
     *
     * @param key
     * @return boolean (true/false)
     */
    @Override
    public boolean hasNi(int key) {
        return this.hash_neighbors.containsKey(key);
    }

    /**
     * This method adds a new neighbor (t) to this vertex.
     *
     * @param t - the vertex we want to add as a neighbor
     */
    @Override
    public void addNi(node_data t) {
        if (t == null) return;
        this.hash_neighbors.put(t.getKey(), t);
    }

    /**
     * Removes a vertex and disconnects it by edges from its neighbors.
     *
     * @param node - the vertex we want to remove
     */
    @Override
    public void removeNode(node_data node) {
        this.hash_neighbors.remove(node.getKey());
    }

    /**
     * return the info associated with this vertex.
     *
     * @return info
     */
    @Override
    public String getInfo() {
        return this.info;
    }

    /**
     * Sets the info of this vertex.
     *
     * @param s - the new value of the info
     */
    @Override
    public void setInfo(String s) {
        this.info = s;
    }

    /**
     * return the tag associated with this vertex.
     *
     * @return tag
     */
    @Override
    public int getTag() {
        return this.tag;
    }

    /**
     * Allows setting the tag value for temporal marking a vertex.
     *
     * @param t - the new value of the tag
     */
    @Override
    public void setTag(int t) {
        this.tag = t;
    }

    /**
     * Represents the vertex as a string.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "NodeData{" +
                "key: " + this.getKey() +
                ", info: '" + this.getInfo() + '\'' +
                ", tag: " + this.getTag() +
                ", neighbors: " + this.hash_neighbors.keySet() +
                '}';
    }
}
