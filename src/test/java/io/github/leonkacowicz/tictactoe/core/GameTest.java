package io.github.leonkacowicz.tictactoe.core;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static io.github.leonkacowicz.tictactoe.core.BoardState.CIRCLE_WINS;
import static org.mockito.Mockito.mock;

public class GameTest {

    @Test
    public void test() throws Exception {

        Board board = mock(Board.class);
        Player player = mock(Player.class, x -> new Move(0, 0));
        Mockito.when(board.getBoardState()).thenReturn(CIRCLE_WINS);
        Game game = new Game(board, player, player);

        Assert.assertEquals(CIRCLE_WINS, game.runGame());
    }
}
