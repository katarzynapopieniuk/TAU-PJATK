package com.s22048

import spock.lang.Specification

class SudokuSolverTest extends Specification {

    def "should check every cell if is empty"() {
        given:
        def sudokuGrid = Mock(SudokuGrid)
        def sudokuSolver = new SudokuSolver(sudokuGrid)

        when:
        sudokuSolver.findNextEmptyCell()

        then:
        81 * sudokuGrid.getValue(*_) >> 8
        thrown(ElementNotExistingException)
    }

    def "should return true for filled point"() {
        given:
        def sudokuGrid = Mock(SudokuGrid)
        def sudokuSolver = new SudokuSolver(sudokuGrid)

        when:
        def isFilled = sudokuSolver.isPointFilled()

        then:
        1 * sudokuGrid.getValue(*_) >> 8
        isFilled
    }

    def "should return false for empty point"() {
        given:
        def sudokuGrid = Mock(SudokuGrid)
        def sudokuSolver = new SudokuSolver(sudokuGrid)

        when:
        def isFilled = sudokuSolver.isPointFilled()

        then:
        1 * sudokuGrid.getValue(*_) >> Constants.NO_VALUE
        !isFilled
    }
}
