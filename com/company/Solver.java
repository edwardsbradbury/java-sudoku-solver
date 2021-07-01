package com.company;

/* Sudoku solver class. Methods to test whether values can be inserted into grid cells without
   breaking the constraints for duplication in a row, column or sub-grid. Recursive backtracking method
   tests whether a valid solution can be built based on the grid state after each change */
public class Solver {

  // Method to test whether a given value can be entered into a given row without causing duplication
  private static boolean duplicateInRow(Cell[] aRow, int testValue) {

    for (Cell aCell : aRow) {
      if (aCell.getValue() == testValue) {
        return true;
      }
    }
    return false;

  }


  // Method to test whether a given value can be entered into a given column without causing duplication
  private static boolean duplicateInColumn(Cell[] aColumn, int testValue) {

    for (Cell aCell : aColumn) {
      if (aCell.getValue() == testValue) {
        return true;
      }
    }
    return false;

  }


  // Method to test whether a given value can be entered into a given subgrid without causing duplication
  private static boolean duplicateInSubgrid(Cell[][] aGrid, int rowNum, int colNum, int testValue) {

    int firstRow = rowNum - (rowNum % 3);
    int firstColumn = colNum - (colNum % 3);

    for (int i = firstRow; i < firstRow + 3; i++) {
      for (int j = firstColumn; j < firstColumn + 3; j++) {
        if (aGrid[i][j].getValue() == testValue) {
          return true;
        }
      }
    }
    return false;
  }


  /* Method to test whether a prospective value can be entered in a given cell without
     violating the duplication rules for row, column and subgrid */
  private static boolean testCell(Cell[][] aGrid, int rowNum, int colNum, int testValue) {

    /* By default, each sub-array in the grid is a row. My duplicateInColumn tester method
       takes an array of cells as a parameter. Hence, I need an array representing a column,
       which will be passed to that method */
    Cell[] testColumn = new Cell[aGrid.length];
    for (int rowIndex = 0; rowIndex < aGrid.length; rowIndex++) {
      Cell currCol = aGrid[rowIndex][colNum];
      testColumn[rowIndex] = currCol;
    }

    // Check for duplication
    boolean valueDuplicateInRow = duplicateInRow(aGrid[rowNum], testValue);
    boolean valueDuplicateInColumn = duplicateInColumn(testColumn, testValue);
    boolean valueDuplicateInSubgrid = duplicateInSubgrid(aGrid, rowNum, colNum, testValue);

    if (!valueDuplicateInRow && !valueDuplicateInColumn && !valueDuplicateInSubgrid) {
      return true;
    }
    return false;

  }


  // Method to solve a given sudoku puzzle, using recursive backtracking
  public static boolean solvePuzzle(Grid aGrid) {

    Cell[][] currGrid = aGrid.getGrid();
    int length = currGrid.length;
    int operations = aGrid.getOperationsToSolve();

    // Iterate through the cells of the grid
    for (int rowIndex = 0; rowIndex < length; rowIndex++) {
      for (int colIndex = 0; colIndex < length; colIndex++) {
        Cell currentCell = currGrid[rowIndex][colIndex];
        // Identify the first empty cell
        if (currentCell.getValue() == 0) {
          // Test which integers can potentially be entered in this empty cell
          for (int testValue = 1; testValue <= length; testValue++) {
            if (testCell(currGrid, rowIndex, colIndex, testValue)) {
              currentCell.setValue(testValue);
//              aGrid.setOperationsToSolve(++operations);
              /* Recursive call controls backtracking. Each time a cell is filled with a valid integer, calling solvePuzzle
                 returns true if the next empty cell can also hold a valid integer. False is returned if the next empty
                 cell cannot hold an integer 1-9 without breaking duplication constraints */
              if (solvePuzzle(aGrid)) {
                aGrid.setOperationsToSolve(++operations);
                return true;
              }
              /* Placing current value of testValue in currentCell meant the next empty cell cannot hold an integer 1-9
                 without breaking duplication constraints (solvePuzzle returned false). Empty currentCell, increment
                 testValue & repeat for-loop */
              currentCell.setValue(0);
            }
          }
          /* Returned to the solvePuzzle call on line 98 when the testValue for-loop has tested all numbers 1-9 in a cell
             and none adhered to the constraints. The solution being tested is a dead-end; backtrack  */
          return false;
        }
      }
    }

    // All cells are filled with valid integers
    aGrid.setPuzzleSolved(true);
    return true;
  }

}
