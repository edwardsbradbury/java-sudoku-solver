package com.company;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// Class for creating instances of sudoku Grids
public class Grid {

  // Private instance variables / properties of a Grid instance
  private Cell[][] grid = new Cell[9][9];
  private File sudoko;
  private boolean puzzleSolved = false;
  private int operationsToSolve = 0;

  public Cell[][] getGrid() {
    return grid;
  }

  public boolean getPuzzleSolved() {
    return puzzleSolved;
  }

  public void setPuzzleSolved(boolean isItSolved) {
    this.puzzleSolved = isItSolved;
  }

  public int getOperationsToSolve() {
    return operationsToSolve;
  }

  public void setOperationsToSolve(int numOperations) {
    this.operationsToSolve += numOperations;
  }


  /* Overridden toString method to print the Grid to the console, followed by a
     message to say either that the puzzle is unsolved or (if solved) how many
     operations it took to solve */
  public String toString() {
    String gridString = "";

    for (int i = 0; i < this.grid.length; i++) {
      if (i % 3 == 0 && i > 0) {
        gridString += "\n-----------------------------";
      }
      gridString += "\n";
      for (int j = 0; j < this.grid.length; j++) {
        if (j % 3 == 0 && j > 0) {
          gridString += "|";
        }
        gridString += " " + this.grid[i][j].getValue() + " ";
      }
    }

    if (!this.puzzleSolved) {
      gridString += "\n\nThis sudoku puzzle is unsolved";
    } else {
      gridString += "\n\nThis sudoku puzzle took " + this.getOperationsToSolve() + " operations to solve";
    }

    return gridString;
  }


  /* Constructor for instances of Grid. Loads the puzzle grid template from file and creates 2d array to represent
     the puzzle template. Creates a Cell instance in each index of the inner array */
  Grid(File aSudoko) {

    this.sudoko = aSudoko;

    try {
      Scanner in = new Scanner(this.sudoko);
      while (in.hasNextLine()) {
        for (int row = 0; row < this.grid.length; row++) {
          String rowString = in.nextLine();
          String[] rowValues = rowString.split(",");
          for (int column = 0; column < rowValues.length; column++) {
            int cellValue = Integer.parseInt(rowValues[column]);
            this.grid[row][column] = new Cell(cellValue);
          }
        }
      }
    } catch (FileNotFoundException err) {
      System.out.println(err);
    }

  }

}
