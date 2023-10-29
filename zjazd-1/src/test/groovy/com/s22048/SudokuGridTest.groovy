package com.s22048

import spock.lang.Specification

class SudokuGridTest extends Specification {

    def "when set value for invalid coordinates should throw exception"() {
        given:
        SudokuGrid sudokuGrid = new SudokuGrid()

        when:
        sudokuGrid.setValue(9, 9, 9)

        then:
        thrown(InvalidCoordinateException)
    }

    def "when set incorrect value should throw exception"() {
        given:
        SudokuGrid sudokuGrid = new SudokuGrid()

        when:
        sudokuGrid.setValue(1, 1, 10)

        then:
        thrown(IllegalArgumentException)
    }

    def "when create grid with wrong size should throw exception"() {
        given:
        int[][] board = [
            [ 8, 0, 0, 0, 0, 0, 0, 0, 0 ],
            [ 0, 0, 3, 6, 0, 0, 0, 0, 0 ],
            [ 0, 7, 0, 0, 9, 0, 2, 0, 0 ],
            [ 0, 5, 0, 0, 0, 7, 0, 0, 0 ],
            [ 0, 0, 0, 0, 4, 5, 7, 0, 0 ],
            [ 0, 0, 0, 1, 0, 0, 0, 3, 0 ],
            [ 0, 0, 1, 0, 0, 0, 0, 6, 8 ],
            [ 0, 0, 8, 5, 0, 0, 0, 1, 0 ],
            [ 0, 9, 0, 0, 0, 0, 4, 0, 0 ],
            [ 0, 9, 0, 0, 0, 0, 4, 0, 0 ]
        ]

        when:
        new SudokuGrid(board)

        then:
        thrown(InvalidGridSizeException)
    }

    def "should reset cell value"() {
        given:
        int[][] board = [
                [ 8, 0, 0, 0, 0, 0, 0, 0, 0 ],
                [ 0, 0, 3, 6, 0, 0, 0, 0, 0 ],
                [ 0, 7, 0, 0, 9, 0, 2, 0, 0 ],
                [ 0, 5, 0, 0, 0, 7, 0, 0, 0 ],
                [ 0, 0, 0, 0, 4, 5, 7, 0, 0 ],
                [ 0, 0, 0, 1, 0, 0, 0, 3, 0 ],
                [ 0, 0, 1, 0, 0, 0, 0, 6, 8 ],
                [ 0, 0, 8, 5, 0, 0, 0, 1, 0 ],
                [ 0, 9, 0, 0, 0, 0, 4, 0, 0 ]
        ]
        SudokuGrid sudokuGrid = new SudokuGrid(board)

        when:
        sudokuGrid.resetCell(0, 0)

        then:
        sudokuGrid.getValue(0, 0) == Constants.NO_VALUE
    }

    def "should set value"() {
        given:
        int[][] board = [
                [ 8, 0, 0, 0, 0, 0, 0, 0, 0 ],
                [ 0, 0, 3, 6, 0, 0, 0, 0, 0 ],
                [ 0, 7, 0, 0, 9, 0, 2, 0, 0 ],
                [ 0, 5, 0, 0, 0, 7, 0, 0, 0 ],
                [ 0, 0, 0, 0, 4, 5, 7, 0, 0 ],
                [ 0, 0, 0, 1, 0, 0, 0, 3, 0 ],
                [ 0, 0, 1, 0, 0, 0, 0, 6, 8 ],
                [ 0, 0, 8, 5, 0, 0, 0, 1, 0 ],
                [ 0, 9, 0, 0, 0, 0, 4, 0, 0 ]
        ]
        SudokuGrid sudokuGrid = new SudokuGrid(board)

        when:
        sudokuGrid.setValue(0, 0, 9)

        then:
        sudokuGrid.getValue(0, 0) == 9
    }
}
