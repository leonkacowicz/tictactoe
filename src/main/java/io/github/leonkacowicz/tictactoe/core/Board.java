package io.github.leonkacowicz.tictactoe.core;

import java.io.PrintWriter;

import static io.github.leonkacowicz.tictactoe.core.BoardState.CIRCLE_WINS;
import static io.github.leonkacowicz.tictactoe.core.BoardState.CROSS_WINS;
import static io.github.leonkacowicz.tictactoe.core.BoardState.DRAW;
import static io.github.leonkacowicz.tictactoe.core.CellState.*;

public class Board {
    protected CellState[][] board;

    public Board() {
        this(3);
    }

    public Board(int n) {
        board = new CellState[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = CellState.BLANK;
            }
        }
    }

    public void setCellState(int row, int column, CellState cellState) {
        board[row][column] = cellState;
    }

    public CellState getCellState(int row, int column) {
        return board[row][column];
    }

    public int size() {
        return board.length;
    }

    public BoardState getBoardState() {

        final int n = board.length;
        int impossibleRows = 0;
        int impossibleColumns = 0;
        int impossibleDiag = 0;

        for (int row = 0; row < n; row++) {
            boolean allCircle = true;
            boolean hasCircle = false;
            boolean allCross = true;
            boolean hasCross = false;

            for (int col = 0; col < n; col++) {
                allCircle = allCircle && (board[row][col] == CIRCLE);
                allCross = allCross && (board[row][col] == CROSS);
                hasCircle = hasCircle || (board[row][col] == CIRCLE);
                hasCross = hasCross || (board[row][col] == CROSS);
            }

            if (allCircle) return CIRCLE_WINS;
            if (allCross) return CROSS_WINS;
            if (hasCircle && hasCross) ++impossibleRows;
        }

        for (int col = 0; col < n; col++) {
            boolean allCircle = true;
            boolean hasCircle = false;
            boolean allCross = true;
            boolean hasCross = false;

            for (int row = 0; row < n; row++) {
                allCircle = allCircle && (board[row][col] == CIRCLE);
                allCross = allCross && (board[row][col] == CROSS);
                hasCircle = hasCircle || (board[row][col] == CIRCLE);
                hasCross = hasCross || (board[row][col] == CROSS);
            }

            if (allCircle) return CIRCLE_WINS;
            if (allCross) return CROSS_WINS;
            if (hasCircle && hasCross) ++impossibleColumns;
        }

        // main diagonal
        boolean allCircle = true;
        boolean hasCircle = false;
        boolean allCross = true;
        boolean hasCross = false;

        for (int i = 0; i < n; i++) {
            allCircle = allCircle && (board[i][i] == CIRCLE);
            allCross = allCross && (board[i][i] == CROSS);
            hasCircle = hasCircle || (board[i][i] == CIRCLE);
            hasCross = hasCross || (board[i][i] == CROSS);
        }

        if (allCircle) return CIRCLE_WINS;
        if (allCross) return CROSS_WINS;
        if (hasCircle && hasCross) ++impossibleDiag;


        // main diagonal
        allCircle = true;
        hasCircle = false;
        allCross = true;
        hasCross = false;

        for (int i = 0; i < n; i++) {
            allCircle = allCircle && (board[i][i] == CIRCLE);
            allCross = allCross && (board[i][i] == CROSS);
            hasCircle = hasCircle || (board[i][i] == CIRCLE);
            hasCross = hasCross || (board[i][i] == CROSS);
        }

        if (allCircle) return CIRCLE_WINS;
        if (allCross) return CROSS_WINS;
        if (hasCircle && hasCross) ++impossibleDiag;


        if (impossibleRows == n && impossibleColumns == n && impossibleDiag == 2) return DRAW;
        return BoardState.NOT_FINISHED;
    }

    public void printBoard(PrintWriter printer) {

        char[] map = new char[3];
        map[CIRCLE.ordinal()] = 'o';
        map[CROSS.ordinal()] = 'x';
        map[BLANK.ordinal()] = ' ';

        for (int row = 0; row < board.length - 1; row++) {
            int col;
            for (col = 0; col < board.length - 1; col++) {
                printer.print(' ');
                printer.print(map[board[row][col].ordinal()]);
                printer.print(" |");
            }
            printer.print(' ');
            printer.print(map[board[row][col].ordinal()]);
            printer.print(' ');
            printer.println();
            for (int i = 0; i < 4 * board.length - 1; i++) printer.print('-');
            printer.println();
        }

        int row = board.length - 1;
        int col;
        for (col = 0; col < board.length - 1; col++) {
            printer.print(' ');
            printer.print(map[board[row][col].ordinal()]);
            printer.print(" |");
        }
        printer.print(' ');
        printer.print(map[board[row][col].ordinal()]);
        printer.print(' ');
        printer.println();
        printer.flush();
    }
}
