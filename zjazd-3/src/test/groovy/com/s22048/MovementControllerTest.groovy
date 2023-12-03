package com.s22048

import spock.lang.Specification

class MovementControllerTest extends Specification {

    private Board board

    private final MovementController movementController = new MovementController();

    void setup() {
        List<Position> obstaclesPositions = new ArrayList<Position>()
        obstaclesPositions.add(new Position(1, 1))
        board = new Board(
                new Position(0, 0),
                new Position(4, 4),
                obstaclesPositions,
                5
        )
    }

    def "should move right when possible"() {
        given:
        board.getCurrentPosition().setX(2)
        board.getCurrentPosition().setY(2)

        when:
        boolean moved = movementController.tryMoveRight(board)

        then:
        moved
        board.getCurrentPosition().getX() == 3
        board.getCurrentPosition().getY() == 2
    }

    def "should not move right when on right edge"() {
        given:
        board.getCurrentPosition().setX(4)
        board.getCurrentPosition().setY(2)

        when:
        boolean moved = movementController.tryMoveRight(board)

        then:
        !moved
        board.getCurrentPosition().getX() == 4
        board.getCurrentPosition().getY() == 2
    }

    def "should not move right when obstacle"() {
        given:
        board.getCurrentPosition().setX(0)
        board.getCurrentPosition().setY(1)

        when:
        boolean moved = movementController.tryMoveRight(board)

        then:
        !moved
        board.getCurrentPosition().getX() == 0
        board.getCurrentPosition().getY() == 1
    }

    def "should move left when possible"() {
        given:
        board.getCurrentPosition().setX(2)
        board.getCurrentPosition().setY(2)

        when:
        boolean moved = movementController.tryMoveLeft(board)

        then:
        moved
        board.getCurrentPosition().getX() == 1
        board.getCurrentPosition().getY() == 2
    }

    def "should not move left when on left edge"() {
        given:
        board.getCurrentPosition().setX(0)
        board.getCurrentPosition().setY(2)

        when:
        boolean moved = movementController.tryMoveLeft(board)

        then:
        !moved
        board.getCurrentPosition().getX() == 0
        board.getCurrentPosition().getY() == 2
    }

    def "should not move left when obstacle"() {
        given:
        board.getCurrentPosition().setX(2)
        board.getCurrentPosition().setY(1)

        when:
        boolean moved = movementController.tryMoveLeft(board)

        then:
        !moved
        board.getCurrentPosition().getX() == 2
        board.getCurrentPosition().getY() == 1
    }

    def "should move up when possible"() {
        given:
        board.getCurrentPosition().setX(2)
        board.getCurrentPosition().setY(2)

        when:
        boolean moved = movementController.tryMoveUp(board)

        then:
        moved
        board.getCurrentPosition().getX() == 2
        board.getCurrentPosition().getY() == 1
    }

    def "should not move up when on upper edge"() {
        given:
        board.getCurrentPosition().setX(2)
        board.getCurrentPosition().setY(0)

        when:
        boolean moved = movementController.tryMoveUp(board)

        then:
        !moved
        board.getCurrentPosition().getX() == 2
        board.getCurrentPosition().getY() == 0
    }

    def "should not move up when obstacle"() {
        given:
        board.getCurrentPosition().setX(1)
        board.getCurrentPosition().setY(2)

        when:
        boolean moved = movementController.tryMoveUp(board)

        then:
        !moved
        board.getCurrentPosition().getX() == 1
        board.getCurrentPosition().getY() == 2
    }

    def "should move down when possible"() {
        given:
        board.getCurrentPosition().setX(2)
        board.getCurrentPosition().setY(2)

        when:
        boolean moved = movementController.tryMoveDown(board)

        then:
        moved
        board.getCurrentPosition().getX() == 2
        board.getCurrentPosition().getY() == 3
    }

    def "should not move down when on down edge"() {
        given:
        board.getCurrentPosition().setX(0)
        board.getCurrentPosition().setY(4)

        when:
        boolean moved = movementController.tryMoveDown(board)

        then:
        !moved
        board.getCurrentPosition().getX() == 0
        board.getCurrentPosition().getY() == 4
    }

    def "should not move down when obstacle"() {
        given:
        board.getCurrentPosition().setX(1)
        board.getCurrentPosition().setY(0)

        when:
        boolean moved = movementController.tryMoveDown(board)

        then:
        !moved
        board.getCurrentPosition().getX() == 1
        board.getCurrentPosition().getY() == 0
    }
}
