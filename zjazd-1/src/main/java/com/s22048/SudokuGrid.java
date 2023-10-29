package com.s22048;

import com.google.common.base.Strings;

public class SudokuGrid {

    private final int[][] grid;

    public SudokuGrid() {
        this.grid = new int[Constants.GRID_SIZE][Constants.GRID_SIZE];
    }

    public SudokuGrid(int[][] grid) throws InvalidGridSizeException {
        this.grid = grid;
        if(!isSizeValid())
            throw new InvalidGridSizeException("Grid has wrong size");
    }


    public int getValue(int row, int column) throws InvalidCoordinateException {
        if(!isPointValid(row,column))
            throw new InvalidCoordinateException("Coordinates out of range");
        return grid[row][column];
    }

    public void setValue(int row, int column, int value) throws InvalidCoordinateException {
        if(!isValueValid(value))
            throw new IllegalArgumentException("value out of range, row: " + row + ", column: " + column + ", value: " + value);
        if(!isPointValid(row,column))
            throw new InvalidCoordinateException("Coordinates out of range");
        grid[row][column] = value;
    }

    private boolean isSizeValid() {
        for(int[] row : grid) {
            if(row.length != grid.length)
                return false;
        }
        return true;
    }

    public static boolean isPointValid(int row, int column) {
        return row >= Constants.MIN_INDEX && row <= Constants.MAX_INDEX && column >= Constants.MIN_INDEX && column <= Constants.MAX_INDEX;
    }

    public static boolean isValueValid(int value) {
        return value >= Constants.MIN_VALUE && value <= Constants.MAX_VALUE;
    }

    public void resetCell(int row, int column) {
        if(isPointValid(row,column))
            grid[row][column] = Constants.NO_VALUE;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(Strings.repeat("_", Constants.GRID_SIZE * 5));
        builder.append("\n");
        for(int[] row : grid) {
            for(int i = Constants.MIN_INDEX; i<=Constants.MAX_INDEX; i++)
                builder.append(" | ").append(row[i]);
            builder.append(" |\n");
            builder.append(Strings.repeat("_", Constants.GRID_SIZE * 5));
            builder.append("\n");
        }
        return builder.toString();
    }

    public SudokuGrid getCopy() {
        SudokuGrid copy = new SudokuGrid();

        for (int row = Constants.MIN_INDEX; row <= Constants.MAX_INDEX; row++) {
            for (int col = Constants.MIN_INDEX; col <= Constants.MAX_INDEX; col++) {
                if(getValue(row, col) != Constants.NO_VALUE)
                    copy.setValue(row, col, this.getValue(row, col));
            }
        }

        return copy;
    }
}
