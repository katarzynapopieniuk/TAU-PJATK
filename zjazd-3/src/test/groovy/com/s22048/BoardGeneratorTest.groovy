package com.s22048

import spock.lang.Specification

class BoardGeneratorTest extends Specification {

    private final BoardGenerator boardGenerator = new BoardGenerator()

    def "start position should be on board's edge"() {
        given:
        Board board = boardGenerator.createBoard()

        expect:
        board.getStartPosition().getX() == 0 || board.getStartPosition().getX() == board.getSize() - 1 || board.getStartPosition().getY() == 0 || board.getStartPosition().getY() == board.getSize() - 1
    }

    def "end position should be on board's edge"() {
        given:
        Board board = boardGenerator.createBoard()

        expect:
        board.getEndPosition().getX() == 0 || board.getEndPosition().getX() == board.getSize() - 1 || board.getEndPosition().getY() == 0 || board.getEndPosition().getY() == board.getSize() - 1
    }

    def "start position should not be end position"() {
        given:
        Board board = boardGenerator.createBoard()

        expect:
        board.getStartPosition() != board.getEndPosition()
    }

    def "should generate 3 obstacles"() {
        given:
        Board board = boardGenerator.createBoard()

        expect:
        3 == board.getObstaclesPositions().size()
    }

    def "obstacles should have different positions"() {
        given:
        Board board = boardGenerator.createBoard()
        List<Position> obstaclePositions = board.getObstaclesPositions()

        expect:
        obstaclePositions.get(0) != obstaclePositions.get(1)
        obstaclePositions.get(0) != obstaclePositions.get(2)
        obstaclePositions.get(1) != obstaclePositions.get(2)
    }

    def "obstacles should not be also start or end positions"() {
        given:
        Board board = boardGenerator.createBoard()

        expect:
        board.getObstaclesPositions().stream().noneMatch {it == board.getStartPosition()}
        board.getObstaclesPositions().stream().noneMatch {it == board.getEndPosition()}
    }
}
