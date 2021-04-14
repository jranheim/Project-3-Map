import java.util.List;

public interface MapperInterface {
    
    public List<String> getStations(); // returns list of stations for user to choose from

    public void addPath(String start, String end, String s); // adds listed path to user's saved paths

    public List<List<String>> getUserPaths(); // returns user's saved paths

    public int getPathCost(String start, String end, String s); // gets path cost 

    public List<String> shortestPath(String start, String end, String s); // uses algorithm to get shortest path

}
