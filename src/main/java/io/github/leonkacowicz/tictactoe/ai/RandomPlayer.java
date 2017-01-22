package io.github.leonkacowicz.tictactoe.ai;

import io.github.leonkacowicz.tictactoe.core.Board;
import io.github.leonkacowicz.tictactoe.core.CellState;
import io.github.leonkacowicz.tictactoe.core.Move;
import io.github.leonkacowicz.tictactoe.core.Player;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomPlayer implements Player {


    @Override
    public Move getNextMove(Board board) {
        int size = board.size();
        List<Integer> validMoves = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board.getCellState(i, j) == CellState.BLANK)
                    validMoves.add(i * size + j);
            }
        }

        int intmove = validMoves.get(new Random().nextInt(validMoves.size()));
        return new Move(intmove / size, intmove % size);
    }
}
