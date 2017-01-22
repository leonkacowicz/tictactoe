package io.github.leonkacowicz.tictactoe.core;

import org.junit.Assert;
import org.junit.Test;

public class BoardTest {

    /**
     * Given: no current board
     * When: I create a new board
     * Then: All cells are blank
     * @throws Exception
     */
    @Test
    public void test_constructor() throws Exception {

        Board board = new Board(2);

        Assert.assertEquals(CellState.BLANK, board.cellStates[0][0]);
        Assert.assertEquals(CellState.BLANK, board.cellStates[0][1]);
        Assert.assertEquals(CellState.BLANK, board.cellStates[1][0]);
        Assert.assertEquals(CellState.BLANK, board.cellStates[1][1]);
    }
}
