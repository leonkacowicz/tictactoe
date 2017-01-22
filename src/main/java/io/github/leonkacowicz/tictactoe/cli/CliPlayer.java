package io.github.leonkacowicz.tictactoe.cli;

import io.github.leonkacowicz.tictactoe.core.Board;
import io.github.leonkacowicz.tictactoe.core.Move;
import io.github.leonkacowicz.tictactoe.core.Player;

import java.io.PrintWriter;
import java.util.Scanner;

public class CliPlayer implements Player {

    private PrintWriter printer = new PrintWriter(System.out);

    @Override
    public Move getNextMove(Board board) {

        board.printBoard(printer);
        int n = new Scanner(System.in).nextInt();
        int row = board.size() - (n - 1) / board.size() - 1;
        int col = (n - 1) % board.size();
        return new Move(row, col);
    }
}
