package io.github.leonkacowicz.tictactoe.ai;

import io.github.leonkacowicz.tictactoe.core.Board;
import io.github.leonkacowicz.tictactoe.core.Move;
import org.junit.Test;

import static io.github.leonkacowicz.tictactoe.core.CellState.CIRCLE;
import static io.github.leonkacowicz.tictactoe.core.CellState.CROSS;
import static org.junit.Assert.assertEquals;

public class MinimaxPlayerTest {

    @Test
    public void test() {
        // GIVEN
        MinimaxPlayer player = new MinimaxPlayer(2, CIRCLE);
        Board board = new Board();
        board.setCellState(0, 0, CROSS);
        board.setCellState(1, 1, CROSS);

        // WHEN
        Move nextMove = player.getNextMove(board);

        // THEN
        assertEquals(new Move(2, 2), nextMove);
    }

    @Test
    public void test2() {
        // GIVEN
        MinimaxPlayer player = new MinimaxPlayer(2, CIRCLE);
        Board board = new Board();
        board.setCellState(0, 1, CROSS);
        board.setCellState(1, 1, CROSS);

        // WHEN
        Move nextMove = player.getNextMove(board);

        // THEN
        assertEquals(new Move(2, 1), nextMove);
    }
}
