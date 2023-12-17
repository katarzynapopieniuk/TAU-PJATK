package com.s22048;

public class Main {

    public static void main(String[] args) {
        BoardGenerator boardGenerator = new BoardGenerator();
        Board board = boardGenerator.createBoard();
        BoardDisplay.display(board);
    }
}
