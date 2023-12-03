package com.s22048;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BoardGenerator {

    private static final int BOARD_SIZE = 5;
    private static final int OBSTACLES_AMOUNT = 3;

    private static final Random random = new Random();

    public Board createBoard() {
        Position startPosition = getRandomEdgePosition();
        List<Position> takenPositions = new ArrayList<>();
        takenPositions.add(startPosition);

        Position endPosition;
        do {
            endPosition = getRandomEdgePosition();
        } while (isTaken(endPosition, takenPositions));
        takenPositions.add(endPosition);

        List<Position> obstaclePositions = new ArrayList<>();
        Position obstaclePosition;
        for (int i = 0; i < OBSTACLES_AMOUNT; i++) {
            do {
                obstaclePosition = getRandomPosition();
            } while (isTaken(obstaclePosition, takenPositions));
            takenPositions.add(obstaclePosition);
            obstaclePositions.add(obstaclePosition);
        }

        return new Board(startPosition, endPosition, obstaclePositions, BOARD_SIZE);
    }

    private Position getRandomEdgePosition() {
        int choice = random.nextInt(4);
        switch (choice) {
            case 0:
                return new Position(0, random.nextInt(BOARD_SIZE));
            case 1:
                return new Position(BOARD_SIZE - 1, random.nextInt(BOARD_SIZE));
            case 2:
                return new Position(random.nextInt(BOARD_SIZE), 0);
            case 3:
                return new Position(random.nextInt(BOARD_SIZE), BOARD_SIZE - 1);
        }

        return new Position(0, 0);
    }

    private boolean isTaken(Position position, List<Position> takenPositions) {
        return takenPositions.contains(position);
    }

    private Position getRandomPosition() {
        return new Position(random.nextInt(BOARD_SIZE), random.nextInt(BOARD_SIZE));
    }
}
