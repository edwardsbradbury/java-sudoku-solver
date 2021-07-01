package com.company;
import java.io.File;

/* Load a sudoku puzzle template from a file, create a new instance of Grid passing the template from file,
   then invoke the solvePuzzle method in Solver class, passing it the puzzle Grid instance */
public class Main {

  public static void main(String[] args) {

    // Load file
    File puzzle = new File("extensionPuzzle.txt");
    // Create instance of Grid, passing it the file above
    Grid sudokuPuzzle = new Grid(puzzle);
    // Log the initial state of the puzzle
    System.out.println("\nInitial state of puzzle: \n"+sudokuPuzzle.toString());
    // Try to solve the puzzle
    if (Solver.solvePuzzle(sudokuPuzzle)) {
      System.out.println("\nFinal state of puzzle: \n"+sudokuPuzzle.toString());
    } else {
      System.out.println("Couldn't solve the sudoku");
    }

  }
}
