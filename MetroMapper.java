// --== CS400 File Header Information ==--
// Name: Jake Schroeder
// Email: schroeder22@wisc.edu
// Team: KG Blue
// Role: Data Wrangler
// TA: Keren Chen
// Lecturer: Florian Heimerl
// Notes to Grader:

import java.util.List;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;

/**
 * MetroMapper that reads in data from 2 CSVs and converts the info in them 
 * into a graph of Stations and edges between the stations.
 */

public class MetroMapper<T> implements MapperInterface {

    private List<List<String>> userPaths = new ArrayList<List<String>>();
    
    private List<String> stationNames = new ArrayList<String>(); // list of the station's names
    
    public MetroMap<T> parisMap = new MetroMap<T>(); // graph to store paris metro stations and info

    public MetroMapper(String stationFilePath, String edgeFilePath) throws FileNotFoundException, IOException {

        String data = "";

        try {
            File file = new File(stationFilePath);
            Scanner myReader = new Scanner(file);

            while (myReader.hasNextLine()) {
                data += myReader.nextLine() + "\n";
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Station file not found.");
            e.printStackTrace();
        }
        String[] test = data.split("\n");
        for (String s : test) {
            String[] array = s.split(",");
            // add station to graph as a vertex
            parisMap.insertVertex(array[0], Boolean.parseBoolean(array[1]), Boolean.parseBoolean(array[2]), Boolean.parseBoolean(array[3]));
            stationNames.add(array[0]); // add station name to list of names
        }
        
        data = "";

        try {
            File file = new File(edgeFilePath);
            Scanner myReader = new Scanner(file);

            while (myReader.hasNextLine()) {
                data += myReader.nextLine() + "\n";
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Edge file not found.");
            e.printStackTrace();
        }

        String[] test2 = data.split("\n");
        for (String s : test2) {
            String[] array2 = s.split(",");
            // add edges to graph
            parisMap.insertEdge(array2[0], array2[1], Integer.parseInt(array2[2]));
        }
        
    }

    @Override
    public List<String> getStations() {
        return stationNames;
    }

    @Override
    public void addPath(String start, String end, String s) {
        userPaths.add(shortestPath(start, end, s));
        
    }

    @Override
    public List<List<String>> getUserPaths() {
        return userPaths;
    }

    @Override
    public int getPathCost(String start, String end, String s) {
        return parisMap.dijkstrasShortestPath(start, end,s).distance;
    }

    @Override
    public List<String> shortestPath(String start, String end, String s) {
        return parisMap.dijkstrasShortestPath(start,end, s).dataSequence;
    }

    


}
