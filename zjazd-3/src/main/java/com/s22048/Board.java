package com.s22048;

import java.util.List;

public class Board {

    private final Position startPosition;
    private final Position endPosition;
    private final Position currentPosition;
    private final List<Position> obstaclesPositions;
    private final int size;


    public Board(Position startPosition, Position endPosition, List<Position> obstaclesPositions, int size) {
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        this.obstaclesPositions = obstaclesPositions;
        this.currentPosition = new Position(startPosition);
        this.size = size;
    }

    public Position getStartPosition() {
        return startPosition;
    }

    public Position getEndPosition() {
        return endPosition;
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public List<Position> getObstaclesPositions() {
        return obstaclesPositions;
    }

    public int getSize() {
        return size;
    }
}
