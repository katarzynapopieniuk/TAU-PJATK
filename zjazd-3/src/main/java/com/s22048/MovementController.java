package com.s22048;

import java.util.List;

public class MovementController {

    public boolean tryMoveRight(Board board) {
        int newX = board.getCurrentPosition().getX() + 1;
        if(isValid(new Position(newX, board.getCurrentPosition().getY()), board)) {
            board.getCurrentPosition().setX(newX);
            return true;
        }

        return false;
    }

    public boolean tryMoveLeft(Board board) {
        int newX = board.getCurrentPosition().getX() - 1;
        if(isValid(new Position(newX, board.getCurrentPosition().getY()), board)) {
            board.getCurrentPosition().setX(newX);
            return true;
        }

        return false;
    }

    public boolean tryMoveUp(Board board) {
        int newY = board.getCurrentPosition().getY() - 1;
        if(isValid(new Position(board.getCurrentPosition().getX(), newY), board)) {
            board.getCurrentPosition().setY(newY);
            return true;
        }

        return false;
    }

    public boolean tryMoveDown(Board board) {
        int newY = board.getCurrentPosition().getY() + 1;
        if(isValid(new Position(board.getCurrentPosition().getX(), newY), board)) {
            board.getCurrentPosition().setY(newY);
            return true;
        }

        return false;
    }

    private boolean isValid(Position position, Board board) {
        return isValidCoordinate(position.getX(), board.getSize())
                && isValidCoordinate(position.getY(), board.getSize())
                && isNotObstacle(position, board.getObstaclesPositions());
    }

    private boolean isValidCoordinate(int coordinate, int boardSize) {
        return coordinate >= 0 && coordinate < boardSize;
    }

    private boolean isNotObstacle(Position position, List<Position> obstaclesPositions) {
        return !obstaclesPositions.contains(position);
    }
}
