Object Oriented Programming (Ariel University - Computer science department):

Name: Rotem Halbreich
ID:   311549364

Assignment 0:

This project deals with the subject of "Graph Theory" focusing on undirectional and unweighted graph.

The project assembeled of 3 different classes:

1.	NodeData:
------------------------------------------------------------------------------------------
	Represents the graph's vertices:
	In this class I chose using the HashMap data structure for its fast running time O(1),
	by linking each neighbor's key to the vertex key.
	
	Including methods:
	A. Get a unique key ID for each vertex.
	B. Get a neighbor of a vertex.
	C. Add a neighbor to a vertex by linking the two keys (IDs) from the neighbor to the vertex.
	D. Remove a vertex diconnecting it by edges from its neighbors.
	E. Gets and sets the vertex info and tag.
	F. Remove an edge from the graph.


2.	Graph_DS:
------------------------------------------------------------------------------------------
	Represents a graph assembeled of vertices:
	In this class I chose using the HashMap data structure for its fast running time O(1),
	by linking each neighbor's key to the vertex key.
	
	Including methods:
	A. Get a vertex by a unique key.
	B. Check if there's an edge between two vertices.
	C. Add a vertex to the graph.
	D. Connect between two vertices.
	E. Get the value of the graph's vertices as a collection.
	F. Get the neighbors of the vertex as a collection.
	G. Remove a vertex from the graph.


3.  Graph_Algo: 
------------------------------------------------------------------------------------------
	Represents the algorithms we apply on the graph:
	Most of the graph's algorithms are based on BFS algorithm.
	
	The purpose of the project is to implement those graph's algorithms:
	A. Init() - Initializes the graph.
	B. Copy() - Computes a deep copy of the graph.
	C. isConnected() - Checks if all the vertices of the graph are connected by edges.
	D. shortestPathDist(src, dest) - Returns the shortest path distance between two vertices of the graph. 
	E. shortestPath(src, dest) - Returns the shortest path route between two vertices of the graph.
