package four;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class GameBoard {
    private final HashMap<Character, HashMap<Integer, JButton>> board;
    private boolean gameOver;

    public GameBoard() {
        this.board = new HashMap<>();
        gameOver = false;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void addCell(char letter, int index, JButton cell) {
        this.board.putIfAbsent(letter, new HashMap<>());
        this.board.get(letter).putIfAbsent(index, cell);
    }

    public JButton getCell(char column, int row) {
        return this.board.get(column).get(row);
    }

    public int getFirstFreeRow(char column) {
        for (int i = 1; i <=7; i++) {
            JButton button = this.board.get(column).get(i);
            if (button != null && button.getText().equals(" ")) {
                return i;
            }
        }
        return -1;
    }

    public void reset() {
        for (char c : this.board.keySet()) {
            for (int i : this.board.get(c).keySet()) {
                this.board.get(c).get(i).setText(" ");
                this.board.get(c).get(i).setBackground(Color.LIGHT_GRAY);
            }
        }
        gameOver = false;
    }

    public void checkCells(char column, int row, String player) {
        checkColumn(column, row, player);
        checkRow(column, row, player);
        checkDiagonals(column, row, player);
    }

    private void checkColumn(char column, int row, String player) {
        if (row < 4) {
            return;
        }
        JButton[] cells = new JButton[4];
        cells[0] = getCell(column, row);
        int index = 1;
        int checkedRow = row - 1;

        while (checkedRow > 0 && index <= 3) {
            JButton cell = getCell(column, checkedRow);
            if (!cell.getText().equals(player)) {
                return;
            } else {
                cells[index] = cell;
                checkedRow--;
                index++;
            }
        }
        endGame(cells);
    }

    private void checkRow(char column, int row, String player) {
        JButton[] cells = new JButton[4];
        cells[0] = getCell(column, row);
        int index = 1;
        char checkedColumn = column;
        while (index <= 3) {
            if (checkedColumn + 1 <= 'G') {
                checkedColumn += 1;
                JButton checkedCell = getCell(checkedColumn, row);
                if (checkedCell.getText().equals(player)) {
                    cells[index] = checkedCell;
                    index++;
                } else {
                    break;
                }
            } else {
                break;
            }
        }
        checkedColumn = column;
        while (index <= 3) {
            if (checkedColumn - 1 >= 'A') {
                checkedColumn -= 1;
                JButton checkedCell = getCell(checkedColumn, row);
                if (checkedCell.getText().equals(player)) {
                    cells[index] = checkedCell;
                    index++;
                } else {
                    return;
                }
            } else {
                return;
            }
        }
        endGame(cells);
    }

    private void checkDiagonals(char column, int row, String player) {
        JButton[] cells = new JButton[4];
        cells[0] = getCell(column, row);
        JButton checkedCell;

        int index = 1;
        char checkedColumn = column;
        int checkedRow = row;

        while (index <= 3 && (checkedColumn != 'G' && checkedRow != 6)) {
            checkedColumn++;
            checkedRow++;
            checkedCell = getCell(checkedColumn, checkedRow);
            if (checkedCell.getText().equals(player)) {
                cells[index] = checkedCell;
                index++;
            } else {
                break;
            }
        }
        checkedColumn = column;
        checkedRow = row;
        while (index <= 3 && (checkedColumn != 'A' && checkedRow != 1)) {
            checkedColumn--;
            checkedRow--;
            checkedCell = getCell(checkedColumn, checkedRow);
            if (checkedCell.getText().equals(player)) {
                cells[index] = checkedCell;
                index++;
            } else {
                break;
            }
        }
        if (index == 4) {
            endGame(cells);
        }

        index = 1;
        checkedColumn = column;
        checkedRow = row;

        while (index <= 3 && (checkedColumn != 'G' && checkedRow != 1)) {
            checkedColumn++;
            checkedRow--;
            checkedCell = getCell(checkedColumn, checkedRow);
            if (checkedCell.getText().equals(player)) {
                cells[index] = checkedCell;
                index++;
            } else {
                break;
            }
        }
        checkedColumn = column;
        checkedRow = row;
        while (index <= 3 && (checkedColumn != 'A' && checkedRow != 6)) {
            checkedColumn--;
            checkedRow++;
            checkedCell = getCell(checkedColumn, checkedRow);
            if (checkedCell.getText().equals(player)) {
                cells[index] = checkedCell;
                index++;
            } else {
                return;
            }
        }
        if (index == 4) {
            endGame(cells);
        }
    }

    private void endGame(JButton[] cells) {
        for (JButton cell : cells) {
            cell.setBackground(Color.YELLOW);
        }
        this.gameOver = true;
    }
}

