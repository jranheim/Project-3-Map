import java.util.List;

public interface MapperInterface {
    
    public List<String> getStations(); // returns list of stations for user to choose from

    public void addPath(String start, String end); // adds listed path to user's saved paths

    public List<List<String>> getUserPaths(); // returns user's saved paths

    public int getPathCost(String start, String end); // gets path cost 

    public List<String> shortestPath(String start, String end); // uses algorithm to get shortest path

}
