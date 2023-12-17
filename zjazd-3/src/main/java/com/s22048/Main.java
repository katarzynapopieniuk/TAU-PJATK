package com.s22048;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class Main {

    private static final String INSTRUCTION = "S - START" +
            System.lineSeparator() +
            "E - END" +
            System.lineSeparator() +
            "* - CURRENT POSITION" +
            System.lineSeparator() +
            "PRESS ARROWS TO MOVE" +
            System.lineSeparator() +
            "REACH THE END TO WIN" +
            System.lineSeparator();

    public static void main(String[] args) {
        BoardGenerator boardGenerator = new BoardGenerator();
        Board board = boardGenerator.createBoard();
        MovementController movementController = new MovementController();
        System.out.print(INSTRUCTION);
        BoardDisplay.display(board);

        JFrame myJFrame = new JFrame();

        myJFrame.addKeyListener(prepareKeyAdapter(movementController, board));

        myJFrame.setVisible(true);
    }

    private static KeyAdapter prepareKeyAdapter(MovementController movementController, Board board) {
        return new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_UP) {
                    movementController.tryMoveUp(board);
                    BoardDisplay.display(board);
                } else if (keyCode == KeyEvent.VK_DOWN) {
                    movementController.tryMoveDown(board);
                    BoardDisplay.display(board);
                } else if (keyCode == KeyEvent.VK_LEFT) {
                    movementController.tryMoveLeft(board);
                    BoardDisplay.display(board);
                } else if (keyCode == KeyEvent.VK_RIGHT) {
                    movementController.tryMoveRight(board);
                    BoardDisplay.display(board);
                }
                if(board.getCurrentPosition().equals(board.getEndPosition())) {
                    System.out.print("You won!");
                    System.out.print(System.lineSeparator());
                }
            }
        };
    }
}
