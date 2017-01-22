package io.github.leonkacowicz.tictactoe.cli;

import io.github.leonkacowicz.tictactoe.core.Board;
import io.github.leonkacowicz.tictactoe.core.Game;
import io.github.leonkacowicz.tictactoe.core.Player;

import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) {
        System.out.println("Tic Tac Toe - v0.1");
        Board board = new Board(3);
        PrintWriter printer = new PrintWriter(System.out);
        Player p1 = new CliPlayer();
        Player p2 = new CliPlayer();
        Game game = new Game(board, p1, p2);
        String result = game.runGame().name();
        board.printBoard(printer);
        System.out.println(result);
    }
}
