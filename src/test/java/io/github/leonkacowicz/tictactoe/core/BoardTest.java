package io.github.leonkacowicz.tictactoe.core;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import static io.github.leonkacowicz.tictactoe.core.CellState.BLANK;
import static io.github.leonkacowicz.tictactoe.core.CellState.CIRCLE;
import static io.github.leonkacowicz.tictactoe.core.CellState.CROSS;

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

        Assert.assertEquals(CellState.BLANK, board.board[0][0]);
        Assert.assertEquals(CellState.BLANK, board.board[0][1]);
        Assert.assertEquals(CellState.BLANK, board.board[1][0]);
        Assert.assertEquals(CellState.BLANK, board.board[1][1]);
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

        board.setCellState(0, 0, CIRCLE);
        Assert.assertEquals(CIRCLE, board.board[0][0]);
        Assert.assertEquals(CellState.BLANK, board.board[0][1]);
        Assert.assertEquals(CellState.BLANK, board.board[1][0]);
        Assert.assertEquals(CellState.BLANK, board.board[1][1]);

        board.setCellState(0, 0, CellState.CROSS);
        Assert.assertEquals(CellState.CROSS, board.board[0][0]);
        Assert.assertEquals(CellState.BLANK, board.board[0][1]);
        Assert.assertEquals(CellState.BLANK, board.board[1][0]);
        Assert.assertEquals(CellState.BLANK, board.board[1][1]);

        board.setCellState(0, 0, CellState.BLANK);
        Assert.assertEquals(CellState.BLANK, board.board[0][0]);
        Assert.assertEquals(CellState.BLANK, board.board[0][1]);
        Assert.assertEquals(CellState.BLANK, board.board[1][0]);
        Assert.assertEquals(CellState.BLANK, board.board[1][1]);

        board.setCellState(1, 1, CIRCLE);
        Assert.assertEquals(CellState.BLANK, board.board[0][0]);
        Assert.assertEquals(CellState.BLANK, board.board[0][1]);
        Assert.assertEquals(CellState.BLANK, board.board[1][0]);
        Assert.assertEquals(CIRCLE, board.board[1][1]);

        board.setCellState(0, 1, CellState.CROSS);
        Assert.assertEquals(CellState.BLANK, board.board[0][0]);
        Assert.assertEquals(CellState.CROSS, board.board[0][1]);
        Assert.assertEquals(CellState.BLANK, board.board[1][0]);
        Assert.assertEquals(CIRCLE, board.board[1][1]);
    }

    /**
     * Given: a board with a known state
     * When: getCellState is called
     * Then: it returns the right value
     * @throws Exception
     */
    @Test
    public void test_getCellState() throws Exception {

        Board board = new Board(2);
        board.board[0][0] = CIRCLE;
        board.board[0][1] = CellState.CROSS;
        board.board[1][0] = CellState.BLANK;
        board.board[1][1] = CIRCLE;

        Assert.assertEquals(CIRCLE, board.getCellState(0, 0));
        Assert.assertEquals(CellState.CROSS, board.getCellState(0, 1));
        Assert.assertEquals(CellState.BLANK, board.getCellState(1, 0));
        Assert.assertEquals(CIRCLE, board.getCellState(1, 1));

    }

    @Test
    public void test_size() throws Exception {
        Board board = new Board(4);

        Assert.assertEquals(4, board.size());
    }

    @Test
    public void test_getBoardState() throws Exception {
        Board board = new Board(3);
        Assert.assertEquals(BoardState.NOT_FINISHED, board.getBoardState());

        board = new Board(3);
        board.board[0][0] = CIRCLE;
        board.board[1][1] = CIRCLE;
        board.board[2][2] = CIRCLE;
        Assert.assertEquals(BoardState.CIRCLE_WINS, board.getBoardState());

        board = new Board(3);
        board.board[0][0] = CIRCLE; board.board[0][1] = CROSS;  board.board[0][2] = CROSS;
        board.board[1][0] = CROSS;  board.board[1][1] = CIRCLE; board.board[1][2] = CIRCLE;
        board.board[2][0] = CIRCLE; board.board[2][1] = CROSS;  board.board[2][2] = CROSS;
        Assert.assertEquals(BoardState.DRAW, board.getBoardState());

        board = new Board(3);
        board.board[0][0] = CROSS; board.board[0][1] = CIRCLE;  board.board[0][2] = CROSS;
        board.board[1][0] = CIRCLE;  board.board[1][1] = BLANK; board.board[1][2] = CROSS;
        board.board[2][0] = CIRCLE; board.board[2][1] = CROSS;  board.board[2][2] = CIRCLE;
        Assert.assertEquals(BoardState.DRAW, board.getBoardState());

        board = new Board(3);
        board.board[0][0] = BLANK; board.board[0][1] = CIRCLE;  board.board[0][2] = CROSS;
        board.board[1][0] = CIRCLE;  board.board[1][1] = CROSS; board.board[1][2] = CROSS;
        board.board[2][0] = CIRCLE; board.board[2][1] = CROSS;  board.board[2][2] = CIRCLE;
        Assert.assertEquals(BoardState.NOT_FINISHED, board.getBoardState());

        board = new Board(3);
        board.board[0][0] = BLANK; board.board[0][1] = CIRCLE;  board.board[0][2] = CROSS;
        board.board[1][0] = CIRCLE;  board.board[1][1] = CROSS; board.board[1][2] = CROSS;
        board.board[2][0] = CIRCLE; board.board[2][1] = CROSS;  board.board[2][2] = CROSS;
        Assert.assertEquals(BoardState.CROSS_WINS, board.getBoardState());
    }

    @Test
    public void test_printBoard() throws Exception {

        Board board = new Board(2);

        board.board[0][0] = CIRCLE;
        board.board[0][1] = CROSS;
        board.board[1][0] = CROSS;
        board.board[1][1] = BLANK;

        StringWriter out = new StringWriter();
        PrintWriter printer = new PrintWriter(out);
        board.printBoard(printer);
        Assert.assertEquals(" o | x \n-------\n x |   \n", out.toString());
    }
}
