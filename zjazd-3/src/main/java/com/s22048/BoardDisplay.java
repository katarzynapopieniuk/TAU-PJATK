package com.s22048;

public class BoardDisplay {

    private static final String START_MARK = "S";
    private static final String END_MARK = "E";
    private static final String OBSTACLE_MARK = "█";
    private static final String FREE_POSITION_MARK = ".";
    private static final String CURRENT_POSITION_MARK = "*";

    public static void display(Board board) {
        StringBuilder builder = new StringBuilder();
        builder.append("▄".repeat(board.getSize()+2));
        builder.append(System.lineSeparator());
        for(int i=0; i < board.getSize(); i++) {
            builder.append("█");
            for(int j = 0; j < board.getSize(); j++) {
                Position position = new Position(j, i);
                builder.append(getMatchingPositionMark(board, position));
            }
            builder.append("█");
            builder.append(System.lineSeparator());
        }
        builder.append("▀".repeat(board.getSize()+2));
        builder.append(System.lineSeparator());

        System.out.print(builder);
    }

    private static String getMatchingPositionMark(Board board, Position position) {
        if(board.getStartPosition().equals(position)) {
            return START_MARK;
        }
        if(board.getEndPosition().equals(position)) {
            return END_MARK;
        }
        if(board.getCurrentPosition().equals(position)) {
            return CURRENT_POSITION_MARK;
        }
        if(board.getObstaclesPositions().contains(position)) {
            return OBSTACLE_MARK;
        }
        return FREE_POSITION_MARK;
    }

    private BoardDisplay() {
    }
}
