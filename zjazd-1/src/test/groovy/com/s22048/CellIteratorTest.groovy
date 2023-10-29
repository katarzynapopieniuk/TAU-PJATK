package com.s22048

import spock.lang.Specification

class CellIteratorTest extends Specification {

    def "should move to next cell"() {
        given:
        CellCoordinates cellCoordinates = new CellCoordinates(1, 1)

        when:
        CellIterator.moveToNextCell(cellCoordinates)

        then:
        cellCoordinates.getRow() == 1
        cellCoordinates.getColumn() == 2
        notThrown(ElementNotExistingException)
    }

    def "should throw exception when next coordinate doesn't exist"() {
        given:
        CellCoordinates cellCoordinates = new CellCoordinates(8, 8)

        when:
        CellIterator.moveToNextCell(cellCoordinates)

        then:
        thrown(ElementNotExistingException)
    }

    def "should move to previous cell"() {
        given:
        CellCoordinates cellCoordinates = new CellCoordinates(8, 0)

        when:
        CellIterator.moveToPreviousCell(cellCoordinates)

        then:
        cellCoordinates.getRow() == 7
        cellCoordinates.getColumn() == 8
        notThrown(ElementNotExistingException)
    }

    def "should throw exception when previous coordinate doesn't exist"() {
        given:
        CellCoordinates cellCoordinates = new CellCoordinates(0, 0)

        when:
        CellIterator.moveToPreviousCell(cellCoordinates)

        then:
        thrown(ElementNotExistingException)
    }
}
