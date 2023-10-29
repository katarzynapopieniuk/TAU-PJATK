package com.s22048;

public class CellIterator {

    public static void moveToNextCell(CellCoordinates currentCellCoordinates) throws ElementNotExistingException {
        currentCellCoordinates.setColumn(currentCellCoordinates.getColumn()+1);

        if(currentCellCoordinates.getColumn() == Constants.GRID_SIZE) {
            currentCellCoordinates.setColumn(Constants.MIN_INDEX);
            currentCellCoordinates.setRow(currentCellCoordinates.getRow()+1);
        }

        if(currentCellCoordinates.getRow() > Constants.MAX_INDEX) {
            throw new ElementNotExistingException("no next element");
        }
    }

    public static void moveToPreviousCell(CellCoordinates currentCellCoordinates) throws ElementNotExistingException {
        currentCellCoordinates.setColumn(currentCellCoordinates.getColumn()-1);

        if(currentCellCoordinates.getColumn() < Constants.MIN_INDEX) {
            currentCellCoordinates.setColumn(Constants.MAX_INDEX);
            currentCellCoordinates.setRow(currentCellCoordinates.getRow()-1);
        }

        if(currentCellCoordinates.getRow() <Constants.MIN_INDEX) {
            throw new ElementNotExistingException("no previous point exists");
        }
    }

    private CellIterator() {
    }
}
