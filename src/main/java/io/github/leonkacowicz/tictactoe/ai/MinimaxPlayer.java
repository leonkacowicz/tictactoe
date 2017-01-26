package io.github.leonkacowicz.tictactoe.ai;

import io.github.leonkacowicz.tictactoe.core.*;

import java.io.PrintWriter;
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
        System.out.println("Minimax: " + maxSteps);
        board.printBoard(new PrintWriter(System.out));
        Move[] bestMoveOut = new Move[1];
        getBoardScore(board, marker, 0, bestMoveOut);
        return bestMoveOut[0];
    }

    protected double heuristic(Board board, CellState turn) {

        double circleScore = 0;
        double crossScore = 0;
        double circleTrial = 0;
        double crossTrial = 0;
        int n = board.size();
        // row
        for (int i = 0; i < n; i++) {
            circleTrial = 0;
            for (int j = 0; j < n; j++) {
                if (board.getCellState(i, j) == CROSS) {
                    circleTrial = 0;
                    break;
                } else {
                    circleTrial += 1;
                }
            }
            crossTrial = 0;
            for (int j = 0; j < n; j++) {
                if (board.getCellState(i, j) == CIRCLE) {
                    crossTrial = 0;
                    break;
                } else {
                    crossTrial += 1;
                }
            }
        }
        if (circleTrial > circleScore) circleScore = circleTrial;
        if (crossTrial > crossScore) crossScore = crossTrial;

        // col
        for (int i = 0; i < n; i++) {
            circleTrial = 0;
            for (int j = 0; j < n; j++) {
                if (board.getCellState(j, i) == CROSS) {
                    circleTrial = 0;
                    break;
                } else {
                    circleTrial += 1;
                }
            }
            crossTrial = 0;
            for (int j = 0; j < n; j++) {
                if (board.getCellState(j, i) == CIRCLE) {
                    crossTrial = 0;
                    break;
                } else {
                    crossTrial += 1;
                }
            }
        }
        if (circleTrial > circleScore) circleScore = circleTrial;
        if (crossTrial > crossScore) crossScore = crossTrial;

        if (turn == CIRCLE) {
            if (circleScore > crossScore) return circleScore / n;
            else return -crossScore / n;
        } else {
            if (circleScore > crossScore) return -circleScore / n;
            else return crossScore / n;
        }
    }

    protected double getBoardScore(Board board, CellState turn, double depth, Move[] bestMoveOut) {
        boolean debug = false;
        if (debug) System.out.println("Depth " + depth);
        if (debug) board.printBoard(new PrintWriter(System.out));
        BoardState boardState = board.getBoardState();
        if (boardState == CIRCLE_WINS && turn == CIRCLE) {
            if (debug) System.out.println("[circle wins]");
            return 100 * maxSteps / depth;
        }
        if (boardState == CIRCLE_WINS && turn == CROSS) {
            if (debug) System.out.println("[circle wins]");
            return -100 * maxSteps / depth;
        }
        if (boardState == CROSS_WINS && turn == CIRCLE) {
            if (debug) System.out.println("[cross wins]");
            return -100 * maxSteps / depth;
        }
        if (boardState == CROSS_WINS && turn == CROSS) {
            if (debug) System.out.println("[cross wins]");
            return 100 * maxSteps / depth;
        }
        if (depth >= maxSteps) return heuristic(board, turn);

        List<Move> validMoves = board.getValidMoves();
        if (validMoves.size() == 0) {
            return 0.0;
        }
        if (validMoves.size() > 10) {
            depth = Math.max(maxSteps - 10, depth);
        }
        if (debug) System.out.println("Turn = " + turn + ", valid moves: " + validMoves);
        Move bestMove = null;
        double best;
        best = -Double.MAX_VALUE;
        for (Move move : validMoves) {
            if (debug) System.out.println("Trying " + move);
            board.setCellState(move.row, move.column, turn);
            double trial = -1.0 * getBoardScore(board, (turn == CIRCLE) ? CROSS : CIRCLE, depth + 1.0D, null);
            if (trial > best) {
                if (debug) System.out.println("Got new best: " + trial);
                best = trial;
                bestMove = move;
            }
            board.setCellState(move.row, move.column, BLANK);
        }

        if (bestMoveOut != null) {
            if (bestMove == null) {
                System.out.println("best: " + best);
                throw new IllegalStateException();
            }
            bestMoveOut[0] = bestMove;
            System.out.println("Best move = " + bestMove);
        }
        return best;
    }
}
