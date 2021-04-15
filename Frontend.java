// --== CS400 File Header Information ==--
// Name: Shuaib Aljabaly
// Email: Aljabaly@wisc.edu
// Team: KG Blue
// Role: Frontend Developer
// TA: Keren Chen
// Lecturer: Florian Heimerl
// Notes to Grader:

import java.util.Scanner;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.io.IOException;


public class Frontend {
  public static void main(String[] args) throws IOException {
    boolean quit = false;
    MetroMapper<Object> newMap = new MetroMapper<>("stations.csv", "edges.csv");
    while (!quit) {
      System.out.println("Welcome to the Metro Mapper!");
      System.out.println("We can help you find the quickest way to your destination!");
      System.out.println("Below you can see a list of subway stations.");
      System.out.println("Please choose your starting station.");
      System.out.println("To see your saved paths, input s.");
      System.out.println("To go back to list press ENTER");
      System.out.println("To quit, input q.");
      System.out.println("Saved Paths: " + newMap.getUserPaths());
      System.out.println();
      System.out.println();
      System.out.println("Input the letter of your starting station.");


      for (String station : newMap.getStations()) {
        System.out.println(station);

      }

      System.out.print("\nInput: ");
      Scanner scnr = new Scanner(System.in);
      String input = scnr.nextLine();
      System.out.println();
      if (input.equals("A")) {
        String start = newMap.getStations().get(0).toString();
        chooseDestination(newMap, scnr, start);
      }
      if (input.equals("B")) {
        String start = newMap.getStations().get(1).toString();
        chooseDestination(newMap, scnr, start);
      }
      if (input.equals("C")) {
        String start = newMap.getStations().get(2).toString();
        chooseDestination(newMap, scnr, start);
      }
      if (input.equals("D")) {
        String start = newMap.getStations().get(3).toString();
        chooseDestination(newMap, scnr, start);
      }
      if (input.equals("E")) {
        String start = newMap.getStations().get(4).toString();
        chooseDestination(newMap, scnr, start);
      }
      if (input.equals("F")) {
        String start = newMap.getStations().get(5).toString();
        chooseDestination(newMap, scnr, start);
      }
      if (input.equals("G")) {
        String start = newMap.getStations().get(6).toString();
        chooseDestination(newMap, scnr, start);
      }
      if (input.equals("H")) {
        String start = newMap.getStations().get(7).toString();
        chooseDestination(newMap, scnr, start);
      }
      if (input.equals("")) {
        continue;
      }
      if (input.equals("s")) {
        System.out.println("Saved Paths: " + newMap.getUserPaths());
      } else if (input.equals("q")) {
        quit = true;
      }

    }
    System.out.println("Session ended");

  }

  private static void chooseDestination(MetroMapper<Object> newMap, Scanner scnr, String first) {
    System.out.println("Now choose your destination.");
    for (String destination : newMap.getStations()) {
      System.out.println(destination);
    }
    System.out.print("\nInput: ");
    String input = scnr.nextLine();
    if (input.equals("A")) {
      String second = newMap.getStations().get(0).toString();
      chooseColor(newMap, scnr, first, second);
    }
    if (input.equals("B")) {
      String second = newMap.getStations().get(1).toString();
      chooseColor(newMap, scnr, first, second);
    }
    if (input.equals("C")) {
      String second = newMap.getStations().get(2).toString();
      chooseColor(newMap, scnr, first, second);
    }
    if (input.equals("D")) {
      String second = newMap.getStations().get(3).toString();
      chooseColor(newMap, scnr, first, second);
    }
    if (input.equals("E")) {
      String second = newMap.getStations().get(4).toString();
      chooseColor(newMap, scnr, first, second);
    }
    if (input.equals("F")) {
      String second = newMap.getStations().get(5).toString();
      chooseColor(newMap, scnr, first, second);
    }
    if (input.equals("G")) {
      String second = newMap.getStations().get(6).toString();
      chooseColor(newMap, scnr, first, second);
    }
    if (input.equals("H")) {
      String second = newMap.getStations().get(7).toString();
      chooseColor(newMap, scnr, first, second);
    }
    if (input.equals("s")) {
      System.out.println("Saved Paths: " + newMap.getUserPaths());
      System.out.println();
    }
    if (input.equals("")) {
      return;
    } else {

    }

  }

  private static void chooseColor(MetroMapper<Object> newMap, Scanner scnr, String first,
      String second) {
    System.out.println();
    System.out.println("Choose your Subway station line.");
    System.out.println();
    System.out.println("Red");
    System.out.println("Green");
    System.out.println("Blue");
    System.out.print("\nInput: ");
    String input = scnr.nextLine();
    System.out.println();

    try {
      if (input.equals("Red")) {
        String color = "Red";

        // newMap.addPath(first, second, color);
        ArrayList<String> list = new ArrayList<String>();
        System.out.print("Shortest Path: ");
        list.add(newMap.parisMap.shortestPath(first, second, color).toString());
        System.out.println(list.toString());
        int i = 0;
        i = newMap.parisMap.getPathCost(first, second, color);
        System.out.print("Path Cost: ");
        System.out.println(i + " Minutes");
        System.out.println();
        System.out.print("Do you want to save this path?");
        System.out.print("\nY/N: ");
        String inp = scnr.nextLine();
        if (inp.equals("Y")) {
          newMap.addPath(first, second, color);
          System.out.println("Path saved.");
          System.out.println();
          return;
        }

      }
      if (input.equals("Green")) {
        String color = "Green";

        // newMap.addPath(first, second, color);
        ArrayList<String> list = new ArrayList<String>();
        System.out.print("Shortest Path: ");
        list.add(newMap.parisMap.shortestPath(first, second, color).toString());
        System.out.println(list.toString());
        int i = 0;
        i = newMap.parisMap.getPathCost(first, second, color);
        System.out.print("Path Cost: ");
        System.out.println(i + " Minutes");
        System.out.println();
        System.out.print("Do you want to save this path?");
        System.out.print("\nY/N: ");
        String inp = scnr.nextLine();
        if (inp.equals("Y")) {
          newMap.addPath(first, second, color);
          System.out.println("Path saved.");
          System.out.println();
          return;
        }

      }
      if (input.equals("Blue")) {
        String color = "Blue";

        // newMap.addPath(first, second, color);
        ArrayList<String> list = new ArrayList<String>();
        System.out.print("Shortest Path: ");
        list.add(newMap.parisMap.shortestPath(first, second, color).toString());
        System.out.println(list.toString());
        int i = 0;
        i = newMap.parisMap.getPathCost(first, second, color);
        System.out.print("Path Cost: ");
        System.out.println(i + " Minutes");
        System.out.println();
        System.out.print("Do you want to save this path?");
        System.out.print("\nY/N: ");
        String inp = scnr.nextLine();
        if (inp.equals("Y")) {
          newMap.addPath(first, second, color);
          System.out.println("Path saved.");
          System.out.println();
          return;
        }

      }
    } catch (NoSuchElementException e) {
      System.out.println("Try another route.");
      return;
    } catch (NullPointerException e) {
      System.out.println("No Path available");
    }

  }

}
