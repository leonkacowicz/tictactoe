package io.github.leonkacowicz.tictactoe.cli;

import io.github.leonkacowicz.tictactoe.ai.MinimaxPlayer;
import io.github.leonkacowicz.tictactoe.ai.RandomPlayer;
import io.github.leonkacowicz.tictactoe.core.Board;
import io.github.leonkacowicz.tictactoe.core.CellState;
import io.github.leonkacowicz.tictactoe.core.Game;
import io.github.leonkacowicz.tictactoe.core.Player;

import java.io.PrintWriter;

import static io.github.leonkacowicz.tictactoe.core.CellState.CIRCLE;
import static io.github.leonkacowicz.tictactoe.core.CellState.CROSS;

public class Main {
    public static void main(String[] args) {
        System.out.println("Tic Tac Toe - v0.1");
        Board board = new Board(4);
        PrintWriter printer = new PrintWriter(System.out);
        Player p1 = new RandomPlayer();
        //Player p1 = new MinimaxPlayer(2, CROSS);
        Player p2 = new MinimaxPlayer(6, CIRCLE);
        Game game = new Game(board, p1, p2);
        String result = game.runGame().name();
        board.printBoard(printer);
        System.out.println(result);
    }
}
