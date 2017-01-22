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


    /**
     * Given: a board
     * When: I set a cell's state
     * Then: The state is set to the correct value
     * @throws Exception
     */
    @Test
    public void test_setCellState() throws Exception {

        Board board = new Board(2);

        board.setCellState(0, 0, CellState.CIRCLE);
        Assert.assertEquals(CellState.CIRCLE, board.cellStates[0][0]);
        Assert.assertEquals(CellState.BLANK, board.cellStates[0][1]);
        Assert.assertEquals(CellState.BLANK, board.cellStates[1][0]);
        Assert.assertEquals(CellState.BLANK, board.cellStates[1][1]);

        board.setCellState(0, 0, CellState.CROSS);
        Assert.assertEquals(CellState.CROSS, board.cellStates[0][0]);
        Assert.assertEquals(CellState.BLANK, board.cellStates[0][1]);
        Assert.assertEquals(CellState.BLANK, board.cellStates[1][0]);
        Assert.assertEquals(CellState.BLANK, board.cellStates[1][1]);

        board.setCellState(0, 0, CellState.BLANK);
        Assert.assertEquals(CellState.BLANK, board.cellStates[0][0]);
        Assert.assertEquals(CellState.BLANK, board.cellStates[0][1]);
        Assert.assertEquals(CellState.BLANK, board.cellStates[1][0]);
        Assert.assertEquals(CellState.BLANK, board.cellStates[1][1]);

        board.setCellState(1, 1, CellState.CIRCLE);
        Assert.assertEquals(CellState.BLANK, board.cellStates[0][0]);
        Assert.assertEquals(CellState.BLANK, board.cellStates[0][1]);
        Assert.assertEquals(CellState.BLANK, board.cellStates[1][0]);
        Assert.assertEquals(CellState.CIRCLE, board.cellStates[1][1]);

        board.setCellState(0, 1, CellState.CROSS);
        Assert.assertEquals(CellState.BLANK, board.cellStates[0][0]);
        Assert.assertEquals(CellState.CROSS, board.cellStates[0][1]);
        Assert.assertEquals(CellState.BLANK, board.cellStates[1][0]);
        Assert.assertEquals(CellState.CIRCLE, board.cellStates[1][1]);
    }

    @Test
    public void test_getCellState() throws Exception {

        Board board = new Board(2);
        board.cellStates[0][0] = CellState.CIRCLE;
        board.cellStates[0][1] = CellState.CROSS;
        board.cellStates[1][0] = CellState.BLANK;
        board.cellStates[1][1] = CellState.CIRCLE;

        Assert.assertEquals(CellState.CIRCLE, board.getCellState(0, 0));
        Assert.assertEquals(CellState.CROSS, board.getCellState(0, 1));
        Assert.assertEquals(CellState.BLANK, board.getCellState(1, 0));
        Assert.assertEquals(CellState.CIRCLE, board.getCellState(1, 1));

    }
}
