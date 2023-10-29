package com.s22048;

public class Main {

    public static void main(String[] args) {

        int[][] board = {
                { 8, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 3, 6, 0, 0, 0, 0, 0 },
                { 0, 7, 0, 0, 9, 0, 2, 0, 0 },
                { 0, 5, 0, 0, 0, 7, 0, 0, 0 },
                { 0, 0, 0, 0, 4, 5, 7, 0, 0 },
                { 0, 0, 0, 1, 0, 0, 0, 3, 0 },
                { 0, 0, 1, 0, 0, 0, 0, 6, 8 },
                { 0, 0, 8, 5, 0, 0, 0, 1, 0 },
                { 0, 9, 0, 0, 0, 0, 4, 0, 0 }
        };

        SudokuGrid grid;
        try {
            grid = new SudokuGrid(board);
        } catch (InvalidGridSizeException e) {
            return;
        }
        System.out.print(grid);

        SudokuSolver solver = new SudokuSolver(grid);
        System.out.print("\n\n");
        if(solver.trySolve()) {
            System.out.print("solved: ");
            System.out.print("\n\n");
            System.out.print(solver.getSolution().toString());
        }
        else
            System.out.println("Cannot solve");
    }
}
