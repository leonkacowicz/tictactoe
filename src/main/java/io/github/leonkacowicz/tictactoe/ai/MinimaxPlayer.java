package io.github.leonkacowicz.tictactoe.ai;

import io.github.leonkacowicz.tictactoe.core.*;

import java.util.List;

import static io.github.leonkacowicz.tictactoe.core.BoardState.CIRCLE_WINS;
import static io.github.leonkacowicz.tictactoe.core.BoardState.CROSS_WINS;
import static io.github.leonkacowicz.tictactoe.core.CellState.BLANK;
import static io.github.leonkacowicz.tictactoe.core.CellState.CIRCLE;
import static io.github.leonkacowicz.tictactoe.core.CellState.CROSS;

public class MinimaxPlayer implements Player {

    private int maxSteps;
    private CellState marker;
    private CellState opponent;

    public MinimaxPlayer(int maxSteps, CellState marker) {
        this.marker = marker;
        if (marker == CIRCLE) {
            opponent = CROSS;
        } else if (marker == CROSS) {
            opponent = CIRCLE;
        } else {
            throw new IllegalArgumentException("Marker must be either CROSS or CIRCLE");
        }

        this.maxSteps = maxSteps;
    }

    @Override
    public Move getNextMove(Board board) {
        Move[] bestMoveOut = new Move[1];
        getBoardScore(board, marker, 0, bestMoveOut);
        return bestMoveOut[0];
    }

    protected int getBoardScore(Board board, CellState turn, int depth, Move[] bestMoveOut) {
        BoardState boardState = board.getBoardState();
        if (boardState == CIRCLE_WINS && turn == CIRCLE) return 1;
        if (boardState == CIRCLE_WINS && turn == CROSS) return -1;
        if (boardState == CROSS_WINS && turn == CIRCLE) return -1;
        if (boardState == CROSS_WINS && turn == CROSS) return 1;
        if (depth >= maxSteps) return 0;

        List<Move> validMoves = board.getValidMoves();
        Move bestMove = null;
        int best;
        if (turn == CIRCLE) {
            best = -10;
            for (Move move : validMoves) {
                board.setCellState(move.row, move.column, turn);
                int trial = -getBoardScore(board, CROSS, depth + 1, null);
                if (trial > best) {
                    best = trial;
                    bestMove = move;
                }
                board.setCellState(move.row, move.column, BLANK);
            }
        } else {
            best = 10;
            for (Move move : validMoves) {
                board.setCellState(move.row, move.column, turn);
                int trial = -getBoardScore(board, CIRCLE, depth + 1, null);
                if (trial < best) {
                    best = trial;
                    bestMove = move;
                }
                board.setCellState(move.row, move.column, BLANK);
            }
        }
        if (bestMoveOut != null) {
            bestMoveOut[0] = bestMove;
            System.out.println("Best move = " + bestMove);
        }
        return best;
    }
}
