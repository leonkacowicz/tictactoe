package io.github.leonkacowicz.tictactoe.cli;

import io.github.leonkacowicz.tictactoe.core.Board;

import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) {
        System.out.println("Tic Tac Toe - v0.1");
        Board board = new Board(4);
        PrintWriter printer = new PrintWriter(System.out);
        board.printBoard(printer);
        printer.flush();
    }
}
