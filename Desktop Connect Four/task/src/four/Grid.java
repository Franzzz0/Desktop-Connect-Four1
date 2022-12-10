package four;

import javax.swing.*;
import java.awt.*;

public class Grid extends JPanel {
    private boolean turnX;
    private final GameBoard board;

    public Grid() {
        setLayout(new GridLayout(6, 7));
        turnX = true;
        board = new GameBoard();
        this.addCells();
    }

    public void reset() {
        this.board.reset();
        this.turnX = true;
    }

    private void addCells() {
        for (int i = 6; i > 0 ; i--) {
            for (char c = 'A'; c <='G'; c++) {
                char column = c;
                String cell = "" + column + i;
                JButton button = new JButton(" ");
                button.setName(String.format("Button%s", cell));
                button.setFocusPainted(false);
                button.setBackground(Color.LIGHT_GRAY);
                this.board.addCell(c, i, button);
                button.addActionListener(e -> {
                    int row = this.board.getFirstFreeRow(column);
                    JButton targetButton = row == -1 ? null : this.board.getCell(column, row);
                    if (!this.board.isGameOver() && targetButton != null) {
                        if (turnX) {
                            targetButton.setText("X");
                            this.board.checkCells(column, row, "X");
                            turnX = false;
                        } else {
                            targetButton.setText("O");
                            this.board.checkCells(column, row, "O");
                            turnX = true;
                        }
                    }

                });
                this.add(button);
            }
        }
    }
}
