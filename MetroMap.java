// --== CS400 File Header Information ==--
// Name: Jake Schroeder
// Email: schroeder22@wisc.edu
// Team: KG Blue
// Role: Data Wrangler // Backend Developer
// TA: Keren Chen
// Lecturer: Florian Heimerl
// Notes to Grader:

import java.util.Hashtable;
import java.util.List;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.NoSuchElementException;

public class MetroMap<T> implements GraphADT<T> {

    /**
     * Station objects group a data field with an adjacency list of weighted
     * directed edges that lead away from them.
     */
    protected class Vertex {
        public String name; // name of station
        public boolean red; // true if red line runs through station
        public boolean blue; // true if blue line runs through station
        public boolean green; // true if green line runs through station
        public LinkedList<Edge> edgesLeaving;

        public Vertex(String name, boolean r, boolean b, boolean g) {
            this.name = name;
            this.red = r;
            this.blue = b;
            this.green = g;
            this.edgesLeaving = new LinkedList<>();
        }
    }

    /**
     * Edge objects are stored within their source station, and group together
     * their target destination vertex, along with an integer weight, denoting the 
     * distance between the two stations.
     */
    protected class Edge {
        public Vertex target;
        public int weight;

        public Edge(Vertex target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    protected Hashtable<String, Vertex> vertices; // holds graph verticies, key=data
    public MetroMap() { vertices = new Hashtable<>(); }

    /**
     * Insert a new vertex into the graph.
     *
     * @param data the data item stored in the new vertex
     * @return true if the data can be inserted as a new vertex, false if it is
     *     already in the graph
     * @throws NullPointerException if data is null
     */
    public boolean insertVertex(String name, boolean r, boolean b, boolean g) {
        if(name == null)
            throw new NullPointerException("Cannot add null station");
        if(vertices.containsKey(name)) return false; // duplicate values are not allowed
        vertices.put(name, new Vertex(name, r, b, g));
        return true;
    }

    /**
     * Remove a vertex from the graph.
     * Also removes all edges adjacent to the vertex from the graph (all edges
     * that have the vertex as a source or a destination vertex).
     *
     * @param name the data item stored in the vertex to remove
     * @return true if a vertex with *name* has been removed, false if it was not in the graph
     * @throws NullPointerException if data is null
     */
    public boolean removeVertex(String name) {
        if(name == null) throw new NullPointerException("Cannot remove null vertex");
        Vertex removeVertex = vertices.get(name);
        if(removeVertex == null) return false; // vertex not found within graph
        // search all vertices for edges targeting removeVertex
        for(Vertex v : vertices.values()) {
            Edge removeEdge = null;
            for(Edge e : v.edgesLeaving)
                if(e.target == removeVertex)
                    removeEdge = e;
            // and remove any such edges that are found
            if(removeEdge != null) v.edgesLeaving.remove(removeEdge);
        }
        // finally remove the vertex and all edges contained within it
        return vertices.remove(name) != null;
    }

    /**
     * Insert a new directed edge with a positive edge weight into the graph.
     *
     * @param vertex the data item contained in the source vertex for the edge
     * @param vertex2 the data item contained in the target vertex for the edge
     * @param weight the weight for the edge (has to be a positive integer)
     * @return true if the edge could be inserted or its weight updated, false
     *     if the edge with the same weight was already in the graph
     * @throws IllegalArgumentException if either source or target or both are not in the graph,
     *     or if its weight is < 0
     * @throws NullPointerException if either source or target or both are null
     */
    public boolean insertEdge(String vertex, String vertex2, int weight) {
        if(vertex == null || vertex2 == null)
            throw new NullPointerException("Cannot add edge with null source or target");
        Vertex sourceVertex = this.vertices.get(vertex);
        Vertex targetVertex = this.vertices.get(vertex2);
        if(sourceVertex == null || targetVertex == null)
            throw new IllegalArgumentException("Cannot add edge with vertices that do not exist");
        if(weight < 0)
            throw new IllegalArgumentException("Cannot add edge with negative weight");
        // handle cases where edge already exists between these verticies
        for(Edge e : sourceVertex.edgesLeaving)
            if(e.target == targetVertex) {
                if(e.weight == weight) return false; // edge already exists
                else e.weight = weight; // otherwise update weight of existing edge
                return true;
            }
        // otherwise add new edge to sourceVertex
        sourceVertex.edgesLeaving.add(new Edge(targetVertex,weight));
        return true;
    }

    /**
     * Remove an edge from the graph.
     *
     * @param source the data item contained in the source vertex for the edge
     * @param target the data item contained in the target vertex for the edge
     * @return true if the edge could be removed, false if it was not in the graph
     * @throws IllegalArgumentException if either source or target or both are not in the graph
     * @throws NullPointerException if either source or target or both are null
     */
    public boolean removeEdge(String source, String target) {
        if(source == null || target == null) throw new NullPointerException("Cannot remove edge with null source or target");
        Vertex sourceVertex = this.vertices.get(source);
        Vertex targetVertex = this.vertices.get(target);
        if(sourceVertex == null || targetVertex == null) throw new IllegalArgumentException("Cannot remove edge with vertices that do not exist");
        // find edge to remove
        Edge removeEdge = null;
        for(Edge e : sourceVertex.edgesLeaving)
            if(e.target == targetVertex)
                removeEdge = e;
        if(removeEdge != null) { // remove edge that is successfully found
            sourceVertex.edgesLeaving.remove(removeEdge);
            return true;
        }
        return false; // otherwise return false to indicate failure to find
    }

    /**
     * Check if the graph contains a vertex with data item *name*.
     *
     * @param name the data item to check for
     * @return true if data item is stored in a vertex of the graph, false otherwise
     * @throws NullPointerException if *name* is null
     */
    public boolean containsVertex(String name) {
        if(name == null) throw new NullPointerException("Cannot contain null data vertex");
        return vertices.containsKey(name);
    }

    /**
     * Check if edge is in the graph.
     *
     * @param source the data item contained in the source vertex for the edge
     * @param target the data item contained in the target vertex for the edge
     * @return true if the edge is in the graph, false if it is not in the graph
     * @throws NullPointerException if either source or target or both are null
     */
    public boolean containsEdge(String source, String target) {
        if(source == null || target == null) throw new NullPointerException("Cannot contain edge adjacent to null data");
        Vertex sourceVertex = vertices.get(source);
        Vertex targetVertex = vertices.get(target);
        if(sourceVertex == null) return false;
        for(Edge e : sourceVertex.edgesLeaving)
            if(e.target == targetVertex)
                return true;
        return false;
    }

    /**
     * Return the weight of an edge.
     *
     * @param source the data item contained in the source vertex for the edge
     * @param target the data item contained in the target vertex for the edge
     * @return the weight of the edge (0 or positive integer)
     * @throws IllegalArgumentException if either sourceVertex or targetVertex or both are not in the graph
     * @throws NullPointerException if either sourceVertex or targetVertex or both are null
     * @throws NoSuchElementException if edge is not in the graph
     */
    public int getWeight(String source, String target) {
        if(source == null || target == null) throw new NullPointerException("Cannot contain weighted edge adjacent to null data");
        Vertex sourceVertex = vertices.get(source);
        Vertex targetVertex = vertices.get(target);
        if(sourceVertex == null || targetVertex == null) throw new IllegalArgumentException("Cannot retrieve weight of edge between vertices that do not exist");
        for(Edge e : sourceVertex.edgesLeaving)
            if(e.target == targetVertex)
                return e.weight;
        throw new NoSuchElementException("No directed edge found between these vertices");
    }

    /**
     * Return the number of edges in the graph.
     *
     * @return the number of edges in the graph
     */
    public int getEdgeCount() {
        int edgeCount = 0;
        for(Vertex v : vertices.values())
            edgeCount += v.edgesLeaving.size();
        return edgeCount;
    }

    /**
     * Return the number of vertices in the graph
     *
     * @return the number of vertices in the graph
     */
    public int getVertexCount() {
        return vertices.size();
    }

    /**
     * Return the vertex with the given key.
     *
     * @return the vertex with the given key.
     */
    public Vertex getVertex(String name) {
        return vertices.get(name);
    }


    /**
     * Check if the graph is empty (does not contain any vertices or edges).
     *
     * @return true if the graph does not contain any vertices or edges, false otherwise
     */
    public boolean isEmpty() {
        return vertices.size() == 0;
    }

    /**
     * Path objects store a discovered path of vertices and the overal distance of cost
     * of the weighted directed edges along this path. Path objects can be copied and extended
     * to include new edges and verticies using the extend constructor. In comparison to a
     * predecessor table which is sometimes used to implement Dijkstra's algorithm, this
     * eliminates the need for tracing paths backwards from the destination vertex to the
     * starting vertex at the end of the algorithm.
     */
    protected class Path implements Comparable<Path>{
        public Vertex start; // first vertex within path
        public int distance; // sumed weight of all edges in path
        public List<String> dataSequence; // ordered sequence of data from vertices in path
        public Vertex end; // last vertex within path

        /**
         * Creates a new path containing a single vertex.  Since this vertex is both
         * the start and end of the path, it's initial distance is zero.
         * @param start is the first vertex on this path
         */
        public Path(Vertex start) {
            this.start = start;
            this.distance = 0;
            this.dataSequence = new LinkedList<>();
            this.dataSequence.add(start.name);
            this.end = start;
        }

        /**
         * This extension constructor makes a copy of the path passed into it as an argument
         * without affecting the original path object (copyPath). The path is then extended
         * by the Edge object extendBy.
         * @param copyPath is the path that is being copied
         * @param extendBy is the edge the copied path is extended by
         */
        public Path(Path copyPath, Edge extendBy) {
          Path p2 = (Path)copyPath;
          this.dataSequence = new LinkedList<>();
          for(int i = 0; i < p2.dataSequence.size(); i++)  {
              this.dataSequence.add(p2.dataSequence.get(i));
          }
          this.dataSequence.add(extendBy.target.name);
          this.start = p2.start;
          this.end = extendBy.target;
          this.distance = p2.distance + extendBy.weight;
        }

        /**
         * Allows the natural ordering of paths to be increasing with path distance.
         * When path distance is equal, the string comparison of end vertex data is used to break ties.
         * @param other is the other path that is being compared to this one
         * @return -1 when this path has a smaller distance than the other,
         *         +1 when this path has a larger distance that the other,
         *         and the comparison of end vertex data in string form when these distances are tied
         */
        public int compareTo(Path other) {
            int cmp = this.distance - other.distance;
            if(cmp != 0) return cmp; // use path distance as the natural ordering
            // when path distances are equal, break ties by comparing the string
            // representation of data in the end vertex of each path
            return this.end.name.toString().compareTo(other.end.name.toString());
        }
    }

    /**
     * Uses Dijkstra's shortest path algorithm to find and return the shortest path
     * between two vertices in this graph: start and end. This path contains an ordered list
     * of the data within each node on this path, and also the distance or cost of all edges
     * that are a part of this path.
     * @param start data item within first node in path
     * @param end data item within last node in path
     * @return the shortest path from start to end, as computed by Dijkstra's algorithm
     * @throws NoSuchElementException when no path from start to end can be found,
     *     including when no vertex containing start or end can be found
     */
    protected Path dijkstrasShortestPath(String start, String end) {
        PriorityQueue<Path> frontier = new PriorityQueue<Path>();
        LinkedList<Vertex> visited = new LinkedList<Vertex>();
        LinkedList<Path> fin = new LinkedList<Path>();

        Path curr = new Path(vertices.get(start));
        visited.add(curr.end);
        while(visited.size() < this.getVertexCount()) {

            for (int i = 0; i < curr.end.edgesLeaving.size(); i++) {
                if(visited.contains(curr.end.edgesLeaving.get(i).target)){
                    continue;
                }
                if(curr.end.edgesLeaving.get(i).target.name.equals(end))  {
                    fin.add(new Path(curr, curr.end.edgesLeaving.get(i)));
                    continue;
                }
                frontier.add(new Path(curr, curr.end.edgesLeaving.get(i)));
            }
            visited.add(curr.end);
            curr = frontier.poll();
        }
        if(fin.size() == 0)  {
            throw new NoSuchElementException();
        }
        int i = fin.size();
        for(int j = 0; j < i; j++) {
            Path t1 = fin.removeFirst();
            if(fin.peekFirst() != null) {
                Path t2 = fin.removeFirst();
                if (t2.compareTo(t1) > 0) {
                    fin.add(t1);
                } else fin.add(t2);
            }
            else curr = t1;
        }

        if (!curr.start.name.equals(start) || !curr.end.name.equals(end)) {
            throw new NoSuchElementException();
        }
        return curr;
    }

    private String toStringHelp(Path p)  {
        String s = "[ ";
        for(int i = 0; i < p.dataSequence.size(); i++) {
            s += p.dataSequence.get(i) + " ";
        }
        s += "]";
        return s;
    }

        /**
         * Returns the shortest path between start and end.
         * Uses Dijkstra's shortest path algorithm to find the shortest path.
         *
         * @param start the data item in the starting vertex for the path
         * @param end the data item in the destination vertex for the path
         * @return list of data item in vertices in order on the shortest path between vertex
         * with data item start and vertex with data item end, including both start and end
         * @throws NoSuchElementException when no path from start to end can be found
         *     including when no vertex containing start or end can be found
         */
    public List<String> shortestPath(String start, String end) {
        return dijkstrasShortestPath(start,end).dataSequence;
    }

    /**
     * Returns the cost of the path (sum over edge weights) between start and end.
     * Uses Dijkstra's shortest path algorithm to find the shortest path.
     *
     * @param start the data item in the starting vertex for the path
     * @param end the data item in the end vertex for the path
     * @return the cost of the shortest path between vertex with data item start
     * and vertex with data item end, including all edges between start and end
     * @throws NoSuchElementException when no path from start to end can be found
     *     including when no vertex containing start or end can be found
     */
    public int getPathCost(String start, String end) {
        return dijkstrasShortestPath(start, end).distance;
    }

}
