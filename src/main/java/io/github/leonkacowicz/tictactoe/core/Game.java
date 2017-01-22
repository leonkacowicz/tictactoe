package io.github.leonkacowicz.tictactoe.core;

import static io.github.leonkacowicz.tictactoe.core.BoardState.NOT_FINISHED;

public class Game {

    protected Board board;
    protected Player playerCross;
    protected Player playerCircle;
    protected Player currentPlayer;

    public Game(Board board, Player playerCross, Player playerCircle) {
        this.board = board;
        this.playerCross = playerCross;
        this.playerCircle = playerCircle;

        if (Math.random() < 0.5) {
            currentPlayer = playerCircle;
        } else {
            currentPlayer = playerCross;
        }
    }

    public BoardState runGame() {
        BoardState boardState;
        do {

            Move move;
            do {
                move = currentPlayer.getNextMove(board);
            } while (!isMoveValid(move));

            if (currentPlayer == playerCircle) {
                board.setCellState(move.row, move.column, CellState.CIRCLE);
                currentPlayer = playerCross;
            } else {
                board.setCellState(move.row, move.column, CellState.CROSS);
                currentPlayer = playerCircle;
            }
            boardState = board.getBoardState();
        } while (boardState == NOT_FINISHED);
        return boardState;
    }

    protected boolean isMoveValid(Move move) {
        return true;
    }

}
