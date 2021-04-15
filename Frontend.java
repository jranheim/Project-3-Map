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

      // int i = 1;
      for (String station : newMap.getStations()) {
        System.out.println(station);
        // i++;
      }

      System.out.print("\nInput: ");
      Scanner scnr = new Scanner(System.in);
      String Choice = scnr.nextLine();
      System.out.println();
      String first;


      if (Choice.equals("A") || Choice.equals("B") || Choice.equals("C") || Choice.equals("D")
          || Choice.equals("E") || Choice.equals("F") || Choice.equals("G") || Choice.equals("H")) {
        first = Choice;
        chooseDestination(newMap, scnr, first);
      }
      if (Choice.equals("")) {
        continue;
      }
      if (Choice.equals("s")) {
        System.out.println("Saved Paths: " + newMap.getUserPaths());
      } else if (Choice.equals("q")) {
        quit = true;
      }
      System.out.println("Try Again.");
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
    String second;
    if (input.equals("A") || input.equals("B") || input.equals("C") || input.equals("D")
        || input.equals("E") || input.equals("F") || input.equals("G") || input.equals("H")) {
      second = input;
      chooseColor(newMap, scnr, first, second);
    }
    if (input.equals("s")) {
      System.out.println("Saved Paths: " + newMap.getUserPaths());
      System.out.println();
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

    String color;
    ArrayList<String> list = new ArrayList<String>();
    try {
      if (input.equals("Red") || input.equals("Blue") || input.equals("Green")) {
        color = input;
        list.add(newMap.parisMap.shortestPath(first, second, color).toString());
        System.out.print("Shortest Path: ");
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