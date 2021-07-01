package com.company;

// Class for creating instances of Cell, representing each individual space in the sudoku grid
public class Cell {

  private int value;

  public int getValue() {
    return value;
  }

  public void setValue(int aValue) { this.value = aValue; }

  Cell(int value) {

    this.value = value;

  }

}
