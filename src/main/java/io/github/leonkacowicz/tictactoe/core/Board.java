package io.github.leonkacowicz.tictactoe.core;

public class Board {
    protected CellState[][] cellStates;

    public Board() {
        this(3);
    }

    public Board(int n) {
        cellStates = new CellState[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cellStates[i][j] = CellState.BLANK;
            }
        }
    }

    public void setCellState(int row, int column, CellState cellState) {
        cellStates[row][column] = cellState;
    }

    public CellState getCellState(int row, int column) {
        return cellStates[row][column];
    }

    public int size() {
        return cellStates.length;
    }

    public BoardState getBoardState() {
        return null;
    }
}
