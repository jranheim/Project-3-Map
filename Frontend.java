import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;


public class Frontend {
    public static void main (String[] args) throws IOException {
        boolean quit = false;
        MetroMapper newMap = new MetroMapper<>("stations.csv", "edges.csv");

        while(!quit) {
            System.out.println("Welcome to the Metro Mapper.");

            Scanner scnr = new Scanner(System.in);
            String input = scnr.nextLine();
            if (input.equals("q")) {
                quit = true;
            }

        }


    }
}
