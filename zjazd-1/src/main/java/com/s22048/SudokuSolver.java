package com.s22048;

public class SudokuSolver {

    public final SudokuGrid grid;
    public final SudokuGrid gridWorkSheet;
    private int currentRow;
    private int currentColumn;
    private boolean isSolved;

    public SudokuSolver(SudokuGrid grid) {
        this.grid = grid;
        this.gridWorkSheet = grid.getCopy();
        isSolved = false;
    }

    public SudokuSolver(SudokuGrid grid, SudokuGrid gridWorkSheet) {
        this.grid = grid;
        this.gridWorkSheet = gridWorkSheet;
        isSolved = false;
    }

    public SudokuGrid getSolution() {
        if (isSolved) {
            return gridWorkSheet;
        } else return null;
    }

    public boolean trySolve() {

        currentRow = Constants.MIN_INDEX;
        currentColumn = Constants.MIN_INDEX - 1;

        do {
            if (!tryFindNextEmptyCell())
                return true;

            while (!tryToFillCell()) {
                gridWorkSheet.resetCell(currentRow, currentColumn);
                if (tryFindPreviousEmpty())
                    return false;
            }
        } while (currentCellIsInAllowedRange());
        isSolved = true;
        return true;
    }

    private boolean tryFindPreviousEmpty() {
        try {
            findPreviousEmptyCell();
        } catch (ElementNotExistingException e) {
            return true;
        }
        return false;
    }

    private boolean currentCellIsInAllowedRange() {
        return currentRow < Constants.MAX_INDEX || currentColumn < Constants.MAX_INDEX;
    }

    private boolean tryFindNextEmptyCell() {
        try {
            findNextEmptyCell();
        } catch (ElementNotExistingException e) {
            return false;
        }
        return true;
    }

    public void findNextEmptyCell() throws ElementNotExistingException { // TODO to private
        do {
            CellCoordinates coordinates = new CellCoordinates(currentRow, currentColumn);
            CellIterator.moveToNextCell(coordinates);
            currentColumn = coordinates.getColumn();
            currentRow = coordinates.getRow();
        } while (isPointFilled());
    }

    public void findPreviousEmptyCell() throws ElementNotExistingException { // TODO to private
        do {
            CellCoordinates coordinates = new CellCoordinates(currentRow, currentColumn);
            CellIterator.moveToPreviousCell(coordinates);
            currentColumn = coordinates.getColumn();
            currentRow = coordinates.getRow();
        } while (isPointFilled());
    }

    public boolean isPointFilled() {
        try {
            return grid.getValue(currentRow, currentColumn) != Constants.NO_VALUE;
        } catch (InvalidCoordinateException e) {
            e.printStackTrace();
        }
        return false;
    }

    protected boolean tryToFillCell() {
        int value;

        try {
            value = gridWorkSheet.getValue(currentRow, currentColumn);
        } catch (InvalidCoordinateException e) {
            e.printStackTrace();
            return false;
        }

        while (value < Constants.MAX_VALUE) {
            value++;

            if (doesValueMatch(value)) {
                try {
                    gridWorkSheet.setValue(currentRow, currentColumn, value);
                } catch (InvalidCoordinateException e) {
                    e.printStackTrace();
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    public boolean doesValueMatch(int value) {
        try {
            if (IsValueInCurrentRow(value))
                return false;

            if (IsValueInCurrentColumn(value))
                return false;

            if (isValueInCurrentSubsquare(value))
                return false;

        } catch (InvalidCoordinateException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean IsValueInCurrentColumn(int value) throws InvalidCoordinateException {
        for (int i = Constants.MIN_INDEX; i <= Constants.MAX_INDEX; i++) {

            if (gridWorkSheet.getValue(i, currentColumn) == value)
                return true;
        }

        return false;
    }

    private boolean IsValueInCurrentRow(int value) throws InvalidCoordinateException {
        for (int i = Constants.MIN_INDEX; i <= Constants.MAX_INDEX; i++) {
            if (gridWorkSheet.getValue(currentRow, i) == value)
                return true;
        }

        return false;
    }

    public boolean isValueInCurrentSubsquare(int value) throws InvalidCoordinateException {
        int minRow = Constants.SUB_SQUARE_SIZE * (currentRow / Constants.SUB_SQUARE_SIZE);
        int maxRow = Constants.SUB_SQUARE_SIZE * (currentRow / Constants.SUB_SQUARE_SIZE) + Constants.SUB_SQUARE_SIZE - 1;
        int minColumn = Constants.SUB_SQUARE_SIZE * (currentColumn / Constants.SUB_SQUARE_SIZE);
        int maxColumn = Constants.SUB_SQUARE_SIZE * (currentColumn / Constants.SUB_SQUARE_SIZE) + Constants.SUB_SQUARE_SIZE - 1;
        for (int r = minRow; r <= maxRow; r++) {
            for (int c = minColumn; c <= maxColumn; c++)
                if (gridWorkSheet.getValue(r, c) == value)
                    return true;
        }
        return false;
    }

    public int getCurrentRow() {
        return currentRow;
    }

    public int getCurrentColumn() {
        return currentColumn;
    }

    public void setCurrentRow(int currentRow) {
        this.currentRow = currentRow;
    }

    public void setCurrentColumn(int currentColumn) {
        this.currentColumn = currentColumn;
    }
}
